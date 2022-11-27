package cn.edu.tongji.backend.report.controller;

import cn.edu.tongji.backend.report.pojo.ReportTemplate;
import cn.edu.tongji.backend.report.service.ReportTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportTemplateController {
    @Autowired
    private ReportTemplateService reportTemplateService;

    @PostMapping("/postReportTemplate")
    public String receiveReportTemplate(@RequestBody List<ReportTemplate> reports) {
        System.out.println(reports);
        for (ReportTemplate reportTemplate : reports) {
            reportTemplateService.insertReportTemplate(reportTemplate);
        }
        return null;
    }

    @GetMapping("/getReportTemplate")
    public List<ReportTemplate> selectReportTemplate(@RequestParam("l_id") int l_id) {
        System.out.println( reportTemplateService.selectLabReportTemplate(l_id));
        return reportTemplateService.selectLabReportTemplate(l_id);
    }

}
