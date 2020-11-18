package com.hhuc.webdesign.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Article {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    private String overview;
    private String content;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date timeStamp;
    private String userName;
}
