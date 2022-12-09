package cn.edu.tongji.backend.grade.pojo;

import lombok.Data;

@Data
public class Takes {
    private String s_id;

    private int c_id;

    private String role;

    private float grade;

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Takes{" +
                "s_id='" + s_id + '\'' +
                ", c_id=" + c_id +
                ", role='" + role + '\'' +
                ", grade=" + grade +
                '}';
    }
}
