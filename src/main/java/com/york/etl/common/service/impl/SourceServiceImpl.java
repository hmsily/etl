package com.york.etl.common.service.impl;

import com.york.etl.common.mapper.SourceMapper;
import com.york.etl.common.mapper.TaskMapper;
import com.york.etl.common.service.SourceService;
import com.york.etl.util.PropertyUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
@Service
public class SourceServiceImpl implements SourceService {

	private static final long INTERVAL = Integer.parseInt(PropertyUtil.getProperty("task.interval"));

	private static final Logger LOG = LoggerFactory.getLogger(SourceServiceImpl.class);
    @Autowired
    private TaskMapper taskMapper;
    
    @Autowired
    private SourceMapper sourceMapper;
    

    @Override
    public List<Map<String, Object>> getByID(Map<String,Object> condition) {
		long begin = 0, end = 0;
    	Map<String,Object> taskRecord = taskMapper.getRecord(condition);
    	long maxID = sourceMapper.getMaxId(condition);
    	if (taskRecord == null) {
    		LOG.info("数据库中有记录获取的task信息是：{}", taskRecord);
    		String taskId = UUID.randomUUID().toString().replace("-","");
    		condition.put("taskId",taskId);
    		begin = sourceMapper.getMinId(condition);
    		end = (INTERVAL > maxID)? maxID: INTERVAL;
    		taskRecord = new HashMap<String, Object>(condition);
    		taskRecord.put("end",end);
    		taskRecord.put("updateTime",new Date());
			taskMapper.addRecord(taskRecord);
		}else {
			LOG.info("数据库中有记录获取的task信息是：{}", taskRecord);
			begin = Long.valueOf(taskRecord.get("CURRENT_VALUE").toString( ));
			end = ((begin + INTERVAL) > maxID)? maxID:(begin + INTERVAL) ;
			taskRecord.put("end", end);
			taskRecord.put("taskId", taskRecord.get("ID"));
			taskRecord.put("updateTime", new Date( ));
			taskMapper.updateRecord(taskRecord);
		}
		condition.put("begin", begin);
		condition.put("end", end);
        return sourceMapper.listById(condition);
    }

    @Override
    public List<Map<String, Object>> getByDate(Map<String,Object> condition) {
    	long begin,end;
    	Map<String,Object> taskRecord = taskMapper.getRecord(condition);
    	if (taskRecord == null) {
    		begin = ((Date)sourceMapper.getMinDate(condition)).getTime();
    		condition.put("begin",begin);
    		condition.put("end",INTERVAL);
    		taskRecord = new HashMap<String, Object>(condition);
    		taskRecord.put("currentDate", new Date(INTERVAL));
			taskMapper.addRecord(taskRecord);
		}else {
			begin = ((Date)taskRecord.get("currentDate")).getTime();
			end = begin + INTERVAL;
			taskRecord.put("begin", end);
			taskMapper.updateRecord(taskRecord);
		}
        return sourceMapper.listByDate(condition);
    }

}
