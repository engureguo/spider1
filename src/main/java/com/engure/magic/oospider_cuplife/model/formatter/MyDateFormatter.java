package com.engure.magic.oospider_cuplife.model.formatter;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.model.formatter.ObjectFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换
 */
@Slf4j
public class MyDateFormatter implements ObjectFormatter<Date> {

    String pattern;

    /**
     * @param raw 提出的数据
     * @return 转化结果
     */
    @Override
    public Date format(String raw) {
        //log.info("raw: " + raw);
        try {
            // 线程安全问题？
            return new SimpleDateFormat(pattern).parse(raw.trim().replaceAll("发布时间：", ""));
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error("日期解析失败：" + raw);
        }
        return new Date(0L);
    }

    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

    @Override
    public void initParam(String[] extra) {
        //log.info(extra[0]);
        pattern = extra[0];
    }
}
