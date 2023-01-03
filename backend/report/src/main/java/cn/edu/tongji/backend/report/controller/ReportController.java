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


    @GetMapping("/getLabReport")
    public List<Report> getLabReport(@RequestParam("l_id") int l_id){
        System.out.println("获取实验 " + l_id + " 的全部report");
        List<Report> reports = reportService.selectLabAllReport(l_id);
        for (Report report : reports) {
            report.setStu_name(reportService.selectStuName(report.getS_id()));
        }
        return reports;
    }

    @PostMapping("/postReport")
    public int postReport(@RequestBody Report report){
        System.out.println(report);
        Integer reportNum = reportService.getReportCount(report.getL_id(), report.getS_id());
        System.out.println(reportNum);
        if(reportNum==0)
        {
            reportService.insertReport(report);
            System.out.println("创建报告" + report.getR_id() + " + " + report.getL_id() + " + " + report.getS_id());
            return report.getR_id();
        }
        else {
            System.out.println("查看报告" + report.getR_id() + " + " + report.getL_id() + " + " + report.getS_id());
            return reportService.selectStuReport(report.getL_id(), report.getS_id()).getR_id();
        }
    }

    @GetMapping("/getReportFiller")
    public List<ReportRow> postReportFiller(@RequestParam("l_id") int l_id, @RequestParam("s_id") String s_id){
        int count = reportTemplateService.selectReportTemplateCount(l_id);
        int l_id_tmp = l_id;
        if(count==0)
        {
            System.out.println("目前实验没有模板，使用默认模板");
            l_id_tmp = 1;
        }
        List<ReportTemplate> reportTemplates = reportTemplateService.selectLabReportTemplate(l_id_tmp);
        System.out.println(reportTemplates);
        Report report = reportService.selectStuReport(l_id, s_id);
        System.out.println(l_id + " " +" " + s_id +" " + report);
        List<ReportRow> reportRows = new ArrayList<>();
        if(report.getStatus()==0){
            System.out.println("未提交实验报告");
            for (ReportTemplate reportTemplate : reportTemplates) {
                reportRows.add(new ReportRow(reportTemplate,"",0));
            }
        }
        else {
            System.out.println("已提交");
            reportRows = getReport(l_id, s_id);
        }

        return reportRows;
    }

    @GetMapping("/getReport")
    public List<ReportRow> getReport(@RequestParam("l_id") int l_id, @RequestParam("s_id") String s_id){
        int r_id = reportService.getReportId(l_id, s_id);
        System.out.println(r_id);
        List<ReportForm> reportForms = reportFormService.selectLabReportForm(r_id);
        int count = reportTemplateService.selectReportTemplateCount(l_id);
        int l_id_tmp = l_id;
        if(count==0)
        {
            System.out.println("目前实验没有模板，使用默认模板");
            l_id_tmp = 1;
        }
        List<ReportTemplate> reportTemplates = reportTemplateService.selectLabReportTemplate(l_id_tmp);
        System.out.println(reportTemplates);
        System.out.println("获取 " + l_id + " + " +s_id);
        System.out.println(reportForms);
        System.out.println(reportTemplates );
        List<ReportRow> reportRows = new ArrayList<ReportRow>();
        for (ReportForm reportForm : reportForms) {
            for (ReportTemplate reportTemplate : reportTemplates) {
                if(reportForm.getRt_id() == reportTemplate.getRt_id()){
                    reportRows.add(new ReportRow(reportTemplate,reportForm.getContent(),reportForm.getRf_id()));
                    break;
                }
            }
        }

        System.out.println(reportRows);
        return reportRows;
    }

    @GetMapping("/getFormImages")
    public String[] getReportFormImages(@RequestParam("rf_id") int rf_id){
        System.out.println(reportService.getReportFormImage(rf_id));
        return reportService.getReportFormImage(rf_id);
    }
}
