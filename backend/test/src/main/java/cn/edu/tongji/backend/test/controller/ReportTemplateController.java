package cn.edu.tongji.backend.test.controller;

import cn.edu.tongji.backend.test.pojo.ReportTemplate;
import cn.edu.tongji.backend.test.service.ReportTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class ReportTemplateController {
    @Autowired
    private ReportTemplateService reportTemplateService;

    @PostMapping("/postReportTemplate")
    public String receiveReport(@RequestBody List<ReportTemplate> reports) {
        //System.out.println(reports);
        for (ReportTemplate reportTemplate : reports) {
            reportTemplateService.insertReportTemplate(reportTemplate);
        }
        return null;
    }

    @GetMapping("/getReportTemplate")
    public List<ReportTemplate> selectReport(@RequestParam("l_id") int l_id) {
        return reportTemplateService.selectLabReportTemplate(l_id);
    }

}
