package cn.edu.tongji.backend.bulletin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.tongji.backend.bulletin.mapper")
@SpringBootApplication
public class BulletinApplication {
    public static void main(String[] args) {
        SpringApplication.run(BulletinApplication.class, args);
    }
}
