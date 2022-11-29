package cn.edu.tongji.backend.todo.controller;

import cn.edu.tongji.backend.todo.pojo.Todo;
import cn.edu.tongji.backend.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    public TodoService todoService;

    @GetMapping("/getCourseTodo")
    public List<Todo> getCourseTodo(@RequestParam("c_id") int c_id){
        return todoService.selectCourseTodo(c_id);
    }


}
