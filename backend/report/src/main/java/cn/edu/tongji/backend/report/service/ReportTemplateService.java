package cn.edu.tongji.backend.report.service;

import cn.edu.tongji.backend.report.mapper.ReportTemplateMapper;
import cn.edu.tongji.backend.report.pojo.ReportTemplate;
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

    public int selectReportTemplateCount(int l_id){
        return reportTemplateMapper.selectTemplateCount(l_id);
    }
}
