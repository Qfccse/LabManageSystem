package cn.edu.tongji.backend.laboratory.service;

import cn.edu.tongji.backend.laboratory.mapper.LaboratoryMapper;
import cn.edu.tongji.backend.laboratory.pojo.Feedback;
import cn.edu.tongji.backend.laboratory.pojo.Laboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    public List<Laboratory> getCourseLabs(int c_id){
        return laboratoryMapper.selectCourseLabs(c_id);
    }

    public Laboratory getLabById(int l_id){
        return laboratoryMapper.selectLabByID(l_id);
    }

    public void addLab(Laboratory laboratory){
        laboratoryMapper.insertIntoLab(laboratory);
    }

    public void updateLab(Laboratory laboratory){
        laboratoryMapper.updateLab(laboratory);
    }

    public void removeLab(int l_id){
        laboratoryMapper.deleteFromLab(l_id);
    }

    public void createFeedback(Feedback feedback){
        laboratoryMapper.insertFeedback(feedback);
    }
}
