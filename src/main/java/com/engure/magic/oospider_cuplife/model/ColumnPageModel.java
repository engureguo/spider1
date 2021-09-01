package com.engure.magic.oospider_cuplife.model;


import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://www.cup.edu.cn/culture/(\\w+)/index.htm")
@HelpUrl("https://www.cup.edu.cn/culture/(\\w+)/(\\w{32}).htm")
public class ColumnPageModel {

    @ExtractBy(value = "", notNull = true)//排除掉本页的数据处理
    private String passBy;

}
