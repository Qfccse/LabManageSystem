package cn.edu.tongji.backend.report.controller;

import cn.edu.tongji.backend.report.pojo.Report;
import cn.edu.tongji.backend.report.pojo.ReportForm;
import cn.edu.tongji.backend.report.pojo.ReportRow;
import cn.edu.tongji.backend.report.pojo.ReportTemplate;
import cn.edu.tongji.backend.report.service.ReportFormService;
import cn.edu.tongji.backend.report.service.ReportService;
import cn.edu.tongji.backend.report.service.ReportTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportFormService reportFormService;
    @Autowired
    private ReportTemplateService reportTemplateService;

    @PostMapping("/postReport")
    public int postReport(@RequestBody Report report){
        reportService.insertReport(report);
        return report.getR_id();
    }

    @GetMapping("/getReportFiller")
    public List<ReportRow> postReportFiller(@RequestParam("l_id") int l_id, @RequestParam("s_id") String s_id){
        List<ReportTemplate> reportTemplates = reportTemplateService.selectLabReportTemplate(l_id);
        Report report = reportService.selectStuReport(l_id, s_id);
        System.out.println(l_id + " " +" " + s_id +" " + report);
        List<ReportRow> reportRows = new ArrayList<>();
        if(report.getStatus()==0){
            for (ReportTemplate reportTemplate : reportTemplates) {
                reportRows.add(new ReportRow(reportTemplate,"",0));
            }
        }
        else {
            reportRows= getReport(l_id, s_id);
        }

        return reportRows;
    }

    @GetMapping("/getReport")
    public List<ReportRow> getReport(@RequestParam("l_id") int l_id, @RequestParam("s_id") String s_id){
        int r_id = reportService.getReportId(l_id, s_id);
        //System.out.println(r_id);
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
