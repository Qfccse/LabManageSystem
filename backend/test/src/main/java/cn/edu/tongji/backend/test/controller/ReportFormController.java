package cn.edu.tongji.backend.test.controller;

import cn.edu.tongji.backend.test.pojo.Report;
import cn.edu.tongji.backend.test.pojo.ReportForm;
import cn.edu.tongji.backend.test.service.ReportFormService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class ReportFormController {
    @Autowired
    private ReportFormService reportFormService;
    //ReportController reportController;

    @PostMapping("/postReportForm")
    public String receiveReportForm(@RequestParam("l_id") int l_id,@RequestParam("s_id") String s_id,
                                    @RequestParam("status") int status,@RequestParam("forms") String[] reportForms) {

        System.out.println(l_id);
        System.out.println(s_id);
        System.out.println(status);
        List<Integer> ids = new ArrayList<Integer>();
        System.out.println(reportForms);
        for (String reportForm : reportForms) {
            //System.out.println(reportForm);
            ReportForm form = JSON.parseObject(reportForm,ReportForm.class);
            System.out.println(form);
            reportFormService.insertReportForm(form);
            ids.add(form.getRf_id());
        }
        //Report report = new Report();
        //report.setStatus(status);
        //
        //String r_id = reportController.addReportToDB(report);

        return ids.toString();
    }

    //@GetMapping("/getReportForm")
    //public List<ReportForm> selectReportForm(@RequestParam("l_id") int l_id,@RequestParam("s_id") int s_id) {
    //    System.out.println(l_id + " + "+ s_id);
    //    System.out.println(reportFormService.selectLabReportForm(l_id, s_id));
    //    return reportFormService.selectLabReportForm(l_id, s_id);
    //}

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
            String dirPath = "C:\\Users\\ASUS\\Desktop\\ss";
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

            // 保存路径到服务器
            reportFormService.insertImage(id,filePath,fileName);
        }

        return null;
    }


}
