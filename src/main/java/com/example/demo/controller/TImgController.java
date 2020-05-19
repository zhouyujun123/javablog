package com.example.demo.controller;

import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TImg;
import com.example.demo.service.TImgService;
import com.example.demo.utils.ImageUtil;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * (TImg)表控制层
 *
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
@RestController
@RequestMapping("tImg")
@Slf4j
public class TImgController {
    /**
     * 服务对象
     */
    @Resource
    private TImgService tImgService;

    @Value("${providers.mino.minoUrl}")
    private String minoUrl;

    @Value("${providers.mino.accessKey}")
    private String accessKey;

    @Value("${providers.mino.secretKey}")
    private String secretKey;

    @Value("${providers.mino.bucketName}")
    private String bucketName;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TImg selectOne(Integer id) {
        return this.tImgService.queryById(id);
    }


    /**
     * 上传图片 支持多图上传
     * @param files
     * @return
     */
    @PostMapping(value = "/uploadFile",consumes = "multipart/form-data")
    public ApiResult uploadFile(MultipartFile[] files) {
        List<String> imgUrlList = new ArrayList<>();
        try {
            MinioClient minioClient = new MinioClient(minoUrl, accessKey, secretKey);
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if (isExist) {
                log.error("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }
            for (MultipartFile file : files) {
                // 图片名称
                String fileName = file.getOriginalFilename();
                // 压缩图片
                BufferedImage image = Thumbnails.of(file.getInputStream()).scale(0.8f).outputQuality(1.0f).asBufferedImage();
                InputStream inputStream = ImageUtil.bufferedImageToInputStream(image);
                // 使用putObject上传一个文件到存储桶中。
                assert inputStream != null;
                minioClient.putObject(bucketName, fileName, inputStream, inputStream.available(), "application/octet-stream");
                String url = minioClient.getObjectUrl(bucketName, fileName);
                log.info("图片url==========>" + url);
                imgUrlList.add(url);
            }
        } catch (Exception e) {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.IMG_FAIL,e.getMessage()));
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, imgUrlList);
    }

}