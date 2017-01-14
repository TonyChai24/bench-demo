package com.example.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by cat on 2017-01-14.
 */
public class WorkerTheadStat implements WorkerTheadMXBean {

    private ThreadPoolExecutor pool;

    public WorkerTheadStat(ThreadPoolExecutor pool) {
        this.pool = pool;
    }

    @Override
    public int getActiveCount() {
        return pool.getActiveCount();
    }

    @Override
    public int getLargestPoolSize() {
        return pool.getLargestPoolSize();
    }

    @Override
    public int getPoolSize() {
        return pool.getPoolSize();
    }

    @Override
    public int getCorePoolSize() {
        return pool.getCorePoolSize();
    }

    @Override
    public int getMaximumPoolSize() {
        return pool.getMaximumPoolSize();
    }

    @Override
    public long getTaskCount() {
        return pool.getTaskCount();
    }

    @Override
    public long getCompletedTaskCount() {
        return pool.getCompletedTaskCount();
    }

    @Override
    public int getSubmittedCount() {
        return pool.getSubmittedCount();
    }

    @Override
    public long getThreadRenewalDelay() {
        return pool.getThreadRenewalDelay();
    }

    @Override
    public long getKeepAliveTimeMillis() {
        return pool.getKeepAliveTime(TimeUnit.MILLISECONDS);
    }
}
