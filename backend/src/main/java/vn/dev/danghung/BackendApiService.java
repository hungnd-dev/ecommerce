package vn.dev.danghung;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import vn.dev.danghung.global.ConfigInfo;

import java.util.Properties;

@SpringBootApplication
public class BackendApiService {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("server.port", ConfigInfo.SERVICE_PORT);

        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder()
                .sources(BackendApiService.class)
                .properties(prop);
        applicationBuilder.run(args);

    }
}
