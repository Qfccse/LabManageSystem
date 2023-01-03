package cn.edu.tongji.backend.bulletin.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Calendar {
   private String u_id;
   private Timestamp start_time;
   private Timestamp end_time;
}
