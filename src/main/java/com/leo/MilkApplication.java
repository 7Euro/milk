package com.leo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableJpaAuditing // 使用jpa自动赋值
public class MilkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkApplication.class, args);
    }

}
