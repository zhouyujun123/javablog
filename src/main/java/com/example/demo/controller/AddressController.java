package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TOrderAddress;
import com.example.demo.service.TOrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author mintaoyu
 * Date on 2020-06-17  14:20
 * 收货地址
 */
@RestController
public class AddressController {

    @Autowired
    private TOrderAddressService addressService;

    @PostMapping("/bindAddress")
    public ApiResult bindAddress(TOrderAddress address) {
        TOrderAddress insert = addressService.insert(address);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, insert);

    }

    @GetMapping("/delAddress/{addressId}")
    public ApiResult delAddress(@PathVariable("addressId") Integer addressId) {
        return addressService.deleteById(addressId);
    }

    @PostMapping("/editAddress")
    public ApiResult editAddress(TOrderAddress address) {
        TOrderAddress update = addressService.update(address);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, update);
    }
}
