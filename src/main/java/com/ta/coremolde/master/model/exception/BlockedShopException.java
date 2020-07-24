package com.ta.coremolde.master.model.exception;

import org.springframework.security.core.AuthenticationException;

public class BlockedShopException extends AuthenticationException {
    public BlockedShopException(String msg, Throwable t) {
        super(msg, t);
    }

    public BlockedShopException(String msg) {
        super(msg);
    }
}
