package cn.edu.tongji.backend.grade.pojo;

import lombok.Data;

@Data
public class Mark {
    private int r_id;

    private String t_id;

    private int score;

    private String comment;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "r_id=" + r_id +
                ", t_id='" + t_id + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
