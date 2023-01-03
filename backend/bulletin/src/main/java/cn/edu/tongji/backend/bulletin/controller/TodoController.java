package cn.edu.tongji.backend.bulletin.controller;


import cn.edu.tongji.backend.bulletin.pojo.Calendar;
import cn.edu.tongji.backend.bulletin.pojo.Todo;
import cn.edu.tongji.backend.bulletin.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/bulletin")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/getStudentCalendar")
    public List<Todo> getStudentCalendar(@RequestBody Calendar calendar){
        System.out.println(calendar);
        return todoService.selectStudentMonthTodo(calendar.getU_id(),calendar.getStart_time(),calendar.getEnd_time());
    }

    @PostMapping("/getTeacherCalendar")
    public List<Todo> getTeacherCalendar(@RequestBody Calendar calendar){
        return todoService.selectTeacherMonthTodo(calendar.getU_id(),calendar.getStart_time(),calendar.getEnd_time());
    }

    @PostMapping("/addBulletin")
    public void addBulletin(@RequestBody Todo todo){
        System.out.println(todo);
        todoService.insertTodo(todo);
    }
}
