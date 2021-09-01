package com.engure.magic.oospider_cuplife.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//1.下载并提取本页   2.匹配本页中的url，会继续下载页面
@TargetUrl("https://www.cup.edu.cn/culture/(\\w+)/(\\w{32}).htm")
//@HelpUrl(value = "/html/body/div[3]/article/div[2]/section/div/ul/li[1]/div/a")
public class NoticeModel {

    //notnull=true，如果获取失败则丢弃本页结果
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[1]/h3/text()", notNull = true)
    private String title;

    //类型转换：string+pattern -> date
    @Formatter(value = "yyyy-MM-dd", formatter = MyDateFormatter.class)
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[2]/span/text()")
    private Date pubDate;

    //提取文章内容（html -> string），使用 allText()，貌似是webmagic自实现的
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[3]/allText()")
    private String article;

}
