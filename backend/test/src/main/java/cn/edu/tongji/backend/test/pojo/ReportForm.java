package cn.edu.tongji.backend.test.pojo;

import lombok.Data;

@Data
public class ReportForm {
    private int rf_id;
    private int l_id;
    private int s_id;
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
                "rf_id=" + rf_id +
                ", l_id=" + l_id +
                ", t_id=" + s_id +
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
