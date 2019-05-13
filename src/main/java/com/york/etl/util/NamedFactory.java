package com.york.etl.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池命名类
 * 
 * @author zhang
 * @date 2019/03/26
 */
public class NamedFactory implements ThreadFactory {

    private AtomicInteger tag = new AtomicInteger(1);
    private String name;
    public NamedFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,name + "-thread" + tag.getAndIncrement());
    }
}
