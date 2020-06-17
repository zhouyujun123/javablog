package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.TComment;
import com.example.demo.service.TCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TComment)表控制层
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 * 文章评论
 */
@RestController
@RequestMapping("tComment")
public class TCommentController {

    /**
     * 服务对象
     */
    @Resource
    private TCommentService tCommentService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ApiResult selectOne(Long id) {
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, this.tCommentService.queryById(id));
    }

    @PostMapping("/addComment")
    public ApiResult addComment(HttpServletRequest request,TComment comment) {
        String myUserId = (String) request.getAttribute(NormalConstant.USER_ID);
        comment.setFromUserId(Long.valueOf(myUserId));
        if (tCommentService.insert(comment) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 通过文章id找文章评论
     * <p>
     * 默认previousId为0，即查找顶级评论
     *
     * @param articleId
     * @return
     */
    @GetMapping("/findComment/{articleId}")
    public ApiResult findComment(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size, @PathVariable("articleId") Long articleId, @RequestParam(defaultValue = "0") Long previousId) {
        TComment com = new TComment();
        com.setArticleId(articleId);
        com.setPreviousId(previousId);
        List<CommentDTO> commentList = tCommentService.queryAll(com);
        if (commentList.size() > 0) {
            PageHelper.startPage(page, size);
            PageInfo<CommentDTO> pageInfo = new PageInfo<>(commentList);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

}