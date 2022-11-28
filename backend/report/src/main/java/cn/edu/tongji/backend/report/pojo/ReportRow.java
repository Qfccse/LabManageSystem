package cn.edu.tongji.backend.report.pojo;

import lombok.Data;

@Data
public class ReportRow {
    private int rf_id;
    private int rt_id;
    private String title;
    private int order;
    private String required;
    private String type;
    private String content;
    private String placeholder;

    public ReportRow(ReportTemplate reportTemplate,String content,int rf_id){
        this.title = reportTemplate.getTitle();
        this.order = reportTemplate.getOrder();
        this.required = reportTemplate.getRequired();
        this.type = reportTemplate.getType();
        this.content = content;
        this.rf_id = rf_id;
        this.rt_id = reportTemplate.getRt_id();
        this.placeholder = reportTemplate.getPlaceholder();
    }
}
