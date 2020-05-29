package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TImg;
import com.example.demo.service.TImgService;
import com.example.demo.utils.MD5Util;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public TImg selectOne(Long id) {
        return this.tImgService.queryById(id);
    }


    /**
     * 上传图片 支持多图上传
     *
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public ApiResult uploadFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        List<String> imgUrlList = new ArrayList<>();
        try {
            MinioClient minioClient = new MinioClient(minoUrl, accessKey, secretKey);
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if (isExist) {
                log.info("存储桶已存在，开始插入图片");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }
            for (MultipartFile file : files) {
                TImg img = new TImg();
                // 图片名称 为了确定唯一性 用户id+图片名称+8位随机数
                String fileName = MD5Util.md5LowerCase(request.getAttribute("userId") + file.getOriginalFilename() + RandomUtil.randomString(5));
                InputStream inputStream = file.getInputStream();
                minioClient.putObject(bucketName, fileName, inputStream, inputStream.available(), "application/octet-stream");
                String url = minioClient.getObjectUrl(bucketName, fileName);
                log.info("图片url==========>" + url);
                String userId = (String) request.getAttribute("userId");
                img.setUserId(Long.valueOf(userId));
                img.setImgUrl(url);
                int insert = tImgService.insert(img);
                if (insert > 0) {
                    imgUrlList.add(url);
                } else {
                    throw new ApiException(ApiResult.errorWith(ResultCodeEnum.IMG_UPLOAD_FAIL));
                }
            }
        } catch (Exception e) {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.IMG_UPLOAD_FAIL, e.getMessage()));
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, imgUrlList);
    }

    /**
     * 图片查询
     */
    @GetMapping("/findImg")
    public ApiResult findImg(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<TImg> imgs = tImgService.queryAll(userId);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, imgs);
    }


    /**
     * 图片删除
     */
    @PostMapping("/deleteImg")
    public ApiResult deleteImg(Long imgId) {
        boolean delete = tImgService.deleteById(imgId);
        if (delete) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.resultWith(ResultCodeEnum.IMG_DELETE_FAIL, imgId);
        }
    }

}