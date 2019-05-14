/**
 * @Title: TransCommon.java
 * @Package com.york.etl.common.core
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:成都康赛电子科大信息技术有限责任公司
 * 
 * @author Comsys-zhang
 * @date 2019年5月14日 下午11:53:23
 * @version V1.0
 */

package com.york.etl.common.core;

import java.util.Map;

import com.york.etl.common.base.AbstractTrans;

/**
  * @ClassName: TransCommon
  * @Description: 转换对象
  * @author Comsys-zhang
  * @date 2019年5月14日 下午11:53:23
  *
  */

public class TransCommon extends AbstractTrans{

	/*
	  * <p>Title: handler</p>
	  * <p>Description: </p>
	  * @param bean
	  * @return
	  * @see com.york.etl.common.base.AbstractTrans#handler(java.util.Map)
	  */
	
	
	@Override
	public Map<String, Object> handler(Map<String, Object> bean) {
		return bean;
	}

}
