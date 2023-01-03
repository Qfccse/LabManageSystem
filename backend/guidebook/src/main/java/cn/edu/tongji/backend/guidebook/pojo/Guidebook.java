package cn.edu.tongji.backend.guidebook.pojo;

import lombok.Data;

@Data
public class Guidebook {
    private int g_id;
    private int l_id;
    private String t_id;
    private int type;
    private String name;
    private String path;
    private String l_name;
    private String t_name;
}
