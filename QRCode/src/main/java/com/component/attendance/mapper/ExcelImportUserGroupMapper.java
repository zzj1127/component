package com.component.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.component.attendance.entity.Rzzy;
import com.component.attendance.entity.UserGroup;
import com.component.attendance.entity.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ZHZJ
 * @create 2022/11/20 12:41
 */
@Repository
public interface ExcelImportUserGroupMapper extends BaseMapper<UserGroup> {
    @Select("SELECT ug.work_group,ug.username,ug.work_no,ui.if_early_work,ui.if_middle_work,ui.if_ngint_work,ui.work_date " +
            " FROM user_info ui, user_group ug " +
            " WHERE ui.username = ug.username " +
            "     AND ug.work_group = '雷霆班组' order by ug.id")
    List<Map> findData();
}
