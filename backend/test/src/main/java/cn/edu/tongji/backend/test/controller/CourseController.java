package cn.edu.tongji.backend.test.controller;


import cn.edu.tongji.backend.test.pojo.Course;
import cn.edu.tongji.backend.test.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/test")
//@RefreshScope
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public Course queryById(@PathVariable("id") Long id) {
        return courseService.queryById(id);
    }
}

