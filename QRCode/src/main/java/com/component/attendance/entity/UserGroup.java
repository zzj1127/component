package com.component.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @Author ZHZJ
 * @create 2022/11/20 12:31
 */
@Data
@TableName("user_group")
public class UserGroup {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private int identifierNo;
    private String company;
    private String department;
    private String numberId;
    private String username;

}
