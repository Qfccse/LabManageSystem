package cn.edu.tongji.backend.report.pojo;

import lombok.Data;

@Data
public class ReportTemplate {
    private int rt_id;
    private int l_id;
    private String t_id;
    private String title;
    private int order;
    private String placeholder;
    private String required;
    private String type;

    @Override
    public String toString() {
        return "ReportTemplate{" +
                "rt_id=" + rt_id +
                ", l_id=" + l_id +
                ", t_id=" + t_id +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", required='" + required + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
