package com.kelin.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    @Scheduled(cron = "*/10 * * * * *")
    private void cancelTimeOutOrder() {
        LOGGER.info("取消订单，并根据 sku 编号释放锁定的库存");
    }
}
