package com.ta.coremolde.shop.config;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.ShopTenant;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.repository.ShopTenantRepository;
import com.ta.coremolde.master.service.AccountService;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.util.DataSourceUtil;
import com.ta.coremolde.util.TenantContextHolder;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    private ShopTenantRepository shopTenantRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ShopUserService shopUserService;

    private Map<String, DataSource> dataSourceMap = new TreeMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    @Override
    protected DataSource selectAnyDataSource() {
        if (dataSourceMap.isEmpty()) {
            List<ShopTenant> shopTenants = shopTenantRepository.findAll();
            LOG.info("selectAnyDataSource() -- Total tenants: " + shopTenants.size());

            for (ShopTenant shopTenant : shopTenants) {
                dataSourceMap.put(shopTenant.getId().toString(), DataSourceUtil.createAndConfigureDataSource(shopTenant));
            }
        }

        return this.dataSourceMap.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantId) {
        tenantId = initializeTenantId(tenantId);

        if (!this.dataSourceMap.containsKey(tenantId)) {
            List<ShopTenant> shopTenants = shopTenantRepository.findAll();
            LOG.info("selectDataSource() -- Tenant: " + tenantId);

            for (ShopTenant shopTenant : shopTenants) {
                dataSourceMap.put(shopTenant.getId().toString(), DataSourceUtil.createAndConfigureDataSource(shopTenant));
            }
        }

        if (!this.dataSourceMap.containsKey(tenantId)) {
            LOG.warn("Trying to get tenant:" + tenantId + " which was not found in master db after rescan");
            throw new UsernameNotFoundException(String.format("Tenant not found after rescan, tenant=%s", tenantId));
        }

        return this.dataSourceMap.get(tenantId);
    }

    private String initializeTenantId(String tenantId) {
//        if (TenantContextHolder.getTenant() == null) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            String email = null;

            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                email = principal.toString();
            }

            Account account = accountService.getAccount(email);
            ShopUser shopUser = shopUserService.getShopUser(email);

            if (account == null) {
                TenantContextHolder.setTenantId(shopUser.getShop().getShopTenant().getId().toString());
            } else {
                TenantContextHolder.setTenantId(shopService.getShopByAccountEmail(email).getShopTenant().getId().toString());
            }
//        }

        if (!tenantId.equals(TenantContextHolder.getTenant())) {
            tenantId = TenantContextHolder.getTenant();
        }

        LOG.info("Current tenant: " + tenantId);

        return tenantId;
    }

}
