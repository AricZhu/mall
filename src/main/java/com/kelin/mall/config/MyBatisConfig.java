package com.kelin.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.kelin.mall.mbg.mapper")
public class MyBatisConfig {
}
