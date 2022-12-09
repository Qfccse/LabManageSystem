package cn.edu.tongji.backend.grade;

import cn.edu.tongji.backend.grade.mapper.OperateMapper;
import cn.edu.tongji.backend.grade.pojo.Mark;
import cn.edu.tongji.backend.grade.pojo.Takes;
import cn.edu.tongji.backend.grade.service.GradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GradeApplication.class)
public class GradeApplicationTest {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private OperateMapper operateMapper;

    @Test
    public void testAddTakes() {
        Mark mark = new Mark();

        mark.setT_id("18239");
        mark.setR_id(3);
        mark.setScore(100);
        mark.setComment("非常好");

        gradeService.addMark(mark);
    }





}