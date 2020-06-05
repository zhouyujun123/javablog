package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.base.RoleCheck;
import com.example.demo.entity.TCorpus;
import com.example.demo.service.TCorpusService;
import com.example.demo.service.TSubscriptionService;
import com.example.demo.vo.ArticleVO;
import com.example.demo.vo.CorpusVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private TSubscriptionService subService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ApiResult selectOne(HttpServletRequest request, Long id) {
        TCorpus tCorpus = this.tCorpusService.queryById(id);
        String myUserId = (String) request.getAttribute(NormalConstant.USER_ID);
        CorpusVO corpusVO = new CorpusVO();
        BeanUtils.copyProperties(tCorpus, corpusVO);
        boolean sub = subService.isSub(myUserId, tCorpus.getId().toString(), NormalConstant.CORPUS_TYPE);
        if (sub) {
            corpusVO.setHasSubscribed(true);
        } else {
            corpusVO.setHasSubscribed(false);
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, corpusVO);
    }

//    @RoleCheck(roles = {"ADMIN"})
    @GetMapping("/findAllCorpus")
    public ApiResult findAllCorpus(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size,TCorpus corpus) {
        PageHelper.startPage(page, size);
        List<TCorpus> corpuses = tCorpusService.queryAll(corpus);
        PageInfo<TCorpus> pageInfo = new PageInfo<>(corpuses);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
    }

    /**
     *
     * @param id 文集id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public ApiResult deleteById(@PathVariable("id") Integer id){
        boolean delete = tCorpusService.deleteById(id);
        if (delete){
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }else {
            return ApiResult.errorWith(ResultCodeEnum.OPERATION_FAILED);
        }
    }

    /**
     * 添加文集
     * @param tCorpus
     * @return
     */
    @PostMapping("/addCorpus")
    public ApiResult addCorpus(HttpServletRequest request,TCorpus tCorpus){
        String myUserId = (String) request.getAttribute(NormalConstant.USER_ID);
        tCorpus.setUserId(Long.valueOf(myUserId));
        if (tCorpusService.insert(tCorpus)) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 修改文集
     * @param tCorpus
     * @return
     */
    @PostMapping("/updateCorpus")
    public ApiResult updateCorpus(TCorpus tCorpus){
        if (tCorpusService.update(tCorpus)) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }


}