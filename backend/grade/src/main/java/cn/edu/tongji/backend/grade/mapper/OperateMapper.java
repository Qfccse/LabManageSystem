package cn.edu.tongji.backend.grade.mapper;

import cn.edu.tongji.backend.grade.pojo.Operates;
import cn.edu.tongji.backend.grade.pojo.Report;

import java.sql.Timestamp;
import java.util.List;

public interface OperateMapper {
    void giveScore(int l_id, String s_id);

    Report getReportById(int r_id);

    void updateFeedback(Operates operates);

//    List<Integer> getAttendScores(String s_id, int c_id);


}
