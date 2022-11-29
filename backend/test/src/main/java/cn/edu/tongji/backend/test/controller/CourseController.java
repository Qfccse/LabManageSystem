package cn.edu.tongji.backend.test.controller;


import cn.edu.tongji.backend.test.pojo.Course;
import cn.edu.tongji.backend.test.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
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
    @GetMapping("/selectById")
    public Course queryById(@RequestParam("id") Long id) {
        return courseService.queryById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/insert")
    public void insert(@RequestParam("num") int num) {
        try {
            courseService.insert(num);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            System.out.println(e);
            throw new RuntimeException("失败了");
        }
    }
}

