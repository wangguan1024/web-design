package com.hhuc.webdesign.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class User{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String pwd;
    private Integer articleNum;
    private String signature;
    private String note;
    private String nickName;
}
