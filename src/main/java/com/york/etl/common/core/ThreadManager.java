package com.york.etl.common.core;


import com.york.etl.util.NamedFactory;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public class ThreadManager {


    private static Map<String, ThreadPoolExecutor> ThreadPools = new ConcurrentHashMap<>(8);


    public synchronized static ThreadPoolExecutor getPool(String poolName) {

        if (ThreadPools.get(poolName) == null) {
            ThreadPools.put(poolName, new ThreadPoolExecutor(10, 20, 5000, TimeUnit.MICROSECONDS,
                    new ArrayBlockingQueue<>(20000), new NamedFactory(poolName)));
        }
        return ThreadPools.get(poolName);
    }
}
