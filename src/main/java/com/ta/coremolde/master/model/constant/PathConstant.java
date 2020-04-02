package com.ta.coremolde.master.model.constant;

public class PathConstant {
    //Common
    public static final String PREFIX = "/molde/api/v1/";

    //Account
    public static final String ACCOUNT_MAPPING = PREFIX + "account";
    public static final String REGISTER_ADMIN = "/admin/register";
    public static final String REGISTER_CLIENT = "/client/register";

    //Role
    public static final String ROLE_MAPPING = PREFIX + "role";
    public static final String ADD_ROLE = "/add";

    //Category
    public static final String CATEGORY_MAPPING = PREFIX + "category";
    public static final String ADD_CATEGORY = "/add";
    public static final String UPDATE_CATEGORY = "/{id}/update";
    public static final String DELETE_CATEGORY = "/{id}/delete";

    //Request
    public static final String REQUEST_MAPPING = PREFIX + "request";
    public static final String CREATE_REQUEST = "/create";
    public static final String UPDATE_REQUEST = "/{id}/update";
    public static final String CANCEL_REQUEST = "/{id}/cancel";
    public static final String ACCEPT_REQUEST = "/{id}/accept";
    public static final String REJECT_REQUEST = "/{id}/reject";

    //Shop User
    public static final String SHOP_USER_MAPPING = PREFIX + "shopuser";
    public static final String REGISTER_SHOP_USER = "/register";

    private PathConstant() {
    }

}
