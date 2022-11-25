package cn.edu.tongji.backend.test.mapper;

import cn.edu.tongji.backend.test.pojo.ReportForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportFormMapper {

    @Insert("insert into report_form VALUES(null,#{l_id},#{s_id},#{icon},#{label},#{title},#{placeholder},#{required},#{type},#{typeName},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "rf_id", keyColumn = "rf_id")
    void insertIntoReportForm(ReportForm reportForm);

    @Select("select * from report_form where l_id = #{l_id} and s_id=#{s_id}")
    List<ReportForm> getLabReportForm(int l_id, int s_id);

    @Select("select path from report_images where rf_id = #{rf_id}")
    List<String> getReportImages(int rf_id);

    @Insert("insert into report_images values (#{rf_id},#{path},#{file_name})")
    void insertIntoReportImages(int rf_id,String path,String file_name);
}
