package com.engure.magic.oospider_cuplife.model;


import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://www.cup.edu.cn/culture/(\\w+)/index.htm")
@HelpUrl("https://www.cup.edu.cn/culture/(\\w+)/(\\w{32}).htm")
public class ColumnPageModel {

    //不进行数据处理怎么办？
    //* 保存数据是进行instanceof类型判断
    //* 或者实现AfterExtractor接口setSkip（好一点？）

}
