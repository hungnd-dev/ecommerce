package vn.dev.danghung.global;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfigInfo {
    private static Config config = ConfigFactory.parseFile(new File("conf.properties"));
    //////////////////////////////////////////////////////////////////////////////////////////
    public static final String SECRET = config.getString("secret");
}
