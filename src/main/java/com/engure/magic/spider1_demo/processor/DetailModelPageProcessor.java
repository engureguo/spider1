package com.engure.magic.spider1_demo.processor;

import us.codecraft.webmagic.Page;

public class DetailModelPageProcessor implements ExtractedPageProcessor {

    /**
     * 详情页数据提取
     *
     * @param page 详情页
     */

    @Override
    public void doProcess(Page page) {

        //详情页
        if (page.getRequest().getUrl().startsWith("detail")) {
            //提取数据
        }

    }
}
