package cn.edu.tongji.backend.guidebook.mapper;

import cn.edu.tongji.backend.guidebook.pojo.Guidebook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GuidebookMapper {
    @Insert("insert into guidebook values(null,#{l_id},#{t_id},#{type},#{name},#{path})")
    void insertIntoGuideBook(Guidebook guidebook);

    @Select("select * from guidebook where g_id=#{g_id}")
    Guidebook selectByGID(int g_id);

    @Select("select * from guidebook where l_id=#{l_id}")
    List<Guidebook> selectByLID(int l_id);

    @Select("select * from guidebook where g_id=#{t_id}")
    List<Guidebook> selectByTID(int t_id);

    @Delete("delete from guidebook where g_id=#{g_id}")
    void deleteGuidebook(int g_id);
}
