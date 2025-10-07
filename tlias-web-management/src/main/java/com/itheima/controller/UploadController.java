package com.itheima.controller;

import com.itheima.AOP.MyLog;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result uploadFile( MultipartFile image) throws IOException {
        String url = aliOSSUtils.upload(image);
        log.info("文件访问的url是：-------->" + url);
        return Result.success(url);
    }
}
