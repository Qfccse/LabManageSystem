package cn.edu.tongji.backend.todo.mapper;

import cn.edu.tongji.backend.todo.pojo.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TodoMapper {

    @Select("select * from todo where c_id=#{c_id}")
    List<Todo> selectCourseTodo(int c_id);

    @Insert("insert into todo values (null,#{name},#{desc},#{start_time},#{end_time},#{c_id},#{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "todo_id", keyColumn = "todo_id")
    void insertCourseTodo(Todo todo);

    @Delete("delete from todo where todo_id=#{todo_id}")
    void deleteTodo(int todo_id);

    @Update("update todo set name=#{name},desc=#{desc},start_time=#{start_time}," +
            "end_time=#{end_time},create_time=#{create_time} where todo_id=#{todo_id}")
    void updateTodo(Todo todo);
}
