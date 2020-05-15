package com.example.demo.controller;

import com.example.demo.entity.TImg;
import com.example.demo.service.TImgService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TImg)表控制层
 *
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
@RestController
@RequestMapping("tImg")
public class TImgController {
    /**
     * 服务对象
     */
    @Resource
    private TImgService tImgService;

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

}