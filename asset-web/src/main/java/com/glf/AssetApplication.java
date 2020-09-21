package com.glf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AssetApplication {
    public static void main(String[] args){
        SpringApplication.run(AssetApplication.class, args);
        System.out.println("########启动成功########\n");
    }
}
