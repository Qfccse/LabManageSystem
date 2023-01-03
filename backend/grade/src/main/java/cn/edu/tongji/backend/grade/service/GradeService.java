package cn.edu.tongji.backend.grade.service;

import cn.edu.tongji.backend.grade.mapper.LaboratoryMapper;
import cn.edu.tongji.backend.grade.mapper.MarkMapper;
import cn.edu.tongji.backend.grade.mapper.OperateMapper;
import cn.edu.tongji.backend.grade.mapper.TakesMapper;
import cn.edu.tongji.backend.grade.pojo.*;
import cn.edu.tongji.backend.grade.pojo.info.CourseGrade;
import cn.edu.tongji.backend.grade.pojo.info.NewMark;
import cn.edu.tongji.backend.grade.pojo.tools.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private MarkMapper markMapper;

    @Autowired
    private OperateMapper operateMapper;

    @Autowired
    private LaboratoryMapper labMapper;

    @Autowired
    private TakesMapper takesMapper;



    public Message getAttendScore(HashMap<String, String> map) {
        int r_id = Integer.parseInt(map.get("r_id"));
        Timestamp submitTime = new Timestamp(Long.parseLong(map.get("submit_time")));

        Message message = new Message();
        message.set("gotScore", "0");

        //根据r_id查询报告相关信息  以及实验截止时间
        Report report = operateMapper.getReportById(r_id);
        Timestamp endTime = labMapper.getLabByID(report.getL_id()).getEnd_time();

        if (submitTime.before(endTime)) {
            operateMapper.giveScore(report.getL_id(), report.getS_id());
            message.set("gotScore", "1");
        }

        return message;
    }

    public Message giveFeedback(Operates operates) {
        Message message = new Message();
        operateMapper.updateFeedback(operates);
        return message;
    }

    public Message weightLabs(List<Laboratory> labs) {
        Message message = new Message();
        //先为实验分派权重
        for ( Laboratory lab : labs) {
            labMapper.setProportion(lab);
        }
//        获取所有选课学生学号
        Laboratory lab = labMapper.getLabByID(labs.get(0).getL_id());
        List<Takes> takes = takesMapper.getStudentsByCourseID(lab.getC_id());

//        //这里模拟一下
//        Takes testtake = new Takes();
//        testtake.setC_id(420203106);
//        testtake.setS_id("2053677");
//        List<Takes> takes = new ArrayList<>();
//        takes.add(testtake);

        /***** 计算所有选了该课的学生的成绩 ****/
        List<CourseGrade> courseGrades = new ArrayList<>();

        for (Takes take : takes) {
            CourseGrade courseGrade = new CourseGrade(take);
            courseGrade.setName(markMapper.getStudentName(take.getS_id()));
            courseGrade.setLabScores(markMapper.getLabScores(courseGrade.getS_id(), courseGrade.getC_id()));
            courseGrade.calcWeightedGrade();
            courseGrades.add(courseGrade);
        }

        message.set("grades", courseGrades);
        return message;
    }


    // 获得一个学生的某门课程成绩
    public CourseGrade getStudentGrade(String s_id, int c_id) {
        CourseGrade courseGrade = new CourseGrade(s_id, c_id);
        courseGrade.setLabScores(markMapper.getLabScores(s_id, c_id));
        return courseGrade;
    }


    //获取一门课的所有学生的这门课程的成绩
    public Message getCourseGrades(int c_id) {
        Message message = new Message();
        List<Takes> takes = takesMapper.getStudentsByCourseID(c_id);

        List<CourseGrade> grades = new ArrayList<>();

        //对于每一个选课的学生 查询他的成绩
        for (Takes take : takes) {
            CourseGrade courseGrade = getStudentGrade(take.getS_id(), c_id);
            courseGrade.setName(markMapper.getStudentName(take.getS_id()));
            courseGrade.calcWeightedGrade();
            grades.add(courseGrade);
        }

        message.set("grades", grades);

        return message;
    }

    public Message addMark(NewMark newMark) {
        Message message = new Message();
        int r_id = markMapper.getRid(newMark);

        Mark mark = new Mark();
        mark.setR_id(r_id);
        mark.setComment(newMark.getComment());
        mark.setScore(newMark.getScore());
        mark.setT_id(newMark.getT_id());
        markMapper.addMarks(mark);

        markMapper.changeReportStatus(r_id);
        message.set("added", 1);
        return message;
    }

    public Message getMark(NewMark newMark) {
        Message message = new Message();
        int r_id = markMapper.getRid(newMark);

        //检查一下装填  如果已批改 就返回
        int status = markMapper.getReportStatus(r_id);

        if(status == 3) {
            Mark mark = markMapper.getMark(r_id);

            message.set("mark", mark);
            message.set("marked", 1);
        }
        else
        {
            message.set("marked", 0);
        }

        return message;
    }
}
