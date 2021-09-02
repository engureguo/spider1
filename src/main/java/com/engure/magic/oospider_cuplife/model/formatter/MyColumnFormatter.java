package com.engure.magic.oospider_cuplife.model.formatter;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public class MyColumnFormatter implements ObjectFormatter<String> {

    @Override
    public String format(String raw) throws Exception {
        return raw.replaceAll("首页» ", "");
    }

    @Override
    public Class<String> clazz() {
        return String.class;
    }

    @Override
    public void initParam(String[] extra) {

    }
}
