package com.ssm.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by 赵梦杰 on 2018/7/17.
 */
public final class SpringBeanUtils implements ApplicationContextAware{

    private volatile static ApplicationContext ctx;


    public static Object getBean(String beanName) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
