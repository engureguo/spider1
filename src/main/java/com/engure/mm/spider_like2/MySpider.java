package com.engure.mm.spider_like2;

import com.engure.mm.spider_like2.anno.Record;
import com.engure.mm.spider_like2.processor.ActualLikePageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.lang.reflect.Method;

/*

文件不存在，休眠200ms，爬取下载到 f:/data1/
------------------
80线程    13s,15s,13s
8个线程   81s,68s,77s

 */


@Service
@Slf4j
public class MySpider extends Spider {

    public static final String STORE_PATH = "F:\\data1\\";

    /**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor pageProcessor
     */
    public MySpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    @Record
    public void startCrawl() {

        String url = "https://www.mm618.com/like";

        new MySpider(new ActualLikePageProcessor())
                //设置线程数量
                .thread(Runtime.getRuntime().availableProcessors() * 10)
                .addUrl(url)
                .run();

    }

}
