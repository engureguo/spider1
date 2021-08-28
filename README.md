# spider1

爬虫试写

---

> 对于需要滚动的页面，它的原理是JS动态加载，怎么爬取内容？

1. 使用自动化工具
2. 直接爬取网页获取的内容只有一小部分，可以手工下拉页面后保存静态页面，程序开始运行时先加载和解析页面



> 使用切面统计爬虫运行时间

```java
@Record
public void startCrawl() {

    String url = "https://www.mm618.com/like";

    new MySpider(new ActualLikePageProcessor())
            //设置线程数量
            .thread(Runtime.getRuntime().availableProcessors() * 10)
            .addUrl(url)
            .run();

}
```



> 线程池的线程数量怎么设置？

IO密集型，比较不同线程下的性能：

```
文件不存在，休眠200ms，爬取下载到 f:/data1/
------------------
80线程    13s,15s,13s
8个线程   81s,68s,77s
```

IO阻塞，CPU时间浪费，增加线程以增加效率



