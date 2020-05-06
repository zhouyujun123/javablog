package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TCorpus;
import com.example.demo.service.TCorpusService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCorpus)表控制层
 *
 * @author makejava
 * @since 2020-05-06 21:42:28
 */
@RestController
@RequestMapping("tCorpus")
public class TCorpusController {
    /**
     * 服务对象
     */
    @Resource
    private TCorpusService tCorpusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TCorpus selectOne(Integer id) {
        return this.tCorpusService.queryById(id);
    }

    @RequestMapping("/findAllCorpus")
    public ApiResult findAllCorpus(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size,TCorpus corpus) {
        PageHelper.startPage(page, size);
        List<TCorpus> corpuses = tCorpusService.queryAll(corpus);
        PageInfo<TCorpus> pageInfo = new PageInfo<>(corpuses);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
    }

}