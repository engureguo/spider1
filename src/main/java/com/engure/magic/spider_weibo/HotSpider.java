package com.engure.magic.spider_weibo;

import com.engure.magic.spider_like2.scheduler.MyBloomFilterDuplicateRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;


/**
 * 微博热门
 */


@Service
public class HotSpider {


    @Qualifier("hotRealtimePageProcessor")
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
