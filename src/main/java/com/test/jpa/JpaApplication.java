package com.test.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.test")
@EnableJpaRepositories("com.test")
@EnableJpaAuditing // BaseEntity의 AuditingEntityListener 활성화
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}