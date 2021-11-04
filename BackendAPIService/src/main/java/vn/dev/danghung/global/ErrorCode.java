package vn.dev.danghung.global;

public class ErrorCode {
    public static final int JWT_ERROR = 601;
    public static final int ACCESS_DENIED = 602;
    public static final int DATE_TIME_INVALID = 603;
    //////////////////////////////////////////////////
    //user error 610-629
    public static final int USERNAME_INVALID = 610;
    public static final int PASSWORD_INVALID = 611;
    public static final int USER_PHONE_INVALID = 612;
    public static final int USER_OR_PASSWORD_INVALID = 613;
    public static final int USER_ACCOUNT_IS_BLOCKED = 614;
    public static final int USER_FULLNAME_INVALID = 615;
    public static final int USER_CHANGE = 616;
    ////////////////////////////////////////////////////
    //product and brand error 630-639
    public static final int PRODUCT_FIND_ALL = 630;
    public static final int PRODUCT_FIND = 631;
    public static final int BRAND_FIND_ALL = 632;
    //////////////////////////////////////////////////
    //cart error 640-649
    public static final int CART_ADD = 640;
    public static final int CART_REDUCE = 641;
    public static final int CART_OVERVIEW = 642;

    //////////////////////////////////////////////////
    // order error 650-659
    public static final int ORDER_USER_CREATE = 650;
    public static final int ORDER_USER_ALL = 651;
    ///////////////////////////////////////////////////
    // admin error
    public static final int ADMIN_BLOCK_USER = 660;
    public static final int ADMIN_UNBLOCK_USER = 661;
    public static final int ADMIN_GET_ORDER = 662;
    public static final int ADMIN_GET_USER = 663;
    public static final int ADMIN_CONFIRM_ORDER = 664;
    public static final int ADMIN_REJECT_ORDER = 665;
    public static final int ADMIN_STATISTICAL = 666;


}
