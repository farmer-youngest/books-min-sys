package com.marubi.security.core.service;

public interface FileService {
    /**
     * 根据相对路径获取绝对路径
     * @param: [path] Uploads/Shop/category/20170629/5954c4c94c69f.jpg
     * @return: java.lang.String
     * @author: tangminzge
     * @date: 2021/9/5
     */
    String getAbs(String path);
    /**
     * 根据绝对路径url 获取 项目相对路径
     * @param: [url]
     * @return: java.lang.String
     * @author: tangminzge
     * @date: 2021/9/5
     */
    String getRelative(String url);
}
