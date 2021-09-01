package com.engure.magic.spider_weibo.entity;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

//垂直爬虫：列表页--->详情页
@TargetUrl(value = "") //匹配“详情页”
@HelpUrl(value = "")//匹配“列表页”
public class HotEvent {

    @ExtractBy("")
    private String title;
    private String elink;
    private String author;
    private String auLink;
    private String sharesNum;
    private String commentsNum;
    private String likesNum;
    private String pubDate;

}
