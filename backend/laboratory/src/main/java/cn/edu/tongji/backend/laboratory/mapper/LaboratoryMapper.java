package cn.edu.tongji.backend.laboratory.mapper;

import cn.edu.tongji.backend.laboratory.pojo.Feedback;
import cn.edu.tongji.backend.laboratory.pojo.Laboratory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LaboratoryMapper {
    @Select("select * from laboratory where c_id=#{c_id}")
    List<Laboratory> selectCourseLabs(int c_id);

    @Select("select * from laboratory where l_id=#{l_id}")
    Laboratory selectLabByID(int l_id);

    @Insert("insert into laboratory values(null,#{c_id},#{name},#{start_time},#{end_time},#{desc},#{proportion})")
    @Options(useGeneratedKeys = true, keyProperty = "l_id", keyColumn = "l_id")
    void insertIntoLab(Laboratory laboratory);

    @Update("update laboratory set c_id=#{c_id},laboratory.name=#{name},start_time=#{start_time}," +
            " end_time=#{end_time},laboratory.desc=#{desc},proportion=#{proportion} where l_id=#{l_id}")
    void updateLab(Laboratory laboratory);

    @Delete("delete from laboratory where l_id=#{l_id}")
    void deleteFromLab(int l_id);

    @Update("update operates set feedback=#{feedback} where l_id=#{l_id} and s_id=#{s_id}")
    void insertFeedback(Feedback feedback);

    @Select("select * from laboratory where l_id in (select l_id from takes where s_id =#{s_id})")
    List<Laboratory> selectStuLab(String s_id);
}
