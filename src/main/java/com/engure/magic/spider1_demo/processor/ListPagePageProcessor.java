package com.engure.magic.spider1_demo.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

/**
 * 使用继承的方法用作功能拓展
 */

@Component
public class ListPagePageProcessor extends DetailModelPageProcessor {
    @Override
    public void doProcess(Page page) {

        //列表页
        if (page.getRequest().getUrl().startsWith("list")) {
            //提取详细信息URL
            //跳过本页数据抽取
            page.setSkip(true);
        } else if (page.getRequest().getUrl().startsWith("detail")) {
            super.doProcess(page);
        } else {
            //其他
        }

    }
}
