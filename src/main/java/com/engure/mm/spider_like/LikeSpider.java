package com.engure.mm.spider_like;

import com.engure.mm.spider_like.processor.ActualLikePageProcessor;
import com.engure.mm.spider_like.processor.LikePageProcessor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取点赞排行页所有图片
 */

@Component
public class LikeSpider implements PageProcessor {

    //"C:\\Users\\HiWin10\\Desktop\\data\\"
    public static final String STORE_PATH = "F:\\data\\"; // 注意格式，结尾\\，目录必须是存在的

    private Site site = Site.me()
            .setCharset("UTF-8")
            .setRetryTimes(3).setTimeOut(1000 * 5).setSleepTime(500);

    private LikePageProcessor likePageProcessor;

    /**
     * 需要设置处理策略
     *
     * @param likePageProcessor
     */
    public void setLikePageProcessor(LikePageProcessor likePageProcessor) {
        this.likePageProcessor = likePageProcessor;
    }

    public Spider me() {
        return Spider.create(this);
    }

    /**
     * 开启爬虫
     */
    public void start() {

        //点赞排行页
        String url = "https://www.mm618.com/like";

        setLikePageProcessor(new ActualLikePageProcessor());
        me().addUrl(url)
                .thread(5).run();

    }

    /**
     * 页面处理
     *
     * @param page
     */
    @Override
    public void process(Page page) {

        likePageProcessor.doProcess(page);

    }

    @Override
    public Site getSite() {
        return site;
    }
}
