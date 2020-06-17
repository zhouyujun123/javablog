package com.example.demo.dto;

import com.example.demo.entity.TOrderAddress;
import com.example.demo.entity.TOrderDetail;
import com.example.demo.entity.TOrderMaster;
import lombok.Data;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-06-17  09:52
 */
@Data
public class OrderDTO extends TOrderMaster {

    private TOrderAddress address;

    private List<TOrderDetail> orderDetailList;
}
