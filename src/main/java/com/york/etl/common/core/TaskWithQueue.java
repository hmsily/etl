package com.york.etl.common.core;

import com.york.etl.common.base.AbstractExtract;
import com.york.etl.common.base.AbstractLoad;
import com.york.etl.common.base.AbstractTrans;
import com.york.etl.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger(TaskWithQueue.class);
    
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
        
        String transClass = task.getTransClass();
        
        if (StringUtil.isEmpty(transClass)) {
			trans = null;
		}

    }

    public void handler() {
    	ThreadPoolExecutor poolExecutor = ThreadManager.getPool(task.getName( ));

    	while(true) {
    		
    		if (poolExecutor.getQueue().size() <= 20000 - 500 ) {
				
    			//判断队列是否已经到达界限值
    			List<Map<String, Object>> taskData = extract.extractWrapper( );
    			
    			LOG.info("获取的数据{}", taskData);
    			if (taskData == null || taskData.isEmpty()) {
    				return;
    			}
    			
    			if (trans == null) {
    				LOG.info("配置转换类{}", trans);
    				return;
				}
    			
    			for (int i = 0; i < taskData.size( ); i++) {
    				poolExecutor.submit(new TaskProcessor(taskData.get(i), trans, load));
    			}
			}
    	}

    }

}
