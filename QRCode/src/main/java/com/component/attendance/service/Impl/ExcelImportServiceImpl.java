package com.component.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.component.attendance.entity.UserInfo;
import com.component.attendance.mapper.ExcelImportMapper;
import com.component.attendance.service.ExcelImportService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ZHZJ
 * @create 2022/11/19 22:14
 */
@Service
public class ExcelImportServiceImpl extends ServiceImpl<ExcelImportMapper, UserInfo> implements ExcelImportService {

    @Resource
    ExcelImportMapper excelImportMapper;
    @Override
    public List<UserInfo> findUser(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",name);
        wrapper.orderByAsc("work_date");
        List list = excelImportMapper.selectList(wrapper);
        return list;
    }
}
