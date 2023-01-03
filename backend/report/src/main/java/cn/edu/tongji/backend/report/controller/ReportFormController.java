package cn.edu.tongji.backend.report.controller;

import cn.edu.tongji.backend.report.pojo.Report;
import cn.edu.tongji.backend.report.pojo.ReportForm;
import cn.edu.tongji.backend.report.service.ReportFormService;
import cn.edu.tongji.backend.report.service.ReportService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportFormController {
    @Autowired
    private ReportFormService reportFormService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/postReportForm")
    public String receiveReportForm(@RequestParam("l_id") int l_id,@RequestParam("s_id") String s_id,
                                    @RequestParam("status") int status,@RequestParam("forms") String[] reportForms) {

        System.out.println(l_id);
        System.out.println(s_id);
        System.out.println(status);
        List<Integer> ids = new ArrayList<Integer>();

        int r_id = 0;
        System.out.println(reportForms);
        for (String reportForm : reportForms) {
            //System.out.println(reportForm);
            ReportForm form = JSON.parseObject(reportForm,ReportForm.class);
            System.out.println(form);
            r_id = form.getR_id();
            int update = reportFormService.checkFormExist(form.getR_id(), form.getRt_id());
            if (update==0)
            {
                System.out.println("insert form");
                reportFormService.insertReportForm(form);
            }
            else {
                System.out.println("update form");
                form.setRf_id(reportFormService.selectRfTd(form.getR_id(), form.getRt_id()));
                reportFormService.updateReportForm(form);
            }

            ids.add(form.getRf_id());
        }

        Report report = new Report();
        report.setR_id(r_id);
        report.setStatus(status);
        report.setSubmit_time(new Timestamp(new Date().getTime()));
        reportService.updateReportStatus(report);

        return ids.toString();
    }

    @GetMapping("/getReportForm")
    public List<ReportForm> selectReportForm(@RequestParam("r_id") int r_id) {
        System.out.println(r_id);
        System.out.println(reportFormService.selectLabReportForm(r_id));
        return reportFormService.selectLabReportForm(r_id);
    }

    @GetMapping("/getReportImages")
    public List<String> selectReportImages(@RequestParam("rf_id") int rf_id) {
        return reportFormService.selectLabReportImages(rf_id);
    }

    @PostMapping("/postReportImages")
    public String insertReportImages(@RequestParam("id") int id,@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        System.out.println(id);
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String dirPath = "D:/TJ-LabImages";
            //https://www.jianshu.com/p/dd0f20a6e44f
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String localFileName = System.currentTimeMillis() + fileSuffix;
            String filePath = dirPath + File.separator + localFileName;
            File localFile = new File(filePath);
            File imagePath = new File(dirPath);
            if (!imagePath.exists()) {
                imagePath.mkdirs();
            }
            // 保存文件到路径
            file.transferTo(localFile);
            String serverFilePath = localFileName;
            // 保存路径到服务器
            reportFormService.insertImage(id,serverFilePath,fileName);
        }

        return null;
    }


}
