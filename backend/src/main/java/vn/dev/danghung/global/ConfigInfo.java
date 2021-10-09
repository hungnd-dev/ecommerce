package vn.dev.danghung.global;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfigInfo {
    private static Config config = ConfigFactory.parseFile(new File("conf.properties"));
    public static final int SERVICE_PORT = config.getInt("service.port");

    public static final String AES_SEEDS = config.getString("aes.seed");
    public static final String AES_NAMESPACE = config.getString("aes.namespace");
    public static final String AES_EMPLOYEE_SET = config.getString("aes.employee.set");
    //-----------------------------------------------------------------------------------------------
    public static final String MYSQL_JDBC_URL = config.getString("mysql.jdbc.url");
    public static final String MYSQL_USERNAME = config.getString("mysql.username");
    public static final String MYSQL_PASSWORD = config.getString("mysql.password");
    public static final int MYSQL_MAXIMUM_POOL_SIZE = config.getInt("mysql.maximum.pool.size");
    public static final int MYSQL_MINIMUM_IDLE_SIZE = config.getInt("mysql.minimum.idle.size");

    //-----------------------------------------------------------------------------------------------
    public static final String PHOENIX_SERVERS = config.getString("phoenix.servers.url");

    //-----------------------------------------------------------------------------------------------
    public static final String ELASTICSEARCH_CLUSTER_HOST = config.getString("elastic.cluster.url");
    public static final int ELASTICSEARCH_CLUSTER_PORT = config.getInt("elastic.cluster.port");
    public static final String ELASTICSEARCH_CLUSTER_INDEX = config.getString("elastic.cluster.index");

}