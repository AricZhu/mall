package com.kelin.mall.service.impl;

import com.kelin.mall.common.api.CommonResult;
import com.kelin.mall.component.CancelOrderSender;
import com.kelin.mall.dto.OrderParam;
import com.kelin.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        LOGGER.info("process generateOrder");
        // 发送订单到队列中
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null , "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        long delayTimes = 10 * 1000;

        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
