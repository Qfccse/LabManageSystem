package cn.edu.tongji.backend.report.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Report {
    private int r_id;
    private String s_id;
    private int l_id;
    private String name;
    private Timestamp submit_time;
    private int status;
    // 非数据库字段
    private String stu_name;
}
