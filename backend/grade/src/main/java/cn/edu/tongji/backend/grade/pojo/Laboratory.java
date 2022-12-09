package cn.edu.tongji.backend.grade.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Laboratory {
    private int l_id;

    private int c_id;

    private String name;

    private Timestamp start_time;

    private Timestamp end_time;

    private String desc;

    private int proportion;

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

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

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "l_id=" + l_id +
                ", c_id=" + c_id +
                ", name='" + name + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", desc='" + desc + '\'' +
                ", proportion=" + proportion +
                '}';
    }
}
