package com.engure.magic.spider_like2.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class RecordAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.engure.magic.spider_like2.anno.Record)")
    public void doPointCut() {

    }

    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        long s = System.currentTimeMillis();
        logger.info("开启爬取 {}", new Date());

        try {
            return point.proceed();
        } finally {

            long e = System.currentTimeMillis();
            logger.info("结束爬取 {}，用时 {} s", new Date(), (e - s) / 1000);

        }

    }

}
