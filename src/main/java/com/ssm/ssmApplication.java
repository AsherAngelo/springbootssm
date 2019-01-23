package com.ssm;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
@SpringBootApplication
public class ssmApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(ssmApplication.class).bannerMode(Banner.Mode.OFF).build();
        springApplication.run(args);
    }
}
