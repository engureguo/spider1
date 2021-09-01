package com.engure.magic.oospider_cuplife;


import com.engure.magic.oospider_cuplife.model.NoticeModel;
import com.engure.magic.spider_like2.scheduler.MyBloomFilterDuplicateRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * OOSpider.java 基于注解的爬虫
 */

/**
 * 爬取学校文化专题网 https://www.cup.edu.cn/culture/index.htm
 * * 基于注解的爬虫
 * * 自定义类型转换
 * * 多页面爬取抽离（不同model相当于原来的不同的 if-else 页面判断）
 * * 使用yaml配置Site，包括cookies、headers配置（定义在spider1_demo包中）
 */

@Service
public class SchoolOOSpider {

    @Autowired
    private Site site;

    @Autowired
    private PageModelPipeline pageModelPipeline;


    public void start() {

        int expectedUrlNum = 1000000;

        OOSpider.create(site, pageModelPipeline, NoticeModel.class/*, NoticeListModel.class*/)
                .setScheduler(
                        new QueueScheduler().setDuplicateRemover(new MyBloomFilterDuplicateRemover(expectedUrlNum)))
                .thread(Runtime.getRuntime().availableProcessors() * 10)
                .addUrl("https://www.cup.edu.cn/culture/bfsd/index.htm")
                .run();

    }


}
