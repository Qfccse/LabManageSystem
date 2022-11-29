package cn.edu.tongji.backend.todo.service;

import cn.edu.tongji.backend.todo.mapper.TodoMapper;
import cn.edu.tongji.backend.todo.pojo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> selectCourseTodo(int c_id){
        return todoMapper.selectCourseTodo(c_id);
    }

    //public Todo selectTodoById(){
    //    return todoMapper.
    //}

    public void insertTodo(Todo todo){
        todoMapper.insertCourseTodo(todo);
    }

    public void updateTodoInfo(Todo todo){
        todoMapper.updateTodo(todo);
    }

    public void deleteTodo(int todo_id){
        todoMapper.deleteTodo(todo_id);
    }

}
