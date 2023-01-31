package com.cntomorrow.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.cntomorrow"},exclude={DataSourceAutoConfiguration.class})
@EnableCaching
@EnableScheduling
public class AdminApplication {

    public static void main(String[] args) {
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        SpringApplication.run(AdminApplication.class, args);
    }

}
