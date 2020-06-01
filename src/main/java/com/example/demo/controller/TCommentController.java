package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TComment;
import com.example.demo.entity.TUser;
import com.example.demo.service.TCommentService;
import com.example.demo.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private TUserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TComment selectOne(Long id) {
        return this.tCommentService.queryById(id);
    }

    @PostMapping("/addComment")
    public ApiResult addComment(TComment comment) {
        if (tCommentService.insert(comment) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 通过文章id找文章评论
     *
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
        List<TComment> commentList = tCommentService.queryAll(com);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (TComment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            String fromUserName = userService.queryNameById(comment.getFromUserId());
            String toUserName = userService.queryNameById(comment.getToUserId());
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setFromUserName(fromUserName);
            commentDTO.setToUserName(toUserName);
            commentDTOList.add(commentDTO);
        }
        if (commentList.size() > 0) {
            PageHelper.startPage(page, size);
            PageInfo<CommentDTO> pageInfo = new PageInfo<>(commentDTOList);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

}