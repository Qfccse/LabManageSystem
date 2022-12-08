package cn.edu.tongji.backend.report.service;

import cn.edu.tongji.backend.report.mapper.ReportFormMapper;
import cn.edu.tongji.backend.report.pojo.ReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportFormService {
    @Autowired
    private ReportFormMapper reportFormMapper;

    public void insertReportForm(ReportForm reportForm) {
        reportFormMapper.insertIntoReportForm(reportForm);
    }

    public void updateReportForm(ReportForm reportForm){
        reportFormMapper.updateReportForm(reportForm);
    }
    public List<ReportForm> selectLabReportForm(int r_id){
        return reportFormMapper.getLabReportForm(r_id);
    }

    public List<String> selectLabReportImages(int rf_id){
        return reportFormMapper.getReportImages(rf_id);
    }

    public void insertImage(int rf_id,String path,String file_name){
        reportFormMapper.insertIntoReportImages(rf_id,path,file_name);
    }

    public int checkFormExist(int r_id, int fr_id){
        return reportFormMapper.selectCountReportFormCheck(r_id, fr_id);
    }

    public int selectRfTd(int r_id, int fr_id){
        return reportFormMapper.selectRfId(r_id, fr_id);
    }
}
