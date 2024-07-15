package com.kelin.mall.service;

import com.kelin.mall.common.api.CommonResult;
import com.kelin.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 */
public interface OmsPortalOrderService {
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);
}
