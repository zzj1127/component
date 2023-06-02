package com.component.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.component.attendance.entity.Rzzy;
import com.component.attendance.entity.UserGroup;
import com.component.attendance.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ZHZJ
 * @create 2022/11/20 12:39
 */
@Service
public interface ExcelImportUserGroupService extends IService<UserGroup> {
    List<Map> findData();
}
