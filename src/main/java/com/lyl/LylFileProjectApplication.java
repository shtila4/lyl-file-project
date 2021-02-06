package com.lyl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;*/

@SpringBootApplication
@MapperScan("com.lyl.mapper")

public class LylFileProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LylFileProjectApplication.class, args);
    }

}
