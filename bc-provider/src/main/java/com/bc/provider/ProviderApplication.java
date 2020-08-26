package com.bc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        System.setProperty("deploy_env","7771");
        SpringApplication.run(ProviderApplication.class, args);
    }
}
