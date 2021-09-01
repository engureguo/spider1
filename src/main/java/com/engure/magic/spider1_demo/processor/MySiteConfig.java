package com.engure.magic.spider1_demo.processor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Site;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "site")
@Slf4j
public class MySiteConfig {

    //@Value("${site.timeOut}")
    private Integer timeOut;

    //@Value("${site.retryTimes}")
    private Integer retryTimes;

    //@Value("${site.sleepTime}")
    private Integer sleepTime;

    //@Value("${site.charset}")
    private String charset;

    //@Value("${site.userAgent}")
    private String userAgent;

    //@Value("${site.cookies")
    private Map<String, String> cookies;

    //@Value("${site.headers}")
    private Map<String, String> headers;

    @Bean
    public Site site() {

        Site site = new Site().setRetryTimes(retryTimes).setTimeOut(timeOut).setSleepTime(sleepTime)
                .setCharset(charset)
                .setUserAgent(userAgent);

        //log.info(cookies.toString());
        //log.info(headers.toString());

        for (String name : cookies.keySet()) {
            site.addCookie(name, cookies.get(name));
        }

        for (String name : headers.keySet()) {
            site.addHeader(name, headers.get(name));
        }

        return site;
    }

}
