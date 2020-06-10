package com.ta.coremolde.shop.config;

import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.repository.ProductRepository;
import com.ta.coremolde.shop.service.ProductService;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ta.coremolde.shop.repository", "com.ta.coremolde.shop.model"})
@EnableJpaRepositories(
        basePackages = {"com.ta.coremolde.shop.repository", "com.ta.coremolde.shop.service"},
        entityManagerFactoryRef = "shopEntityManagerFactory",
        transactionManagerRef = "shopTransactionManager")
public class ShopDatabaseConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ShopDatabaseConfig.class);

    @Bean(name = "shopJpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private Map<String, Object> hibernateProperties(MultiTenantConnectionProvider connectionProvider, @Qualifier("currentTenantIdentifierResolver")
            CurrentTenantIdentifierResolver tenantResolver) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.MULTI_TENANT,
                MultiTenancyStrategy.SCHEMA);
        properties.put(
                Environment.MULTI_TENANT_CONNECTION_PROVIDER,
                connectionProvider);
        properties.put(
                Environment.MULTI_TENANT_IDENTIFIER_RESOLVER,
                tenantResolver);
        properties.put(Environment.DIALECT,
                "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, false);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");

        return properties;
    }

    @Bean(name = "shopTransactionManager")
    public JpaTransactionManager shopTransactionManager(@Qualifier("shopEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean(name = "datasourceBasedMultitenantConnectionProvider")
    @ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new DataSourceBasedMultiTenantConnectionProviderImpl();
    }

    @Bean(name = "shopEntityManagerFactory")
    @ConditionalOnBean(name = "datasourceBasedMultitenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasourceBasedMultitenantConnectionProvider") MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolver") CurrentTenantIdentifierResolver tenantResolver) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPackagesToScan(
                Product.class.getPackage().getName(),
                ProductRepository.class.getPackage().getName(),
                ProductService.class.getPackage().getName());
        em.setPersistenceUnitName("shop-persistence-unit");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaPropertyMap(hibernateProperties(connectionProvider, tenantResolver));
        LOG.info("tenantEntityManagerFactory set up successfully");

        return em;
    }

}
