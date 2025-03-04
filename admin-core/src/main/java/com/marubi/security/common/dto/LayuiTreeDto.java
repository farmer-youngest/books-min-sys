package com.marubi.security.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * layui tree dto
 * @author tangmingze
 */
@Data
public class LayuiTreeDto implements Serializable {
    /**
     * 节点标题	String
     */
    private String title;
    /**
     * 节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
     */
    private Integer id;
    /**
     * 	节点字段名	String	一般对应表字段名
     */
    private String field;
    /**
     * 子节点。支持设定选项同父节点	Array	[{title: '子节点1', id: '111'}]
     */
    private List<LayuiTreeDto> children;
    /**
     * 	点击节点弹出新窗口对应的 url。需开启 isJump 参数	String	任意 URL
     */
    private String href;
    /**
     * 	节点是否初始展开，默认 false	Boolean	true
     */
    private Boolean spread;
    /**
     * 节点是否初始为选中状态（如果开启复选框的话），默认 false	Boolean	true
     */
    private Boolean checked;
    /**
     * 	节点是否为禁用状态。默认 false	Boolean	false
     */
    private Boolean disabled=false;
}
