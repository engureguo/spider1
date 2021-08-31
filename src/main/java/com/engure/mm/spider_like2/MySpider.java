package com.engure.mm.spider_like2;

import com.engure.mm.spider_like2.anno.Record;
import com.engure.mm.spider_like2.processor.ActualLikePageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/*

文件不存在，休眠200ms，爬取下载到 f:/data1/
------------------
80线程    13s,15s,13s
8个线程   81s,68s,77s

 */


@Service
@Slf4j
public class MySpider {

    public static final String STORE_PATH = "F:\\data1\\";

    @Record//统计爬取时间
    public void startCrawl() {

        //String url = "https://www.mm618.com/like";
        String url = "https://www.mm618.com/categories/xinggan";

        Spider.create(new ActualLikePageProcessor())
                //设置线程数量
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(Runtime.getRuntime().availableProcessors() * 10)
                .addUrl(url)
                .run();

    }

}
