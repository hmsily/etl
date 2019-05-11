package com.york.etl.common.base;

import java.util.Map;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public abstract class AbstractLoad {

    public abstract void load(Map<String, Object> o);

    public void loadFather(Map<String, Object> o) {
        load(o);
    }
}
