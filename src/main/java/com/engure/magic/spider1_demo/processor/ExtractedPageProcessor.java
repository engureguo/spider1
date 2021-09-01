package com.engure.magic.spider1_demo.processor;

import us.codecraft.webmagic.Page;

/**
 * 抽取页面处理逻辑
 */

public interface ExtractedPageProcessor {
    void doProcess(Page page);
}
