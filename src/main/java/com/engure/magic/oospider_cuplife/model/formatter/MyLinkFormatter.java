package com.engure.magic.oospider_cuplife.model.formatter;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public class MyLinkFormatter implements ObjectFormatter<String> {
    @Override
    public String format(String raw) {
        return "https://www.cup.edu.cn/culture" + raw + ".htm";
    }

    @Override
    public Class<String> clazz() {
        return String.class;
    }

    @Override
    public void initParam(String[] extra) {

    }
}
