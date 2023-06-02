package com.component.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author ZHZJ
 * @create 2022/11/19 21:39
 */
@Data
@TableName("user_info")
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private LocalDateTime workDate;

    private int ifEarlyWork;
    private int ifMiddleWork;
    private int ifNgintWork;
    //休息
    private int ifRest;
    //年休
    private int ifAnnualLeave;
    //病假
    private int ifSickLeave;
    //调休
    private int ifCompassionateLeave;
    //公差
    private int ifPublicErrand;
    //中夜班
    private int ifMidNgint;

    private String workGroup;
}
