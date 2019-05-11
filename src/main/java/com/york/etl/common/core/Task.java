package com.york.etl.common.core;

/**
 * @author zhang
 * @version 1.0
 * @date 2019/5/9 0009
 * @since jdk1.8
 */
public class Task {

    private String name;

    private String extractClass;

    private String transClass;

    private String loadClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtractClass() {
        return extractClass;
    }

    public void setExtractClass(String extractClass) {
        this.extractClass = extractClass;
    }

    public String getTransClass() {
        return transClass;
    }

    public void setTransClass(String transClass) {
        this.transClass = transClass;
    }

    public String getLoadClass() {
        return loadClass;
    }

    public void setLoadClass(String loadClass) {
        this.loadClass = loadClass;
    }

    public Task(String name, String extractClass, String transClass, String loadClass) {
        this.name = name;
        this.extractClass = extractClass;
        this.transClass = transClass;
        this.loadClass = loadClass;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", extractClass='" + extractClass + '\'' +
                ", transClass='" + transClass + '\'' +
                ", loadClass='" + loadClass + '\'' +
                '}';
    }
}
