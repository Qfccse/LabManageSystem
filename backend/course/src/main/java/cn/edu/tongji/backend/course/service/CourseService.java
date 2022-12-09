package cn.edu.tongji.backend.course.service;

import cn.edu.tongji.backend.course.mapper.*;
import cn.edu.tongji.backend.course.pojo.Course;
import cn.edu.tongji.backend.course.pojo.Laboratory;
import cn.edu.tongji.backend.course.pojo.Teaches;
import cn.edu.tongji.backend.course.pojo.Todo;
import cn.edu.tongji.backend.course.pojo.info.CourseAndRole;
import cn.edu.tongji.backend.course.pojo.tools.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

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

//    public List<Course> getCourses() {
//        return courseMapper.getAllCourses();
//    }

    public Message getAllCourses() {
        Message message = new Message();
        message.set("coursesList", courseMapper.getAllCourses());
        return message;
    }

    public Message addCourse(Course course, Teaches teaches) {
        courseMapper.addCourse(course);
        teaches.setC_id(course.getC_id());
        teaches.setRole("1");
        teachesMapper.addTeaches(teaches);

        Message message = new Message();
        message.set("course", course);
        message.set("teaches", teaches);
        return message;
    }

    //查询课程
    public Message getCoursesAsTeacher(String id) {
        Message message = new Message();
        List<CourseAndRole> coursesAsHT = new ArrayList<>();
        List<CourseAndRole> coursesAsTeacher = new ArrayList<>();

        for (CourseAndRole c : courseMapper.getAllCoursesAsTeacher(id)) {
            if (c.getRole().equals("1")) {
                coursesAsHT.add(c);
            }
            else {
                coursesAsTeacher.add(c);
            }
        }

        message.set("CoursesAsHeadTeacher", coursesAsHT);
        message.set("CoursesAsTeacher", coursesAsTeacher);

        return message;
    }
    public Message getCoursesAsStudent(String id) {
        Message message = new Message();
        List<CourseAndRole> coursesAsStudent = new ArrayList<>();
        List<CourseAndRole> coursesAsTA = new ArrayList<>();

        for (CourseAndRole c : courseMapper.getAllCoursesAsStudent(id)) {
            if (c.getRole().equals("4")) {
                coursesAsStudent.add(c);
            }
            else {
                coursesAsTA.add(c);
            }
        }

        message.set("CoursesAsStudent", coursesAsStudent);
        message.set("CoursesAsTA", coursesAsTA);

        return message;
    }

    public Course updateCourse(Course course) {
        courseMapper.updateCourse(course);
        return course;
    }

    public Laboratory updateLab(Laboratory laboratory) {
        laboratoryMapper.updateLab(laboratory);
        return laboratory;
    }

    public boolean deleteCourse(int id) {
        Course course = courseMapper.getById(id);
        if (course.getStatus() == 2) {
            courseMapper.deleteById(id);
            return true;
        }

        return false;
    }

    public Course getCourseById(int c_id) {
        return courseMapper.getById(c_id);
    }


    public Message getLabsByCid(int c_id) {
        Message message = new Message();
        message.set("labs", laboratoryMapper.getLabsByCid(c_id));
        return message;
    }

    public Message getFacultyByCid(int c_id) {
        Message message = new Message();
        message.set("teachers", teachesMapper.getTeachersByCid(c_id));
        message.set("students", takesMapper.getStudentsByCid(c_id));

        return message;
    }


    public Message getFeedbackAsTeacher(int c_id) {
        Message message = new Message();
        message.set("feedbacks", laboratoryMapper.getFeedbackAsTeacher(c_id));
        return message;
    }

    public Message getFeedbackAsStudent(int c_id, String s_id) {
        Message message = new Message();
        message.set("feedbacks", laboratoryMapper.getFeedbackAsStudent(c_id, s_id));
        return message;
    }

    public Message getAllTodo() {
        Message message = new Message();

        message.set("todos", todoMapper.getAllTodo());
        return message;
    }

    public Message getTodoByCid(int c_id) {
        Message message = new Message();
        message.set("todos", todoMapper.getTodoByCid(c_id));
        return message;
    }

    public Message addTodo(Todo todo) {
        Timestamp curtime = new Timestamp(System.currentTimeMillis());
//        System.out.println(time1);

        todo.setEnd_time(curtime);
        todo.setStart_time(curtime);

        todoMapper.addTodo(todo);
        return new Message();
    }



}
