package com.marubi.security.business.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.marubi.security.business.entity.BooksEntity;
import com.marubi.security.common.validatedinter.GroupAdd;
import com.marubi.security.common.validatedinter.GroupEdit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 图书新增/编辑
 * @author tmz
 */
@Data
public class BookEditParamDto implements Serializable {
    /**
     * 书籍ID
     */
    @ApiModelProperty("书籍ID，新增不用穿")
    @NotNull(groups = {GroupEdit.class})
    private Integer bookId;

    /**
     * 书名
     */
    @ApiModelProperty("书名")
    @NotBlank(groups = {GroupAdd.class})
    private String title;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    @NotBlank(groups = {GroupAdd.class})
    private String author;

    /**
     * 出版社
     */
    @ApiModelProperty("出版社")
    @NotBlank(groups = {GroupAdd.class})
    private String publisher;

    /**
     * 出版日期
     */
    @ApiModelProperty("出版日期")
    @NotBlank(groups = {GroupAdd.class})
    private LocalDate publishDate;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    @NotNull(groups = {GroupAdd.class})
    private BigDecimal price;


    /**
     * 类别
     */
    @ApiModelProperty("类别")
    @NotNull(groups = {GroupAdd.class})
    private String category;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    @NotNull(groups = {GroupAdd.class})
    private String summary;

    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    @NotNull(groups = {GroupAdd.class})
    private String keywords;

}
