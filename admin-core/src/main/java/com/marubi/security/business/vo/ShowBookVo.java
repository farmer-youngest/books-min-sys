package com.marubi.security.business.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.marubi.security.common.validatedinter.GroupAdd;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShowBookVo implements Serializable {

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
    private Integer delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 最新修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 最新修改人
     */
    private String updateName;

    public String getPriceStr() {
        BigDecimal number = price;
        BigDecimal scaledNumber = number.setScale(2, RoundingMode.HALF_UP);
        String result = scaledNumber.toString();
        return result;
    }
}
