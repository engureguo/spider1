package com.engure.mm.spider_like2.processor;

import com.engure.mm.spider_like2.MySpider;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Slf4j
public class ActualLikePageProcessor implements LikePageProcessor {

    @Override
    public void process(Page page) {

        String url = page.getRequest().getUrl();

        if (url.startsWith("https://www.mm618.com/like")) {
            //主页

            // 如果使用正则表达式，url可能会重复，
            // 使用 xpath 拿到所有albums链接

            List<String> albumsLinks = page.getHtml().xpath("/html/body/section/div/div/article/h2/a/@href").all();
            List<String> albumsTitle = page.getHtml().xpath("/html/body/section/div/div/article/h2/a/text()").all();

            //System.out.println("所有 albums ---------------------------------------");
            for (int i = 0; i < albumsLinks.size(); i++) {

                try {
                    //System.out.println("影集：" + albumsTitle.get(i) + " --> " + albumsLinks.get(i));
                    log.info("影集：" + albumsTitle.get(i) + " --> " + albumsLinks.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            page.addTargetRequests(albumsLinks.subList(0, 5));
            //page.addTargetRequests(albumsLinks);

            page.setSkip(true);
        } else if (url.matches("https://www\\.mm618\\.com/albums/(\\w+)")) {
            // 图片集
            //会跳转不同样式的页面

            String xpath1 = "/html/body/section/div[1]/div/article/figure/ul/li/figure/img/@src";
            String xpath2 = "/html/body/section/div[1]/a/@href";
            List<String> photoLinks1 = page.getHtml().xpath(xpath1).all();//   /123123/abc.jpg, /123123/efg.jpg.....
            List<String> photoLinks2 = page.getHtml().xpath(xpath2).all();//   /albums/123456/2, /albums/123456/3....
            //System.out.println("影集 ------------------------------------");
            //System.out.println("获取影集！" + url);
            //System.out.println(photoLinks1);
            //System.out.println(photoLinks2);
            log.info("获取影集！" + url);
            log.info(photoLinks1.toString());
            log.info(photoLinks2.toString());

            page.addTargetRequests(photoLinks1);
            page.addTargetRequests(photoLinks2);

            if (photoLinks1.size() == 0 && photoLinks2.size() != 0) {
                try {
                    //第一张图片
                    String img0 = page.getHtml().xpath("/html/body/section/article/p/a/img/@src").all().get(0);
                    page.addTargetRequest(img0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            page.setSkip(true);
        } else if (url.matches("https://www\\.mm618\\.com/albums/(\\w+)/(\\w+)")) {

            //提取图片
            try {
                String imgXpath = page.getHtml().xpath("/html/body/section/article/p/a/img/@src").all().get(0);
                page.addTargetRequest(imgXpath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            page.setSkip(true);
        } else if (url.matches("https://img\\.mm618\\.com/photos/(\\w+)/(\\w+)\\.jpg")) {
            //根据url存储图片

            //System.out.println("图片 ---------------------------------------------");
            int start = url.indexOf("photos");
            String[] imgInfo = url.substring(start).split("/");
            //System.out.println(url + " ---> " + Arrays.toString(imgInfo));

            //保存图片？

            try {
                String storePath = MySpider.STORE_PATH + "\\" + imgInfo[1] + "\\" + imgInfo[2];
                //System.out.println(storePath);

                //创建存放文件的目录    PATH\\123456\\
                String storeDirectory = MySpider.STORE_PATH + "\\" + imgInfo[1];
                File directory = new File(storeDirectory);
                if (!directory.exists()) directory.mkdir();

                byte[] content = page.getBytes();
                File f = new File(storePath);
                if (f.exists()) f.delete();
                f.createNewFile();//不会自动创建文件夹
                FileOutputStream fileOutputStream = new FileOutputStream(f);
                fileOutputStream.write(content);
                fileOutputStream.flush();
                fileOutputStream.close();

                //System.out.println("写入成功！" + storePath);
                log.info("写入成功！" + storePath);
            } catch (Exception e) {
                //System.out.println("保存失败！！！！！");
                log.info("保存失败！！！！！");
                e.printStackTrace();
            }

            page.setSkip(true);
        }


    }

    private final Site site = Site.me()
            .setCharset("UTF-8")
            .setRetryTimes(3).setTimeOut(1000 * 5).setSleepTime(200);

    @Override
    public Site getSite() {
        return site;
    }
}
