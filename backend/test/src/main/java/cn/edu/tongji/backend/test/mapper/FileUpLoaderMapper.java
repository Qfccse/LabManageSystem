package cn.edu.tongji.backend.test.mapper;

import org.apache.ibatis.annotations.Insert;

public interface FileUpLoaderMapper {

    @Insert("insert into test VALUE(null,#{name},#{path})")
    void uploadImage(String name, String path);

    void uploadFile();
}
