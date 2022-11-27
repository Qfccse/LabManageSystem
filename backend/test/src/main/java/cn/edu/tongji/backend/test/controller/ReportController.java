package cn.edu.tongji.backend.test.controller;

import cn.edu.tongji.backend.test.pojo.Report;
import cn.edu.tongji.backend.test.pojo.ReportForm;
import cn.edu.tongji.backend.test.pojo.ReportRow;
import cn.edu.tongji.backend.test.pojo.ReportTemplate;
import cn.edu.tongji.backend.test.service.ReportFormService;
import cn.edu.tongji.backend.test.service.ReportService;
import cn.edu.tongji.backend.test.service.ReportTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportFormService reportFormService;
    @Autowired
    private ReportTemplateService reportTemplateService;

    public String addReportToDB(Report report){
        reportService.insertReport(report);
        return report.getR_id() + "";
    }

    @GetMapping("/getReport")
    public List<ReportRow> getReport(@RequestParam("l_id") int l_id, @RequestParam("s_id") int s_id){
        int r_id = reportService.getReportId(l_id, s_id);
        List<ReportForm> reportForms = reportFormService.selectLabReportForm(r_id);
        List<ReportTemplate> reportTemplates = reportTemplateService.selectLabReportTemplate(l_id);
        List<ReportRow> reportRows = new ArrayList<ReportRow>();
        for (ReportForm reportForm : reportForms) {
            for (ReportTemplate reportTemplate : reportTemplates) {
                if(reportForm.getRt_id() == reportTemplate.getRt_id()){
                    reportRows.add(new ReportRow(reportTemplate,reportForm.getContent(),reportForm.getRf_id()));
                    break;
                }
            }
        }

        return reportRows;
    }

    @GetMapping("/getFormImages")
    public String[] getReportFormImages(@RequestParam("rf_id") int rf_id){
        System.out.println(reportService.getReportFormImage(rf_id));
        return reportService.getReportFormImage(rf_id);
    }
}
