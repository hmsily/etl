package com.york.etl.common.mapper;

import java.util.Map;

public interface TaskMapper {
	/**
	 * 获取任务记录
	 * @param map 任务参数
	 * @return 任务记录
	 */
    Map<String, Object> getRecord(Map<String, Object> map);

    /**
	 * 添加任务记录
	 * @param map 任务参数
	 * @return 任务记录
	 */
    void addRecord(Map<String, Object> map);

    /**
     * 更新任务记录
     * @param map 任务参数
     * 
     */
    void updateRecord(Map<String, Object> map);
}
