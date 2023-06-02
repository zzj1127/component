package com.component.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * @Author ZHZJ
 * @create 2022/11/20 13:20
 */

public class Rzzy {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String workGroup;
    private String username;
    private String workNo;
    private int ifEarlyWork;
    private int ifMiddleWork;
    private int ifNgintWork;
    private LocalDateTime workDate;
}
