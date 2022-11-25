package cn.edu.tongji.backend.test.pojo;

import lombok.Data;

@Data
public class ReportForm {
    private int rfId;
    private int lId;
    private int sId;
    private String icon;
    private String label;
    private String title;
    private String placeholder;
    private String required;
    private String type;
    private String typeName;
    private String content;
    @Override
    public String toString() {
        return "ReportForm{" +
                "rf_id=" + rfId +
                ", l_id=" + lId +
                ", t_id=" + sId +
                ", icon='" + icon + '\'' +
                ", label='" + label + '\'' +
                ", title='" + title + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", required='" + required + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
