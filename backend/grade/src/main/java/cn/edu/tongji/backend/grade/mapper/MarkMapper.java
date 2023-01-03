package cn.edu.tongji.backend.grade.mapper;

import cn.edu.tongji.backend.grade.pojo.Mark;
import cn.edu.tongji.backend.grade.pojo.info.LabScore;
import cn.edu.tongji.backend.grade.pojo.info.NewMark;

import java.util.List;

public interface MarkMapper {

    void addMarks(Mark mark);

    Mark getMark(int r_id);

    List<LabScore> getLabScores(String s_id, int c_id);

    String getStudentName(String s_id);

    int getRid(NewMark newMark);

    void changeReportStatus(int r_id);

    int getReportStatus(int r_id);
}
