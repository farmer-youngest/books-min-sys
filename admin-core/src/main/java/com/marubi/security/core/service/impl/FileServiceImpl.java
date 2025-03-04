package com.marubi.security.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.marubi.security.common.utils.UploadCommonUtils;
import com.marubi.security.core.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("filelocalService")
@Slf4j
public class FileServiceImpl implements FileService {
    @Value("${marubi.pre.address: http://127.0.0.1:8089}")
    private String preName;
    @Override
    public String getAbs(String path) {
        UploadCommonUtils instance = UploadCommonUtils.getInstance();
        if (StrUtil.startWith(path, File.separator) ) {
            path  = path.replaceFirst(File.separator,"");
            return instance.getRelativePathForAbs(path,preName);
        } else if (StrUtil.startWith(path, "/")) {
            path  = path.replaceFirst("/","");
            return instance.getRelativePathForAbs(path,preName);
        } else {
            return path;
        }

    }

    @Override
    public String getRelative(String url) {
        return StrUtil.replace(StrUtil.replace(url,preName,""),UploadCommonUtils.diyUri,"");
    }
}
