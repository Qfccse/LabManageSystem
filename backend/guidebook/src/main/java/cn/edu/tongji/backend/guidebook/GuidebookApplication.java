package cn.edu.tongji.backend.guidebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.tongji.backend.guidebook.mapper")
@SpringBootApplication
public class GuidebookApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuidebookApplication.class, args);
    }
}
