package cn.edu.tongji.backend.bulletin.mapper;

import cn.edu.tongji.backend.bulletin.pojo.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface TodoMapper {
    @Select("select * from todo")
    public List<Todo> selectAllTodo();

    @Select("select * from todo where c_id=#{c_id}")
    public List<Todo> selectTodoByCid(int c_id);

    @Insert("insert into todo(todo_id,c_id, t_id, name, `desc`, start_time, end_time,type) values(null,#{c_id}, #{t_id}, #{name}, #{desc}, #{start_time}, #{end_time},#{type})")
    public void insertTodo(Todo todo);

    @Select("select * from todo where " +
            "end_time>=#{start_time} and " +
            "end_time<=#{end_time} and " +
            "c_id in (select c_id from takes where s_id =#{s_id})")
    public List<Todo> selectStudentTodoByMonth(String s_id,Timestamp start_time,Timestamp end_time);

    @Select("select * from todo where t_id=#{t_id} and end_time>=#{start_time} and end_time<=#{end_time}")
    public List<Todo> selectTeacherTodoByMonth(String t_id,Timestamp start_time,Timestamp end_time);

}
