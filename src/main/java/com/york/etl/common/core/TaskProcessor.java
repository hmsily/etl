package com.york.etl.common.core;

import com.york.etl.common.base.AbstractLoad;
import com.york.etl.common.base.AbstractTrans;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public class TaskProcessor implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskProcessor.class);

    private Map<String, Object> bean;

    private AbstractTrans trans;

    private AbstractLoad load;

    public TaskProcessor(Map<String, Object> bean, AbstractTrans trans, AbstractLoad load) {
        this.bean = bean;
        this.trans = trans;
        this.load = load;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
    	LOG.info("处理数据的转换类:" + trans);
        Map<String, Object> o = trans.handler(bean);
        if (o == null) {
            return;
        }
        LOG.info("处理数据" + bean);
        load.loadFather(o);
    }
}
