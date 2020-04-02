package com.ta.coremolde.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContextHolder {
    private static Logger LOG = LoggerFactory.getLogger(TenantContextHolder.class);

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenant) {
        LOG.info("Setting tenant to: " + tenant);
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }

}
