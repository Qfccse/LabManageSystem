package cn.edu.tongji.backend.test.mapper;

import cn.edu.tongji.backend.test.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface CourseMapper {

    @Select("select * from test where id=#{id}")
    Course selectById(Long id) ;

    @Insert("insert into test2 values (null,#{id})")
    void insertTest(int id);
}
