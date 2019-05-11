package com.york.etl.common.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SourceMapper {

    long getMinId(Map<String, Object> map);

    Date getMinDate(Map<String, Object> map);
    
    long getMaxId(Map<String, Object> map);

    Date getMaxDate(Map<String, Object> map);

    List<Map<String, Object>> listById(Map<String, Object> condition);

    List<Map<String, Object>> listByDate(Map<String, Object> condition);
}
