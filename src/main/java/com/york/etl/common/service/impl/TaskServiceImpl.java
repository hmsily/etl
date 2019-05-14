package com.york.etl.common.service.impl;

import com.york.etl.common.core.Task;
import com.york.etl.common.core.TaskWithQueue;
import com.york.etl.common.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/11 0011
 * @since jdk1.8
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public String publish(Task task) {
        System.out.println(task );
        String result = "success";
        try {
            new TaskWithQueue(task).handler();
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
            result = e.getMessage();
        } catch (IllegalAccessException e) {
            e.printStackTrace( );
            result = e.getMessage();
        } catch (InstantiationException e) {
            e.printStackTrace( );
            result = e.getMessage();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
    }
}
