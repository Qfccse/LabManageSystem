package cn.edu.tongji.backend.course.controller;

import cn.edu.tongji.backend.course.pojo.Course;
import cn.edu.tongji.backend.course.pojo.Laboratory;
import cn.edu.tongji.backend.course.pojo.Teaches;
import cn.edu.tongji.backend.course.pojo.Todo;
import cn.edu.tongji.backend.course.pojo.tools.Message;
import cn.edu.tongji.backend.course.pojo.tools.STATUS;
import cn.edu.tongji.backend.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<HashMap<String, Object>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses().getMap(), HttpStatus.OK);
    }


    /**
     * 根据角色和id来获取属于该用户的所有课程
     * @param id
     * @param role
     * @return
     *               query方式  @RequestParam("id") String id, @RequestParam("role") String role
     */
    @GetMapping("/getAllCoursesById/{role}/{id}")
    public ResponseEntity<HashMap<String, Object>> getCourses(@PathVariable String id, @PathVariable String role) {
        if (role.equals("teacher")) {
            return new ResponseEntity<>(courseService.getCoursesAsTeacher(id).getMap(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(courseService.getCoursesAsStudent(id).getMap(), HttpStatus.OK);
        }
    }



    //添加一门课程
    @PostMapping
    public ResponseEntity<HashMap<String, Object>> addCourse(@RequestBody Map<String, String> params) {
        Course course = new Course();
        course.setName(params.get("name"));
        course.setStatus(Integer.parseInt(params.get("status")));
        course.setDesc(params.get("desc"));
        Teaches teaches = new Teaches();
        teaches.setT_id(params.get("t_id"));
        Message message = courseService.addCourse(course, teaches);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    class Param {
        public Course course;
        public Teaches teaches;
    }


    //更改课程 名字 描述 状态
    @PutMapping
    public ResponseEntity<HashMap<String, Object>> updateCourse(@RequestBody Course course){
        Message message = new Message();
        message.set("course", courseService.updateCourse(course));
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    //修改课程描述
    @PostMapping("/labModify")
    public ResponseEntity<HashMap<String, Object>> updateLab(@RequestBody Laboratory laboratory){
        Message message = new Message();
        message.set("course", courseService.updateLab(laboratory));
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    /**
     * 教师无法删除课程  保留接口
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteCourse(@PathVariable int id) {
        Message message = new Message();
        Boolean deleted = courseService.deleteCourse(id);
        message.set("deleted", deleted);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getCourseById/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getCourseById(@PathVariable int c_id) {
        Message message = new Message();
        Course course = courseService.getCourseById(c_id);
        if (course == null) {
            message.set("status", STATUS.BADPARAM);
        }
        else{
            message.set("course", course);
        }
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getLabsById/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getLabsByCid(@PathVariable int c_id) {
        Message message;

        if ( c_id > 420203100) {
            message = courseService.getLabsByCid(c_id);
        }
        else {
            message = new Message();
            message.set("status", STATUS.BADPARAM);
        }

        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getCourseFaculty/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getFacultyById(@PathVariable int c_id) {
        Message message;

        if ( c_id > 420203100) {
            message = courseService.getFacultyByCid(c_id);
        }
        else {
            message = new Message();
            message.set("status", STATUS.BADPARAM);
        }

        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getFeedbackAsTeacher/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getFeedbackAsTeacher(@PathVariable int c_id) {
        Message message = courseService.getFeedbackAsTeacher(c_id);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getFeedbackAsStudent/{c_id}/{s_id}")
    public ResponseEntity<HashMap<String, Object>> getFeedbackAsStudent(@PathVariable int c_id, @PathVariable String s_id) {
        Message message = courseService.getFeedbackAsStudent(c_id, s_id);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @GetMapping("/getTodo/{c_id}")
    public ResponseEntity<HashMap<String, Object>> getFeedbackAsStudent(@PathVariable int c_id) {
        Message message;
        if (c_id < 420203100) {
            message = courseService.getAllTodo();
        }
        else {
            message = courseService.getTodoByCid(c_id);
        }
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }

    @PostMapping("/addBulletin")
    public ResponseEntity<HashMap<String, Object>> addBulletin(@RequestBody Todo todo) {
        Message message = courseService.addTodo(todo);
        return new ResponseEntity<>(message.getMap(), HttpStatus.OK);
    }
}
