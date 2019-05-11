package com.york.etl.common.core;

import com.york.etl.common.base.AbstractExtract;
import com.york.etl.common.base.AbstractLoad;
import com.york.etl.common.base.AbstractTrans;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public class TaskWithQueue {

    private Task task;

    private AbstractExtract extract;

    private AbstractTrans trans;

    private AbstractLoad load;

    /**
     * 任务队列构造方法
     *
     * @param task
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public TaskWithQueue(Task task) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        this.task = task;

        extract = (AbstractExtract) Class.forName(task.getExtractClass( )).newInstance( );

        extract.setTaskName(task.getName());

//        trans = (AbstractTrans) Class.forName(task.getTransClass( )).newInstance( );
//
//        load = (AbstractLoad) Class.forName(task.getLoadClass( )).newInstance( );
    }

    public void handler() {

        ThreadPoolExecutor poolExecutor = ThreadManager.getPool(task.getName( ));

        //判断队列是否已经到达界限值
        List<Map<String, Object>> taskData = extract.extractWrapper( );

        if (taskData == null || taskData.size( ) == 0) {
            return;
        }

        for (int i = 0; i < taskData.size( ); i++) {
            poolExecutor.submit(new TaskProcessor(taskData.get(i), trans, load));
        }

    }
}
