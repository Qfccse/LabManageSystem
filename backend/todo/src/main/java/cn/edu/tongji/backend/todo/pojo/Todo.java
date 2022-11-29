package cn.edu.tongji.backend.todo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Todo {
    private int todo_id;
    private String name;
    private String desc;
    private Timestamp start_time;
    private Timestamp end_time;
    private int c_id;
    private Timestamp create_time;
}
