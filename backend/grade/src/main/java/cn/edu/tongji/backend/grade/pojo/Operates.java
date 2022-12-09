package cn.edu.tongji.backend.grade.pojo;

import lombok.Data;

@Data
public class Operates {
    private int l_id;

    private String s_id;

    private int attend_score;

    private String feedback;

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public int getAttend_score() {
        return attend_score;
    }

    public void setAttend_score(int attend_score) {
        this.attend_score = attend_score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Operate{" +
                "l_id=" + l_id +
                ", s_id='" + s_id + '\'' +
                ", attend_score=" + attend_score +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
