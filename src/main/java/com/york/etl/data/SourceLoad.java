/**
 * @Title: SourceLoad.java
 * @Package com.york.etl.data
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:成都康赛电子科大信息技术有限责任公司
 * 
 * @author Comsys-zhang
 * @date 2019年5月14日 下午11:54:21
 * @version V1.0
 */

package com.york.etl.data;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.york.etl.common.base.AbstractLoad;

/**
  * @ClassName: SourceLoad
  * @Description: 上传
  * @author Comsys-zhang
  * @date 2019年5月14日 下午11:54:21
  *
  */

public class SourceLoad extends AbstractLoad{

	private static final Logger LOG = LoggerFactory.getLogger(SourceLoad.class);
	/*
	  * <p>Title: load</p>
	  * <p>Description: </p>
	  * @param o
	  * @see com.york.etl.common.base.AbstractLoad#load(java.util.Map)
	  */
	
	
	@Override
	public void load(Map<String, Object> o) {
		
		LOG.info("{}上传数据{}成功",Thread.currentThread().getName(),o);
	}

}
