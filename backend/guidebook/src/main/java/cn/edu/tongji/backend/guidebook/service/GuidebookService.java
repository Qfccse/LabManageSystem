package cn.edu.tongji.backend.guidebook.service;

import cn.edu.tongji.backend.guidebook.mapper.GuidebookMapper;
import cn.edu.tongji.backend.guidebook.pojo.Guidebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuidebookService {
    @Autowired
    GuidebookMapper guidebookMapper;

    public void addGuidebook(Guidebook guidebook){
        guidebookMapper.insertIntoGuideBook(guidebook);
    }

    public Guidebook findGuidebook(int g_id){
        return guidebookMapper.selectByGID(g_id);
    }

    public List<Guidebook> findAllGuidebook(int l_id){
        return guidebookMapper.selectByLID(l_id);
    }

    public void deleteGuidebook(int g_id){
        guidebookMapper.deleteGuidebook(g_id);
    }

    public List<Guidebook> findCourseGuidebook(int c_id){
        return  guidebookMapper.selectCourseGuidebook(c_id);
    }

    public void renewGuidebookName(int g_id,String name){
        guidebookMapper.updateGuidebookName(g_id,name);
    }
}
