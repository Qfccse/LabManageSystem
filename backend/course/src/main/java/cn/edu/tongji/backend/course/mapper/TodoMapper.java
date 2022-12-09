package cn.edu.tongji.backend.course.mapper;

import cn.edu.tongji.backend.course.pojo.Todo;
import cn.edu.tongji.backend.course.pojo.info.Bulletin;

import java.util.List;

public interface TodoMapper {
    public List<Bulletin> getAllTodo();

    public List<Bulletin> getTodoByCid(int c_id);

    public void addTodo(Todo todo);

}
