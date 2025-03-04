package com.marubi.security.common.utils;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.marubi.security.core.config.WebConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.expression.Lists;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
* @Description: 上传工具
* @Author: hhw
* @Date: 2021/9/5
*/
@Data
@Slf4j
public class UploadCommonUtils {
    private static UploadCommonUtils instance;

    /**
     * 当前文件的项目路径
     */
    private static String filePathLcoal;
    public  static final String diyUri = "/marubi/file/";

    private UploadCommonUtils(){
        getFilePathLcoal();
    }

    public static UploadCommonUtils getInstance() {
        if(instance==null){
            synchronized (UploadCommonUtils.class){
                if(instance==null){
                    instance = new UploadCommonUtils();
                }
            }
        }
        return instance;
    }



    /**
     * 获取当前项目上传文件的项目路径
     * @author tmz
     * @date 2021/8/17
     * @param []
     * @return java.lang.String
     */
    public  String getFilePathLcoal() {
        try {
            if (StrUtil.isBlank(filePathLcoal)) {
                synchronized (WebConfig.class) {
                    if (StrUtil.isBlank(filePathLcoal)) {
                        File path = new File(ResourceUtils.getURL("classpath:").getPath());
                        if (!path.exists()) {
                            path = new File("");
                        }
                        File upload = new File(path.getAbsolutePath(), "static/upload/");
                        if (!upload.exists()) {
                            upload.mkdirs();
                        }
                        filePathLcoal = upload.getAbsolutePath();
                        log.info("存储绝对路径初始化,path:{}",filePathLcoal);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePathLcoal;
    }
    /**
     * 根据相对路径获取绝对路径
     * @author tmz
     * @date 2021/8/17
     * @param path 相对路径  static/upload/ 后面的路径
     * @param preName 域名或者(ip:端口)
     * @return java.lang.String
     */
    public String getRelativePathForAbs(String path ,String preName){
        String urlTemp = "%s%s%s";
        return String.format(urlTemp,preName,diyUri,path);
    }
    /**
     * 随机生成 文件夹路径
     * @author tmz
     * @date 2021/8/17
     * @param []
     * @return java.lang.String
     */
    public static String ramdomFileMkdir(){
        String replace = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                .replace(" ", File.separator);
        return StrUtil.isNotBlank(replace)?replace: StringUtils.EMPTY;
    }
    /**
     * 获取当前已知的 icon 字符串
     * @author tmz
     * @date 2021/8/24
     * @return java.util.List<java.lang.String>
     */
    public  List<String> iconAll(){
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            File icon = new File(path.getAbsolutePath(), "tempfile/icon.txt");
            if (!icon.exists()) {
                icon.mkdirs();
            }
            List<String> list = FileUtil.readLines(icon, Charsets.UTF_8).stream().map(s -> HtmlUtil.escape(StrUtil.trim("&"+s))).collect(Collectors.toList());
//            log.info("icon list ->{}",list);
            return list;
        }catch (Exception e){
            log.error("err,{}",e.getMessage());
        }
        return ListUtil.empty();
    }
}
