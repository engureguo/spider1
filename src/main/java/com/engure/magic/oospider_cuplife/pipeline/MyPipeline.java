package com.engure.magic.oospider_cuplife.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


@Component
@Slf4j
public class MyPipeline implements PageModelPipeline<Object> {

    @Override
    public void process(Object o, Task task) {
        log.info("pipeline output: " + o.toString());
    }
}
