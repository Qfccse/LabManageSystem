package cn.edu.tongji.backend.report.service;

import cn.edu.tongji.backend.report.mapper.ReportMapper;
import cn.edu.tongji.backend.report.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportMapper reportMapper;

    public void insertReport(Report report){
        System.out.println(report);
        reportMapper.insertIntoReport(report);
    }

    public int getReportId(int l_id, String s_id){
        return reportMapper.selectIdFromReport(l_id, s_id);
    }

    public int getReportCount(int l_id, String s_id){
        return reportMapper.selectCountFromReport(l_id, s_id);
    }

    public String[] getReportFormImage(int rf_id){
        return reportMapper.selectImagesFromReportImage(rf_id);
    }

    public void updateReportStatus(Report report){
        reportMapper.updateReport(report);
    }

    public Report selectStuReport(int l_id, String s_id){
        return reportMapper.selectReportById(l_id,s_id);
    }

    public List<Report> selectLabAllReport(int l_id){
        return reportMapper.selectLabReport(l_id);
    }

    public String selectStuName(String s_id){
        return reportMapper.getStuNameByID(s_id);
    }
}
