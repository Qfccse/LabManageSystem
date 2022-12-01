package cn.edu.tongji.backend.admin.pojo;

import lombok.Data;

@Data
public class User {
    private String u_id;
    private String name;
    private String password;
    private int status;
    private int role;
    private String email;

    private String cidList;

    public void setAttributeByIndex(int index,String content){
        switch (index){
            case 0:
                this.u_id = content;
                break;
            case 1:
                this.name = content;
                break;
            case 2:
                this.password = content;
                break;
            case 3:
                this.status = Integer.parseInt(content);
                break;
            case 4:
                this.role = Integer.parseInt(content);
                break;
            case 5:
                this.email = content;
                break;
            case 6:
                if(content.contains("\"")){
                    content = content.split("\"")[1];
                }
                this.cidList = content;
                break;
            default:
                System.out.println("多余的列");
                break;
        }
    }
}
