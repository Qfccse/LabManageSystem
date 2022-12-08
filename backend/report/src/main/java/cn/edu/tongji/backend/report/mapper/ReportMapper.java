package cn.edu.tongji.backend.report.mapper;

import cn.edu.tongji.backend.report.pojo.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportMapper {
    @Insert("insert into report values (null,#{s_id},#{l_id},#{name},#{status},#{submit_time})")
    @Options(useGeneratedKeys = true, keyProperty = "r_id", keyColumn = "r_id")
    void insertIntoReport(Report report);

    @Update("update report set status=#{status},submit_time=#{submit_time} where r_id=#{r_id}")
    void updateReport(Report report);

    @Select("select * from report where s_id=#{s_id} and l_id=#{l_id}")
    Report selectReportById(int l_id, String s_id);

    @Select("select r_id from report where l_id=#{l_id} and s_id=#{s_id};")
    int selectIdFromReport(int l_id, String s_id);

    @Select("select count(r_id) from report  where l_id=#{l_id} and s_id=#{s_id}")
    int selectCountFromReport(int l_id, String s_id);

    @Select("select path from report_image where rf_id=#{rf_id}")
    String[] selectImagesFromReportImage(int rf_id);

    @Select("select * from report where l_id=#{l_id}")
    List<Report> selectLabReport(int l_id);

    @Select("select student.name from student where s_id=#{s_id}")
    String getStuNameByID(String s_id);
}
