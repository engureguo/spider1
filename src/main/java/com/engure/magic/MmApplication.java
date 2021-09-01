package com.engure.magic;

import com.engure.magic.oospider_cuplife.SchoolOOSpider;
import com.engure.magic.spider1_demo.Spider1;
import com.engure.magic.spider_like.LikeSpider;
import com.engure.magic.spider_like2.MySpider;
import com.engure.magic.spider_weibo.HotSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spider：基本爬虫，基于PageProcessor接口
 * oospider：基于注解的爬虫，爬虫逻辑定义在model中，更简洁
 */


@SpringBootApplication
public class MmApplication implements CommandLineRunner {

    @Autowired
    private LikeSpider likeSpider;

    @Autowired
    private MySpider mySpider;

    @Autowired
    private Spider1 spider1;

    @Autowired
    private HotSpider hotSpider;

    @Autowired
    private SchoolOOSpider schoolOOSpider;

    public static void main(String[] args) {
        SpringApplication.run(MmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //likeSpider.start();
        //mySpider.startCrawl();
        //spider1.startCrawl(1000, "https://www.cup.edu.cn/");
        //hotSpider.startCrawl(1000, "https://weibo.com/a/hot/realtime/");
        schoolOOSpider.start();
    }


}
