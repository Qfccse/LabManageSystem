package cn.edu.tongji.backend.grade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.tongji.backend.grade.mapper")
@SpringBootApplication
public class GradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradeApplication.class, args);
    }
}
