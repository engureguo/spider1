package com.engure.magic.spider_like.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class DemoSpider implements PageProcessor {

    Site site = new Site().setRetryTimes(3).setTimeOut(3000).setSleepTime(100);

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new DemoSpider()).addUrl("https://www.cup.edu.cn/culture/index.htm")
                .thread(10).run();
    }


}
