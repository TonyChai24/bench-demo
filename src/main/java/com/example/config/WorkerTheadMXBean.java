package com.example.config;

/**
 * JMX的标准MBean的接口必须以XxxxMXBean格式名称
 * Created by cat on 2017-01-14.
 */
public interface WorkerTheadMXBean {

    public int getActiveCount();

    public int getLargestPoolSize();

    public int getPoolSize();

    public int getCorePoolSize();

    public int getMaximumPoolSize();

    public long getTaskCount();

    public long getCompletedTaskCount();

    public int getSubmittedCount();

    public long getThreadRenewalDelay();

    public long getKeepAliveTimeMillis();
}
