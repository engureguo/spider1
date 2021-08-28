package com.engure.mm;

import com.engure.mm.spider_like.LikeSpider;
import com.engure.mm.spider_like2.MySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;

@SpringBootApplication
public class MmApplication implements CommandLineRunner {

    @Autowired
    private LikeSpider likeSpider;

    @Autowired
    private MySpider mySpider;

    public static void main(String[] args) {
        SpringApplication.run(MmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //likeSpider.start();
        mySpider.startCrawl();
    }


}
