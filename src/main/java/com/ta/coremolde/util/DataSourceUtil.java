package com.ta.coremolde.util;

import com.ta.coremolde.master.model.entity.ShopTenant;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DataSourceUtil {
    private static Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);

    public static DataSource createAndConfigureDataSource(ShopTenant shopTenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(shopTenant.getUsername());
        ds.setPassword(shopTenant.getPassword());
        ds.setJdbcUrl(shopTenant.getUrl());
        ds.setDriverClassName("org.postgresql.Driver");

        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(10);
        ds.setMaximumPoolSize(20);
        ds.setIdleTimeout(300000);

        Integer tenantId = shopTenant.getId();
        String tenantConnectionPoolName = tenantId + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        LOG.info("Configured datasource:" + tenantId
                + ". Connection poolname:" + tenantConnectionPoolName);

        return ds;
    }

}
