# spider1

爬虫试写

---

### 配置 log4j



webmagic 自定制的 slf4j-log4j12（是 slf4j 的实现）

<img src="images/README.assets/image-20210831174447794.png" alt="image-20210831174447794" style="zoom:80%;" />



默认情况下，logback 和 slf4j-log4j12 会产生冲突：

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/F:/MavenRepo/ch/qos/logback/logback-classic/1.2.5/logback-classic-1.2.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/F:/MavenRepo/org/slf4j/slf4j-log4j12/1.7.32/slf4j-log4j12-1.7.32.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
```



自定制 log4j：

①排除 logback 依赖和 slf4j-log4j12

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
	<!--排除logback依赖-->
	<exclusions>
		<exclusion>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<dependency>
	<groupId>us.codecraft</groupId>
	<artifactId>webmagic-core</artifactId>
	<version>0.7.3</version>
</dependency>

<dependency>
	<groupId>us.codecraft</groupId>
	<artifactId>webmagic-extension</artifactId>
	<version>0.7.3</version>
	<exclusions>
		<exclusion>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```

②创建 log4j.properties

```properties
# 全局日志级别设定 ,file
log4j.rootLogger=INFO, stdout, file

# 自定义包路径LOG级别
log4j.logger.org.quartz=WARN, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{MM-dd HH:mm:ss} >> [%p] >> [%thread] >> %m%n

# Output to the File
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=F:/data1/webmagic.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{MM-dd HH:mm:ss} >> [%p] >> [%thread] >> %C.%M() >> %m%n
```



### 对于需要滚动的页面，它的原理是JS动态加载，怎么爬取内容？

1. 使用自动化工具
2. 直接爬取网页获取的内容只有一小部分，可以手工下拉页面后保存静态页面，程序开始运行时先加载和解析页面



### 使用注解+切面统计爬虫运行时间

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



### 线程池的线程数量怎么设置？

IO密集型，比较不同线程下的性能：

```
文件不存在，休眠200ms，爬取下载到 f:/data1/
------------------
80线程    13s,15s,13s
8个线程   81s,68s,77s
```

IO阻塞，CPU时间浪费，增加线程以增加效率



### 集成布隆过滤器，实现URL去重

guava-BloomFilter https://blog.csdn.net/qq_43341057/article/details/120027164

参照已知的 `BloomFilterDuplicateRemover` 进行实现：

```java
public class MyBloomFilterDuplicateRemover implements DuplicateRemover {

    public MyBloomFilterDuplicateRemover(int size) {
        this(size, 0.01);
    }

    public MyBloomFilterDuplicateRemover(int size, double fpp) {
        this.size = size;
        this.fpp = fpp;
        rebuild();
    }

    private AtomicInteger counter;

    private int size;

    private double fpp;

    private BloomFilter<String> bloomFilter;

    private void rebuild() {
        counter = new AtomicInteger();
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), size, fpp);
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        boolean duplicate = bloomFilter.mightContain(request.getUrl());
        if (!duplicate) { //不存在
            bloomFilter.put(request.getUrl());
            counter.incrementAndGet();
        }
        return duplicate;
    }

    //重置布隆过滤器，貌似没用上
    @Override
    public void resetDuplicateCheck(Task task) {
        rebuild();
    }

    //获取布隆集合元素数量，貌似没用上
    @Override
    public int getTotalRequestsCount(Task task) {
        return counter.get();
    }
}
```



### 多线程控制

blockingQueue、线程池、数据合并保存，哪些地方有线程安全问题？

> 多个线程同时合并保存一条数据，存在线程安全问题
>
> 解决方法：前者将抓取的数据传递给后者，后者爬取结束后统一做数据和并保存，而不是分别做
>
> https://blog.csdn.net/qq_46122292/article/details/118515916



### 抽取爬取逻辑到接口







### 基于注解的爬虫



```java

//////////////爬虫启动，相比之前的爬虫传入PageProcessor，这里传入Model类

OOSpider.create(site, pageModelPipeline, NoticeModel.class)
        .setScheduler(
                new QueueScheduler().setDuplicateRemover(new MyBloomFilterDuplicateRemover(expectedUrlNum)))
        .thread(Runtime.getRuntime().availableProcessors() * 10)
        .addUrl("https://www.cup.edu.cn/culture/bfsd/index.htm")
        .run();


////////////model类

@Data
@AllArgsConstructor
@NoArgsConstructor
//1.下载并提取本页   2.匹配本页中的url，会继续下载页面
@TargetUrl("https://www.cup.edu.cn/culture/bfsd/(\\w{32}).htm")
//搜索本页中的匹配url，不会在本页提取数据，此注解可选
@HelpUrl(value = "https://www.cup.edu.cn/culture/bfsd/(\\w{32}).htm") 
public class NoticeModel {

    //定义提取路径
    //notnull=true，如果获取失败则丢弃本页结果
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[1]/h3/text()", notNull = true)
    private String title;

    //类型转换：string+pattern -> date
    @Formatter(value = "yyyy-MM-dd", formatter = MyDateFormatter.class)
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[2]/span/text()")
    private Date pubDate;

	//提取文章内容（html块 -> string），使用 allText()，貌似是webmagic自实现的⭐⭐
    //看源码看出来的，作者 codecraft4 有一处是这样用😄
    @ExtractBy(value = "/html/body/div[3]/article/div[2]/section/div/div[3]/allText()")
    private String article;
    
}

```



> `HelpUrl/TargetUrl`是一个非常有效的爬虫开发模式，TargetUrl 是我们最终要抓取的 URL，最终想要的数据都来自这里；而 HelpUrl 则是为了发现这个最终 URL，我们需要访问的页面。几乎所有垂直爬虫的需求，都可以归结为对这两类 URL 的处理：
>
> - 对于博客页，HelpUrl 是列表页，TargetUrl 是文章页。
> - 对于论坛，HelpUrl 是帖子列表，TargetUrl 是帖子详情。
> - 对于电商网站，HelpUrl 是分类列表，TargetUrl 是商品详情。
>
> 在这个例子中，TargetUrl 是最终的项目页，而 HelpUrl 则是项目搜索页，它会展示所有项目的链接。

```java
//1.提取本页  2.继续正则匹配，会继续下载页面
@TargetUrl("https://www.cup.edu.cn/culture/index.htm")

//在本页中正则匹配，不会在本页提取数据，此注解可选
@HelpUrl("https://www.cup.edu.cn/culture/(\\w+)/index.htm")
```



将爬取逻辑整合在 model 上，可以自定义 ObjectFormatter 处理爬取的数据（类型转换等等），可以很方便地将对象保存到数据库，或者说爬虫要爬的就是信息的集合，而不是零散的数据。



对于注解模式无法完全解决问题，作者提供了 `AfterExtractor` 接口，可以在数据抽取完后处理一些特殊的逻辑



感觉这种一个 model 对象就相当于 pageprocessor 中的一个 if 分支？



### 添加pipeline，将结果保存至数据库

他山之石：https://gitee.com/complone/zhihuMagicCrawel









