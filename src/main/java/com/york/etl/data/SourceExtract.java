package com.york.etl.data;

import com.york.etl.common.base.AbstractExtract;
import com.york.etl.common.service.SourceService;
import com.york.etl.util.PropertyUtil;
import com.york.etl.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public class SourceExtract extends AbstractExtract {

	/**
	 * 抽取的表明
	 */
	private static final String  TABLE_NAME = "table";
	
	/**
	 * 偏移字段
	 */
	private static final String FIELD_NAME = "field";
	
	/**
	 * 抽取的字段
	 */
	private static final String FIELDS = "fields";
	
	/**
	 * 参数
	 */
	private static final Map<String,Object> TASK_PARAMS = new HashMap<String, Object>(16);
    
    private static final Logger LOG = LoggerFactory.getLogger(SourceExtract.class);


    private static int EXTRACT_TYPE = 0;

    static {
        //初始化 知道表、抽取类型
			TASK_PARAMS.put(TABLE_NAME, PropertyUtil.getProperty(TABLE_NAME));
			TASK_PARAMS.put(FIELD_NAME, PropertyUtil.getProperty(FIELD_NAME));
			TASK_PARAMS.put(FIELDS, PropertyUtil.getProperty(FIELDS));
			EXTRACT_TYPE = Integer.parseInt(PropertyUtil.getProperty("task.extract.type"));
			LOG.info("构建的任务对象为：{}" , TASK_PARAMS);

    }

    private SourceService dataService = (SourceService) SpringContextUtil.getBean(SourceService.class);

    @Override
    public List<Map<String, Object>> extract() {

    	if (!TASK_PARAMS.containsKey("taskName")) {
    		TASK_PARAMS.put("taskName",getTaskName());
		}
        
        List<Map<String, Object>> list;
        if (EXTRACT_TYPE == 0) {
            list = dataService.getByID(TASK_PARAMS);
        } else {
            list = dataService.getByDate(TASK_PARAMS);
        }

        return list;
    }

    @Override
    public List<Object> extractException() {
        return null;
    }
}
