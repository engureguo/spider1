package com.engure.magic.oospider_cuplife.model;


import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

@TargetUrl("https://www.cup.edu.cn/culture/index.htm")//1.提取本页  2.继续正则匹配，会继续下载页面
@HelpUrl("https://www.cup.edu.cn/culture/(\\w+)/index.htm")//在本页中正则匹配，不会在本页提取数据，此注解可选
public class IndexPageModel {

    @ExtractBy("/html/body/div[1]/div/div[2]/ul/li/a/@text()")
    private List<String> columns;

    @ExtractBy("/html/body/div[1]/div/div[2]/ul/li/a/@href")
    private List<String> links;

}
