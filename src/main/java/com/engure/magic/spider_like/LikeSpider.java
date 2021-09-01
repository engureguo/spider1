package com.engure.magic.spider_like;

import com.engure.magic.spider_like.processor.LikePageProcessor;
import com.engure.magic.spider_like.processor.ActualLikePageProcessor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取点赞排行页所有图片
 * </p>
 * 爬取逻辑抽取到一个类中，使用多个if-else判断
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

        setLikePageProcessor(new ActualLikePageProcessor());//需要传入抽取的接口，而非PageProcessor
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

        likePageProcessor.doProcess(page);//逻辑抽取

    }

    @Override
    public Site getSite() {
        return site;
    }
}
