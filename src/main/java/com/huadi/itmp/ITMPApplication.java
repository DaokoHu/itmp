package com.huadi.itmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.huadi.itmp.modules.*.mapper")
@SpringBootApplication
public class ITMPApplication {

    public static void main(String[] args) {
        SpringApplication.run(ITMPApplication.class, args);
    }

}
