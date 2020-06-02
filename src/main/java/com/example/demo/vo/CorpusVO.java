package com.example.demo.vo;

import com.example.demo.entity.TCorpus;
import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-06-02  10:36
 */
@Data
public class CorpusVO extends TCorpus {
    private Boolean hasSubscribed;
}
