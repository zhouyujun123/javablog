package com.example.demo.service.impl;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.TOrderDetailDao;
import com.example.demo.dao.TOrderMasterDao;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.TOrderDetail;
import com.example.demo.entity.TOrderMaster;
import com.example.demo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-06-17  09:34
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TOrderDetailDao detailDao;

    @Autowired
    private TOrderMasterDao masterDao;


    @Override
    public ApiResult creatOrder(OrderDTO orderDTO) {
        List<TOrderDetail> detailList = orderDTO.getOrderDetailList();
        for (TOrderDetail detail : detailList) {
            detailDao.insert(detail);
        }
        masterDao.insert(orderDTO);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }

    @Override
    public ApiResult delOrder(String orderId) {
        masterDao.deleteById(orderId);
        detailDao.deleteByOrderId(orderId);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }

    @Override
    public ApiResult getOrderList(String userId) {
        TOrderMaster master = masterDao.queryByUserId(userId);
        List<TOrderDetail> details = detailDao.queryAllByOrderId(master.getOrderId());
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master,orderDTO);
        orderDTO.setOrderDetailList(details);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS,orderDTO);
    }

    @Override
    public ApiResult getOrderDetail(String orderId) {
        return null;
    }
}
