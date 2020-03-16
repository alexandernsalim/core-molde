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

    //Category
    public static final String CATEGORY_MAPPING = PREFIX + "category";
    public static final String ADD_CATEGORY = "/add";
    public static final String UPDATE_CATEGORY = "/{id}/update";
    public static final String DELETE_CATEGORY = "/{id}/delete";

    private PathConstant() {
    }

}
