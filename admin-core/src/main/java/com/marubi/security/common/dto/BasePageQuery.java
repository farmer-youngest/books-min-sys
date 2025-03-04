package com.marubi.security.common.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页查询的基类
 * @Date 2020-11-08
 * @Description
 * @Version 1.0
 */
public class BasePageQuery implements Serializable {

    /**
     * 当前页码
     */
    @Min(1)
    protected int pageNum = 1;

    /**
     * 每页数量
     */
    @Min(1)
    @Max(100)
    protected int pageSize = 10;

    /**
     * 排序队列
     */
    protected List<OrderItem> orderItems;


    public BasePageQuery() {
    }

    public BasePageQuery(@Min(1) int pageNum, @Min(1) @Max(100) int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 生成{@linkplain Page}的快捷方法
     * @param <T>
     * @return
     */
    public <T> Page<T> createPage(){
        return createPage(true);
    }

    /**
     * 生成{@linkplain Page}的快捷方法
     * @param isSearchCount 是否进行 count 查询
     * @param <T>
     * @return
     */
    public <T> Page<T> createPage(boolean isSearchCount){
        Page<T> page = new Page<T>(pageNum,getPageSize(),isSearchCount);
        page.setRecords(new ArrayList<>());
        page.setOrders(orderItems);
        return page;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getOffset(){
        return (pageNum-1)*pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
