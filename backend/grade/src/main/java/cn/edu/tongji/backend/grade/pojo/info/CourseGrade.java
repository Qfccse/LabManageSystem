package cn.edu.tongji.backend.grade.pojo.info;

import cn.edu.tongji.backend.grade.pojo.Takes;
import lombok.Data;

import java.util.List;

@Data
public class CourseGrade {
    private String s_id;

    private String name;

    private int c_id;

    private List<LabScore> labScores;

    private float attend_score = -1;
    private float lab_score = -1;
    private float final_grade = -1;

    public CourseGrade(String s_id, int c_id) {
        this.s_id = s_id;
        this.c_id = c_id;
    }

    public CourseGrade(Takes take) {
        c_id = take.getC_id();
        s_id = take.getS_id();
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public float getAttend_score() {
        return attend_score;
    }

    public void setAttend_score(float attend_score) {
        this.attend_score = attend_score;
    }

    public float getLab_score() {
        return lab_score;
    }

    public void setLab_score(float lab_score) {
        this.lab_score = lab_score;
    }

    public float getFinal_grade() {
        return final_grade;
    }

    public void setFinal_grade(float final_grade) {
        this.final_grade = final_grade;
    }

    public List<LabScore> getLabScores() {
        return labScores;
    }

    public void setLabScores(List<LabScore> labScores) {
        this.labScores = labScores;
    }

    //计算加权总分
    public void calcWeightedGrade() {
        for (LabScore lab : labScores) {
            attend_score += lab.getAttend_score();
            lab_score += lab.getScore() * lab.getProportion();
        }

        lab_score = lab_score / 100f;
        lab_score = Math.round(lab_score * 100) / 100f;
        final_grade = (attend_score / labScores.size()) * 100f * 0.1f + lab_score * 0.9f;
        final_grade = Math.round(final_grade * 100) / 100f;
    }

}
