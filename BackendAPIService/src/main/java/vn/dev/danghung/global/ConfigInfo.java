package vn.dev.danghung.global;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfigInfo {
    private static Config config = ConfigFactory.parseFile(new File("conf.properties"));
    //////////////////////////////////////////////////////////////////////////////////////////
    public static final String SECRET = config.getString("secret");
    /////////////////////////////////////////////////////////////////////////////////////////
    //    table
//    public static final String MYSQL_TABLE_USER = config.getString("mysql.table.user");
//    public static final String MYSQL_TABLE_PRODUCT = config.getString("mysql.table.product");
//    public static final String MYSQL_TABLE_CART = config.getString("mysql.table.cart");
//    public static final String MYSQL_TABLE_CART_DETAIL = config.getString("mysql.table.cart.detail");
//    public static final String MYSQL_TABLE_ORDER = config.getString("mysql.table.order");
//    public static final String MYSQL_TABLE_BRAND = config.getString("mysql.table.brand");

    ///images for brand
    ///i want to set default image with brand if image = null
    public static final String HP_IMAGES = config.getString("images.hp");
    public static final String DELL_IMAGES = config.getString("images.dell");
    public static final String APPLE_IMAGES = config.getString("images.apple");
    public static final String ASUS_IMAGES = config.getString("images.asus");
    public static final String LENOVO_IMAGES = config.getString("images.lenovo");
}
