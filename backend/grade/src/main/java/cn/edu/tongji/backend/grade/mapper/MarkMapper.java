package cn.edu.tongji.backend.grade.mapper;

import cn.edu.tongji.backend.grade.pojo.Mark;
import cn.edu.tongji.backend.grade.pojo.info.LabScore;

import java.util.List;

public interface MarkMapper {

    void addMarks(Mark mark);

    List<LabScore> getLabScores(String s_id, int c_id);

    String getStudentName(String s_id);
}
