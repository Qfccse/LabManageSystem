package cn.edu.tongji.backend.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication相当于3个注解
 * 1. @Configuration  定义一个配置类
 * 2. @EnableAutoConfiguration spring boot自动根据jar包的依赖来自动配置项目
 * 3. @ComponentScan  spring自动扫描类上的注解, 并注入spring容器
 */
@MapperScan("cn.edu.tongji.backend.login.mapper")
@SpringBootApplication
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class,args);
    }

}
