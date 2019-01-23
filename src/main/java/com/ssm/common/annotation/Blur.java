package com.ssm.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 赵梦杰 on 2018/7/16.
 *
 * 创建提示value值里面可以放beanDao方法中的对
 * 单表操作的
 * 所对应的类的方法名称
 * 例如如下 list和find方法
 * list为全部查询
 * find为分页查询
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Blur {
    String[] value();
}
