package cn.edu.tongji.backend.test.mapper;

import cn.edu.tongji.backend.test.pojo.ReportTemplate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportTemplateMapper {

    @Insert("insert into report_template VALUES(null,#{l_id},#{t_id},#{title},#{order},#{placeholder},#{required},#{type})")
    void insertIntoReportTemplate(ReportTemplate reportTemplate);

    @Select("select * from report_template where l_id = #{l_id}")
    List<ReportTemplate> getLabReportTemplate(int l_id);
}
