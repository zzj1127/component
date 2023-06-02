package com.component.attendance.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.component.attendance.entity.Rzzy;
import com.component.attendance.entity.UserGroup;
import com.component.attendance.entity.UserInfo;
import com.component.attendance.mapper.ExcelImportMapper;
import com.component.attendance.mapper.ExcelImportUserGroupMapper;
import com.component.attendance.service.ExcelImportService;
import com.component.attendance.service.ExcelImportUserGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author ZHZJ
 * @create 2022/11/20 12:40
 */
@Service
public class ExcelImportUserGroupServiceImpl extends ServiceImpl<ExcelImportUserGroupMapper, UserGroup> implements ExcelImportUserGroupService {
    @Resource
    ExcelImportUserGroupMapper excelImportUserGroupMapper;

    @Override
    public List<Map> findData() {
        return excelImportUserGroupMapper.findData();

    }
}
