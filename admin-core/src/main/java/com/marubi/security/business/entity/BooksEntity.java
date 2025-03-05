package com.marubi.security.business.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图书信息表
 * </p>
 *
 * @author tmz
 * @since 2025-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bk_books")
public class BooksEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍ID
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 出版日期
     */
    private LocalDate publishDate;

    /**
     * 价格
     */
    private BigDecimal price;


    /**
     * 类别
     */
    private String category;

    /**
     * 简介
     */
    private String summary;

    /**
     * 关键字
     */
    private String keywords;


    /**
     * 0:未删除1已删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer delFlag;
//    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private LocalDateTime createTime;
//    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private String createName;
//    @TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
    private LocalDateTime updateTime;
//    @TableField(fill = FieldFill.INSERT_UPDATE,value = "create_time")
    private String updateName;


}
