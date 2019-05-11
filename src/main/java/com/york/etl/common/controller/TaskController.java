package com.york.etl.common.controller;

import com.york.etl.common.core.Task;
import com.york.etl.common.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/11 0011
 * @since jdk1.8
 */
@RestController
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(name = "/publish",method = RequestMethod.POST)
    public String publish(@RequestBody Task task){
        LOG.info("enter in task publish method!");
        LOG.info("request body:{}",task);
        String result = taskService.publish(task);
        return result;
    }
}
