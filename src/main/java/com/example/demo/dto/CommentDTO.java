package com.example.demo.dto;

import com.example.demo.entity.TComment;
import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-06-01  16:08
 */
@Data
public class CommentDTO extends TComment {
    private String fromUserName;
    private String toUserName;
}
