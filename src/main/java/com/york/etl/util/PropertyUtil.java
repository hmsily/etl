package com.york.etl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/11 0011
 * @since jdk1.8
 */
public class PropertyUtil implements Runnable{

    private static Map<String,String> PropertyContainer = null;

    public static String getProperty(String name){

        if (PropertyContainer == null){
            PropertyContainer = new HashMap<>(128);
            PropertyUtil.refreshContext();
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new NamedFactory("load-pool"));
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> refreshContext(), 1000, 5000, TimeUnit.MILLISECONDS);
        }
        if (!PropertyContainer.containsKey(name)){
            throw new IllegalArgumentException("properties file don't contains {" + name + "}");
        }
        return PropertyContainer.get(name);
    }

    private synchronized static void refreshContext(){
        String path = PropertyUtil.class.getResource("/").getPath();
        File rootDir = new File(path);

        for (File file : rootDir.listFiles()){
            if (file.isDirectory()){

            }
            if (file.isFile() && file.getName().endsWith(".properties")){
                Properties p = new Properties();
                try {
                    p.load(new FileInputStream(file));
                    for (String propertyName : p.stringPropertyNames()){
                        PropertyContainer.put(propertyName,p.getProperty(propertyName));
                    }
                } catch (IOException e) {
                    e.printStackTrace( );
                }

            }
        }

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        refreshContext();
    }
}
