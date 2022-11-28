package cn.edu.tongji.backend.laboratory.pojo;

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
}
