package cn.edu.tongji.backend.test.mapper;

import cn.edu.tongji.backend.test.pojo.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface ReportMapper {
    @Insert("insert into report values (null,#{s_id},#{l_id},#{name},#{submit_time},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "r_id", keyColumn = "r_id")
    void insertIntoReport(Report report);

    @Select("select r_id from report where l_id=#{l_id} and s_id=#{s_id};")
    int selectIdFromReport(int l_id, int s_id);

    @Select("select path from report_image where rf_id=#{rf_id}")
    String[] selectImagesFromReportImage(int rf_id);
}
