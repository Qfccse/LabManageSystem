package cn.edu.tongji.backend.report.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private int r_id;
    private String s_id;
    private int l_id;
    private String name;
    private Date submit_time;
    private int status;
}
