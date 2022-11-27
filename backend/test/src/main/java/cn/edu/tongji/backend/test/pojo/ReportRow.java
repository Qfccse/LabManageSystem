package cn.edu.tongji.backend.test.pojo;

import lombok.Data;

@Data
public class ReportRow {
    private int rf_id;
    private String title;
    private int order;
    private String required;
    private String type;
    private String content;

    public ReportRow(ReportTemplate reportTemplate,String content,int rf_id){
        this.title = reportTemplate.getTitle();
        this.order = reportTemplate.getOrder();
        this.required = reportTemplate.getRequired();
        this.type = reportTemplate.getType();
        this.content = content;
        this.rf_id = rf_id;
    }
}
