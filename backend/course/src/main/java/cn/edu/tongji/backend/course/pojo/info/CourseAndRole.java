package cn.edu.tongji.backend.course.pojo.info;

import cn.edu.tongji.backend.course.pojo.Course;
import lombok.Data;

@Data
public class CourseAndRole extends Course {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
