package cn.edu.tongji.backend.grade.controller;


import cn.edu.tongji.backend.grade.pojo.Laboratory;
import cn.edu.tongji.backend.grade.pojo.Mark;
import cn.edu.tongji.backend.grade.pojo.Operates;
import cn.edu.tongji.backend.grade.pojo.info.CourseGrade;
import cn.edu.tongji.backend.grade.pojo.info.NewMark;
import cn.edu.tongji.backend.grade.pojo.tools.Message;
import cn.edu.tongji.backend.grade.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    //教师批阅报告并打分
    @PostMapping("/markReport")
    public ResponseEntity<HashMap<String, Object>> markReport(@RequestBody NewMark newMark) {
        Message message = gradeService.addMark(newMark);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @PostMapping("/getComment")
    public ResponseEntity<HashMap<String, Object>> getComment(@RequestBody NewMark newMark) {
        Message message = gradeService.getMark(newMark);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }



    /**
     * 分配考勤分数  当学生提交报告时 调用  判断submit_time和end_time
     * @param
     * @return
     */
    @PostMapping("/getAttendScore")
    public ResponseEntity<HashMap<String, Object>> getAttendScore(@RequestBody HashMap<String, String> map) {
        Message message = gradeService.getAttendScore(map);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @PostMapping("/giveFeedback")
    public ResponseEntity<HashMap<String, Object>> giveFeedback(@RequestBody Operates operates) {
        Message message = gradeService.giveFeedback(operates);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    /**
     * 校验在前端   保证每门课都有权重 并且总和为100
     * @return
     */
    @PostMapping("/calcWeightedGrade")
    public ResponseEntity<HashMap<String, Object>> calcWeightedGrade(@RequestBody List<Laboratory> labs) {
        Message message = gradeService.weightLabs(labs);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getSingleGrade/{s_id}/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getSingleStudentGrade(@PathVariable String s_id, @PathVariable int c_id) {
        CourseGrade courseGrade = gradeService.getStudentGrade(s_id, c_id);
        Message message = new Message();
        message.set("grade", courseGrade);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getAllGrades/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getAllGrades(@PathVariable int c_id) {
        Message message = gradeService.getCourseGrades(c_id);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

}
