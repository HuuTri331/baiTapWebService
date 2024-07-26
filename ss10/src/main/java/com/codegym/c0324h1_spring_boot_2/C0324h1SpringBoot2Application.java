package com.codegym.c0324h1_spring_boot_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class C0324h1SpringBoot2Application {

    public static void main(String[] args) {
        SpringApplication.run(C0324h1SpringBoot2Application.class, args);
    }

}
