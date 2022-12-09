package cn.edu.tongji.backend.course.mapper;

import cn.edu.tongji.backend.course.pojo.Laboratory;
import cn.edu.tongji.backend.course.pojo.info.CourseFeedBack;

import java.util.List;

public interface LaboratoryMapper {
    List<Laboratory> getLabsByCid(int c_id);

    List<CourseFeedBack> getFeedbackAsTeacher(int c_id);

    List<CourseFeedBack> getFeedbackAsStudent(int c_id, String s_id);

    void updateLab(Laboratory laboratory);
}
