package com.ta.coremolde.master.model.exception;

import org.springframework.security.core.AuthenticationException;

public class DifferentShopException extends AuthenticationException {
    public DifferentShopException(String msg, Throwable t) {
        super(msg, t);
    }

    public DifferentShopException(String msg) {
        super(msg);
    }
}
