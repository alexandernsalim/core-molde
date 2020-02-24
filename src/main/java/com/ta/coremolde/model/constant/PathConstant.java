package com.ta.coremolde.model.constant;

public class PathConstant {
    //Common
    public static final String PREFIX = "/molde/api/v1/";

    //Account
    public static final String ACCOUNT_MAPPING = PREFIX + "account";
    public static final String REGISTER_ADMIN = "/create/admin";
    public static final String REGISTER_CLIENT = "/create/client";

    //Role
    public static final String ROLE_MAPPING = PREFIX + "role";
    public static final String ADD_ROLE = "/add";

    private PathConstant() {
    }

}
