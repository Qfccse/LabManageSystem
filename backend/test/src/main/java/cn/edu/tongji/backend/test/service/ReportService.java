package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.ReportMapper;
import cn.edu.tongji.backend.test.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ReportMapper reportMapper;

    public void insertReport(Report report){
        reportMapper.insertIntoReport(report);
    }

    public int getReportId(int l_id, int s_id){
        return reportMapper.selectIdFromReport(l_id, s_id);
    }

    public String[] getReportFormImage(int rf_id){
        return reportMapper.selectImagesFromReportImage(rf_id);
    }
}
