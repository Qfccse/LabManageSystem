package cn.edu.tongji.backend.course.pojo;

import lombok.Data;

@Data
public class Course {
    private int c_id;
    private String name;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private int status;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course{" +
                "c_id=" + c_id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
