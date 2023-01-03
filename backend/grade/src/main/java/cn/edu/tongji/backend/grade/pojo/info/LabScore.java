package cn.edu.tongji.backend.grade.pojo.info;

public class LabScore {
    private int l_id;

    private String name;

    private int score;

    private int attend_score;

    private int proportion;

    private String comment;

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttend_score() {
        return attend_score;
    }

    public void setAttend_score(int attend_score) {
        this.attend_score = attend_score;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
