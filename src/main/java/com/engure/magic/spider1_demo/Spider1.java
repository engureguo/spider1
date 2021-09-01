package com.engure.magic.spider1_demo;

import com.engure.magic.spider_like2.scheduler.MyBloomFilterDuplicateRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * 基本爬虫（基于PageProcessor接口）的框架
 */

/**
 * 只有一个PageProcessor，要爬取多页，具体的逻辑
 * * 如果写在一个pageprocessor实现类中，那么需要多个 if-else 语句，可读性差
 * * 如果写在”多个pageprocessor“中，那么需要做功能拓展（方法有：继承、装饰者、动态代理）
 */

@Service
public class Spider1 {

    @Qualifier("myPageProcessor")//多个pageprocessr，按照id注入
    @Autowired
    private PageProcessor pageProcessor;

    public void startCrawl(int expectedUrlNum, String... urls) {

        Spider.create(pageProcessor)
                .addUrl(urls)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new MyBloomFilterDuplicateRemover(expectedUrlNum)))
                .thread(Runtime.getRuntime().availableProcessors() * 10)
                .run();

    }

}
