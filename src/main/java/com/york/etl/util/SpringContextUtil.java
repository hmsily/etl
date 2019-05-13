package com.york.etl.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/11 0011
 * @since jdk1.8
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(Class<?> clazz){
        return context.getBean(clazz);
    }
}
