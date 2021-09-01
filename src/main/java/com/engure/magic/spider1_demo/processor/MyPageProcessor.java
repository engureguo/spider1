package com.engure.magic.spider1_demo.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;


@Slf4j
@Component
public class MyPageProcessor implements PageProcessor {

    @Autowired
    private ExtractedPageProcessor processor;

    @Autowired
    private Site site;

    @Override
    public void process(Page page) {

        preProcessor(page);
        processor.doProcess(page);
        postProcessor(page);

    }

    private void preProcessor(Page page) {
    }

    private void postProcessor(Page page) {
    }

    @Override
    public Site getSite() {
        return site;
    }
}
