package cn.edu.tongji.backend.course.pojo.tools;

import java.util.HashMap;

public class Message {
    private HashMap<String, Object> map;

    public Message() {
        this.map = new HashMap<>();
        map.put("status", STATUS.OK);
    }

    public void set(String str, Object obj) {
        map.put(str, obj);
    }

    public HashMap<String, Object> getMap() {
        return this.map;
    }
}
