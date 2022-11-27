package cn.edu.tongji.backend.test.pojo;

import lombok.Data;

@Data
public class ReportForm {
    private int rf_id;
    private int rt_id;
    private int r_id;
    private String content;

    @Override
    public String toString() {
        return "ReportForm{" +
                "rf_id=" + rf_id +
                ", rt_id=" + rt_id +
                ", r_id=" + r_id +
                ", content='" + content + '\'' +
                '}';
    }
}
