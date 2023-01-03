package cn.edu.tongji.backend.bulletin.service;

import cn.edu.tongji.backend.bulletin.mapper.TodoMapper;
import cn.edu.tongji.backend.bulletin.pojo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> selectAllTodo(){
        return todoMapper.selectAllTodo();
    }

    public List<Todo> selectCourseTodo(int c_id){
        return todoMapper.selectTodoByCid(c_id);
    }

    public void insertIntoTodo(Todo todo){
        todoMapper.insertTodo(todo);
    }

    public List<Todo> selectStudentMonthTodo(String s_id,Timestamp start_time, Timestamp end_time){
        return todoMapper.selectStudentTodoByMonth(s_id, start_time,end_time);
    }

    public List<Todo> selectTeacherMonthTodo(String t_id,Timestamp start_time, Timestamp end_time) {
        return todoMapper.selectTeacherTodoByMonth(t_id,start_time, end_time);
    }

    public void insertTodo(Todo todo){
        todoMapper.insertTodo(todo);
    }
}
