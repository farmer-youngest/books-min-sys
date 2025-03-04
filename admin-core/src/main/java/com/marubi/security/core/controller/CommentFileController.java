package com.marubi.security.core.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.utils.UploadCommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 文件统一上传类
 *
 * @param
 * @author tmz
 * @date 2021/8/17
 * @return
 */
@RestController
@RequestMapping("commentFile")
@Slf4j
public class CommentFileController {
    @Value("${marubi.pre.address: http://127.0.0.1:8089}")
    private String preName;
    /**
     * 上传文件同一接口
     *
     * @param file
     * @param type
     * @return java.lang.String
     * @author tmz
     * @date 2021/8/17
     */
    @PostMapping("file/{type}")
    public Result<String> upload(MultipartFile file, @PathVariable(value = "type",required = false) String type) throws FileNotFoundException {
        String uploadFileAbsPath = null;
        FileTypeEnums typeEnum = FileTypeEnums.getByType(type);
        UploadCommonUtils instance = UploadCommonUtils.getInstance();
        String ramdom = UploadCommonUtils.ramdomFileMkdir();
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        String fileramdomName = String.format("%s.%s",IdUtil.fastSimpleUUID(), suffix);
        if (file != null) {
            System.out.println(suffix);
            try (InputStream in = file.getInputStream()) {
                log.info("file->{}", file.getName());
                String absolutePath = instance.getFilePathLcoal();
                uploadFileAbsPath = new StringBuilder(absolutePath).append(File.separator)
                                .append(typeEnum.getType()).append(File.separator)
                                .append(ramdom)
                                .append(File.separator).append(fileramdomName).toString();
                log.info("abs path :{}",uploadFileAbsPath);
                FileCopyUtils.copy(in, FileUtil.getOutputStream(uploadFileAbsPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // static/upload/ 后面的路径
        String path = new StringBuilder(typeEnum.getType())
                .append(File.separator).append(ramdom).append(File.separator)
                .append(fileramdomName)
                .toString();
        return Result.build(Result.SUCCESS_CODE, StringUtils.EMPTY,instance.getRelativePathForAbs(path,preName));
    }
    /**
     * 可扩展文件类型枚举
     * @author tmz
     * @date 2021/8/17
     */
    public enum FileTypeEnums {
        IMAGE("图片文件类型","images")
        ,EXCEL("excel表格文件类型","excels")
        ,VIDEO("媒体文件类型","videos")
        ,DEFAULT("未知文件类型","default_files")
        ;
        private String desc;
        private String type;

        FileTypeEnums(String desc, String type) {
            this.desc = desc;
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }
        /**
         * 根据type获取文件类型枚举
         * @author tmz
         * @date 2021/8/17
         * @param [type]
         * @return com.marubi.security.core.controller.CommentFileController.FileTypeEnums
         */
        public static FileTypeEnums getByType(String type){
            for (FileTypeEnums value : FileTypeEnums.values()) {
                if(StrUtil.equals(value.type,type)){
                    return value;
                }
            }
            return FileTypeEnums.DEFAULT;
        }
    }
}
