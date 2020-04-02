package com.ta.coremolde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CoreMoldeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreMoldeApplication.class, args);
    }

}
