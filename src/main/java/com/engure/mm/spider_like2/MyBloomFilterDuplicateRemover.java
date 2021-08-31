package com.engure.mm.spider_like2;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 集成BloomFilter，实现URL的去重
 */

public class MyBloomFilterDuplicateRemover implements DuplicateRemover {

    public MyBloomFilterDuplicateRemover(int size) {
        this(size, 0.01);
    }

    public MyBloomFilterDuplicateRemover(int size, double fpp) {
        this.size = size;
        this.fpp = fpp;
        rebuild();
    }

    private AtomicInteger counter;

    private int size;

    private double fpp;

    private BloomFilter<String> bloomFilter;

    private void rebuild() {
        counter = new AtomicInteger();
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), size, fpp);
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        boolean duplicate = bloomFilter.mightContain(request.getUrl());
        if (!duplicate) { //不存在
            bloomFilter.put(request.getUrl());
            counter.incrementAndGet();
        }
        return duplicate;
    }

    //重置布隆过滤器，貌似没用上
    @Override
    public void resetDuplicateCheck(Task task) {
        rebuild();
    }

    //获取布隆集合元素数量，貌似没用上
    @Override
    public int getTotalRequestsCount(Task task) {
        return counter.get();
    }
}
