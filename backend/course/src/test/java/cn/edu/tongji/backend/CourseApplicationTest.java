package cn.edu.tongji.backend;

import cn.edu.tongji.backend.course.CourseApplication;
import cn.edu.tongji.backend.course.mapper.*;
import cn.edu.tongji.backend.course.pojo.Course;
import cn.edu.tongji.backend.course.pojo.Laboratory;
import cn.edu.tongji.backend.course.pojo.Teaches;
import cn.edu.tongji.backend.course.pojo.Todo;
import cn.edu.tongji.backend.course.pojo.info.Bulletin;
import cn.edu.tongji.backend.course.pojo.info.CourseFeedBack;
import cn.edu.tongji.backend.course.pojo.info.StudentForCourse;
import cn.edu.tongji.backend.course.pojo.info.TeacherForCourse;
import cn.edu.tongji.backend.course.service.CourseService;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.Temperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApplication.class)
public class CourseApplicationTest {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeachesMapper teachesMapper;

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Autowired
    private TakesMapper takesMapper;

    @Autowired
    private TodoMapper todoMapper;



    @Autowired
    private CourseService courseService;

    @Test
    public void testSelectByCondition() {
        int status = 0;
        String name = "计算机";

        //接收参数后要处理一下
        name = "%" + name + "%";

        Course course = new Course();
        course.setStatus(status);
        course.setName(name);

        Map map = new HashMap();
//        map.put("name", name);
//        map.put("status", status);

//        System.out.println(courseMapper.getByCondition(status, name));
        System.out.println(courseMapper.getByCondition(map));
    }

    @Test
    public void testAdd() {
        String name = "高级程序语言设计";
        int status = 1;

        Course course = new Course();
        course.setName(name);
        course.setStatus(status);

        courseMapper.addCourse(course);

        int id = course.getC_id();
        System.out.println(id);
    }

    @Test
    public void testUpdate() {
        int id = 1;
        String name = "离散数学";
        int status = 1;

        Course course = new Course();
        course.setC_id(id);
        //course.setName(name);
        course.setStatus(status);

        int num = courseMapper.updateCourse(course);

        System.out.println(num);
    }


    @Test
    public void testDelete() {
//        int id = 6;
//        courseMapper.deleteById(id);

        int[] ids = new int[] {1,2,3,4,5};
        courseMapper.deleteByIds(ids);
    }

    @Test
     public void testGetCourses() {
        System.out.println(courseMapper.getAllCoursesAsTeacher("04354"));
    }

    @Test
    public void testGetLabs() {
       List<CourseFeedBack> list1 = laboratoryMapper.getFeedbackAsStudent(420203101, "2053677");
       List<CourseFeedBack> list2 = laboratoryMapper.getFeedbackAsTeacher(420203101);

        System.out.println();
    }

    @Test
    public void testBulletin() {
        List< Bulletin> list1 = todoMapper.getAllTodo();
        List< Bulletin> list2 = todoMapper.getTodoByCid(420203101);

        System.out.println();
    }

    @Test
    public void testAddTodo() {
        courseService.addTodo(new Todo());
    }


}