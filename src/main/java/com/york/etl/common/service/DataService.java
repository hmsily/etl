package com.york.etl.common.service;

import java.util.List;
import java.util.Map;

public interface DataService {

    List<Map<String, Object>> getByID(Map<String, Object> condition);

    List<Map<String, Object>> getByDate(Map<String, Object> condition);

}
