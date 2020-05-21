package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author mintaoyu
 * Date on 2020-05-21  16:48
 */
@Data
public class TRole implements Serializable {


    private static final long serialVersionUID = 1089397178261080060L;

    private Integer id;
    private String role;
}
