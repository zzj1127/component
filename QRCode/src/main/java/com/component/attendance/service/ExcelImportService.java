package com.component.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.component.attendance.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZHZJ
 * @create 2022/11/19 22:00
 */
@Service
public interface  ExcelImportService extends IService<UserInfo>{
   List<UserInfo> findUser(String name);
}
