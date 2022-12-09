package cn.edu.tongji.backend.grade.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Report {
    private int r_id;

    private String s_id;

    private int l_id;

    private String name;

    private Timestamp submit_time;

    private int Status;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Timestamp submit_time) {
        this.submit_time = submit_time;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "r_id=" + r_id +
                ", s_id='" + s_id + '\'' +
                ", l_id=" + l_id +
                ", name='" + name + '\'' +
                ", submit_time=" + submit_time +
                ", Status=" + Status +
                '}';
    }
}
