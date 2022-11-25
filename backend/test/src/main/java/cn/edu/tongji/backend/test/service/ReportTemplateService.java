package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.ReportTemplateMapper;
import cn.edu.tongji.backend.test.pojo.ReportTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTemplateService {
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    public void insertReportTemplate(ReportTemplate reportTemplate) {
        reportTemplateMapper.insertIntoReportTemplate(reportTemplate);
    }

    public List<ReportTemplate> selectLabReportTemplate(int l_id){
        return reportTemplateMapper.getLabReportTemplate(l_id);
    }
}
