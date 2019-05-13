package com.york.etl.common.base;

import java.util.Map;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public abstract class AbstractTrans {

    /**
     * 对Map对象进行转换
     *
     * @param bean 待转换的map
     * @return 转换之后的Map对象
     */
    public abstract Map<String, Object> handler(Map<String, Object> bean);
}
