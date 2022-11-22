package cn.edu.tongji.backend.test.mapper;

import cn.edu.tongji.backend.test.pojo.FileUploader;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface FileUpLoaderMapper {

    @Insert("insert into test VALUE(null,#{name},#{path})")
    void uploadFile(String name, String path);

    @Select("select * from test where id=#{id}")
    FileUploader getFile(Long id);
}
