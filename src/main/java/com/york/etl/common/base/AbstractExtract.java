package com.york.etl.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public abstract class AbstractExtract {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExtract.class);

    private String taskName;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return
     */
    public abstract List<Map<String, Object>> extract();

    public abstract List<Object> extractException();

    public List<Map<String, Object>> extractWrapper() {

    	return extract( );
    }

}
