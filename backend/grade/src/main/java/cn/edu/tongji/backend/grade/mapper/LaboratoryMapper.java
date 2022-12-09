package cn.edu.tongji.backend.grade.mapper;

import cn.edu.tongji.backend.grade.pojo.Laboratory;

import java.util.List;

public interface LaboratoryMapper {
    Laboratory getLabByID(int l_id);

    void setProportion(Laboratory lab);

}
