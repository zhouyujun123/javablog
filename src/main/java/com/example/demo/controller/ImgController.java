package com.example.demo.controller;

import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.utils.ImageUtil;
import io.minio.MinioClient;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-05-13  08:43
 */
@Slf4j
@RestController
public class ImgController {

    @RequestMapping(value = "/uploadFile",consumes = "multipart/form-data")
    public ApiResult uploadFile(@RequestParam("files") MultipartFile[] files) {
        List<String> imgUrlList = new ArrayList<>();
        try {
            MinioClient minioClient = new MinioClient("http://115.28.105.227:8888/", "minioadmin", "minioadmin");
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("blogImg");
            if (isExist) {
                log.error("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("blogImg");
            }
            for (MultipartFile file : files) {
                // 图片名称
                String fileName = file.getOriginalFilename();
                // 压缩图片
                BufferedImage image = Thumbnails.of(file.getInputStream()).scale(0.8f).outputQuality(1.0f).asBufferedImage();
                InputStream inputStream = ImageUtil.bufferedImageToInputStream(image);
                // 使用putObject上传一个文件到存储桶中。
                assert inputStream != null;
                minioClient.putObject("blogImg", fileName, inputStream, inputStream.available(), "application/octet-stream");
                String url = minioClient.getObjectUrl("blogImg", fileName);
                log.info("图片url==========>" + url);
                imgUrlList.add(url);
            }
        } catch (Exception e) {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.IMG_FAIL));
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, imgUrlList);
    }


}

