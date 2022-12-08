package cn.edu.tongji.backend.report.mapper;

import cn.edu.tongji.backend.report.pojo.ReportForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportFormMapper {

    @Insert("insert into report_form VALUES(null,#{rt_id},#{r_id},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "rf_id", keyColumn = "rf_id")
    void insertIntoReportForm(ReportForm reportForm);

    @Update("update report_form set content=#{content} where rf_id=#{rf_id}")
    void updateReportForm(ReportForm reportForm);

    //@Select("select * from report_form where " +
    //        "(rf_id = (select rf_id from report_template where l_id=#{l_id})) and" +
    //        "(r_id = (select s_id from report where s_id=#{s_id}))")
    //List<ReportForm> getLabReportForm(int l_id, int s_id);
    @Select("select * from report_form where r_id=#{r_id}")
    List<ReportForm> getLabReportForm(int r_id);

    @Select("select path from report_image where rf_id = #{rf_id}")
    List<String> getReportImages(int rf_id);

    @Insert("insert into report_image values (#{rf_id},#{path},#{file_name})")
    void insertIntoReportImages(int rf_id, String path, String file_name);

    @Select("select rf_id from report_form where r_id=#{r_id} and rt_id=#{rt_id}")
    int selectRfId(int r_id, int rt_id);

    @Select("select count(rf_id) from report_form where r_id=#{r_id} and rt_id=#{rt_id}")
    int selectCountReportFormCheck(int r_id, int rt_id);
}
