package com.engure.magic.spider_weibo.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Slf4j
@Component
public class HotRealtimePageProcessor implements PageProcessor {

    @Autowired
    private Site site;

    @Override
    public void process(Page page) {

        if (page.getRequest().getUrl().startsWith("https://weibo.com/a/hot/realtime")) {

            //话题
            List<String> title = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/h3/a/text()").all();
            List<String> link = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/h3/a/@href").all();
            //发布作者
            List<String> author = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/a[2]/span/text()").all();
            List<String> authorlink = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/a[2]/@href").all();
            //评论点赞分享
            List<String> share = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/span[6]/em[2]/text()").all();
            List<String> commes = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/span[4]/em[2]/text()").all();
            List<String> likes = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/span[2]/em[2]/text()").all();
            //发布时间
            List<String> pubDate = page.getHtml().xpath("/html/body/div/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div/span[1]/text()").all();

            try {

                log.info("开始打印数据~~~");

                for (int i = 0; i < title.size(); i++) {
                    log.info(title.get(i) + "," + link.get(i) + "," + share.get(i) + "," + pubDate.get(i) + "," + author.get(i) + ","
                            + authorlink.get(i) + "," + commes.get(i) + "," + likes.get(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("爬取数量不一致~~~");
            }

        }

        page.setSkip(true);

    }

    @Override
    public Site getSite() {
        return site;
    }
}


/*

09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #张雨绮哭戏控制度#,c7fc0dd649ea9f1c_0.html?type=grab,24179,今天 12:35,新浪娱乐,//weibo.com/entpaparazzi,1225,168
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #北京环球度假区玩一趟花多少钱#,ff88c0aae2f5b132_0.html?type=grab,10392,今天 15:13,金主哥,//weibo.com/u/6511541439,92,13
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #恒大集团举行保交楼军令状签署大会#,ea9a78f3d02f0d50_0.html?type=grab,20779,今天 16:53,秋上,//weibo.com/u/6452812829,167,45
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #原来包子是按皮算重量的#,db43c9733f7db712_0.html?type=grab,26986,今天 14:35,天秀bot,//weibo.com/u/6593871464,3025,7004
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #郑州别下了#,01b205d173cf40f6_0.html?type=grab,83011,今天 12:25,新浪河南,//weibo.com/xinlanghenan,48,85
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #月球上的兔子有多可爱#,d16fbec3433fe902_0.html?type=grab,52040,今天 15:09,逗一个逗,//weibo.com/u/5691272626,2986,4970
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #上海消保委点名优酷爱奇艺#,7a0757d54c10fd8b_0.html?type=grab,69697,今天 18:18,新浪娱乐,//weibo.com/entpaparazzi,189,169
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> 长沙暴雨,2fa23fbec019538d_0.html?type=grab,29967,7月23日 17:24,冲浪少年皮同学,//weibo.com/u/5017913220,2245,357
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #孙俪拍理想之城跑步戏冲出现场#,d46b60b9f1c7f04b_0.html?type=grab,1510,今天 18:05,娱乐潮流,//weibo.com/u/5898274548,243,1426
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #体检发现结节怎么办#,484160a185ad5964_0.html?type=grab,13146,今天 13:20,每日经济新闻,//weibo.com/mrjjxw,1549,2101
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #与君歌大结局太扎心了#,02f9f73a1a9f1967_0.html?type=grab,6726,今天 16:00,浪里追剧,//weibo.com/u/6337707488,1220,899
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #西安草莓音乐节官宣#,c9657ce1349e2632_0.html?type=grab,424844,今天 11:00,新浪音乐,//weibo.com/musichut,32904,4263
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #一张退伍战士的请假条#,4e7a04548d2ad080_0.html?type=grab,29213,今天 13:18,人民日报,//weibo.com/rmrb,1166,1039
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> 芭莎超模汉服封面,e56dd8e0784575c0_0.html?type=grab,306,今天 12:49,小象王国,//weibo.com/xiangchong1015,58,66
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #马龙孙颖莎全红婵全运会证件照#,b2be3344c7688dad_0.html?type=grab,72339,今天 14:01,人民日报,//weibo.com/rmrb,2543,1128
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #太了吧体#,aebc941f0a0c4843_0.html?type=grab,78990,今天 11:42,一起来迷惑,//weibo.com/u/5963800461,4987,3400
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #开学第一课#,72427bee8b7d9326_0.html?type=grab,8535,今天 17:03,央视新闻,//weibo.com/cctvxinwen,617,823
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #原来小猫咪也有坏心思#,03204749f7f242f1_0.html?type=grab,2721,今天 15:14,主子万岁,//weibo.com/u/5859858228,233,1339
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #兰州交大遇害研究生同学发声#,8685a493a3b06529_0.html?type=grab,184381,今天 12:58,漩涡视频,//weibo.com/u/7467277921,6507,3761
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #女生千万别穿睡裙睡觉#,31e2c3d86b2e5865_0.html?type=grab,52186,今天 15:45,被牛撞了个满怀,//weibo.com/u/5277137334,4,15
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #今起夫妻过户子女继承免征契税#,c52ab370bd0a7abe_0.html?type=grab,7387,今天 14:46,人民法院报,//weibo.com/renminfayuanbao,849,1037
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #在家里上了个大学#,eb3800b1a098b483_0.html?type=grab,34375,今天 17:04,kimmy大姐姐,//weibo.com/u/6280983752,4,20
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #女子用假表掉包男友40万真表#,1f43b8a0df6ea3d4_0.html?type=grab,57293,今天 16:22,头条新闻,//weibo.com/breakingnews,3203,356
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> LCK,edb5e09e34dd5880_0.html?type=grab,1636,今天 18:34,玩加电竞LOL,//weibo.com/wanpluslol,347,370
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #北京环球度假区小黄人雪糕40元一个#,9d7e55005c3aeebf_0.html?type=grab,9362,57分钟前,北京生活导航,//weibo.com/u/5522461484,35,23
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #王一丁丧失生育能力#,4aa7dd06ce16d561_0.html?type=grab,4473,今天 17:24,鲜娱君,//weibo.com/209097465,712,719
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #学校出现学生欺凌情况将被问责#,e509ce162d86362e_0.html?type=grab,160366,今天 10:51,人民网,//weibo.com/renminwang,6152,2487
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #郎平卸任中国女排主教练#,8120b978cc330925_0.html?type=grab,4457,今天 13:39,中国女排,//weibo.com/u/3982084673,265,190
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #北京环球影城票价公布#,08546b4965cb0b16_0.html?type=grab,23833,今天 17:20,财经网,//weibo.com/caijing,3696,3358
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #取消大小周后降薪公司需要补偿吗#,c729322c043d092d_0.html?type=grab,6170,今天 18:32,财经网科技,//weibo.com/tigeeker,2,2
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #头等舱男子疑辱骂空姐手脏#,89c5dd7f629313f5_0.html?type=grab,16138,今天 17:52,刘能,//weibo.com/u/1667976775,179,286
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #腾讯视频优化超前点播#,3484194c39744d83_0.html?type=grab,69697,今天 18:18,新浪娱乐,//weibo.com/entpaparazzi,189,169
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #厦门连续送4小时外卖20分钟内不派单#,3b31cdd12cdbbc2a_0.html?type=grab,25002,今天 17:30,IT互联网那点事,//weibo.com/u/1839300134,63,22
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #抖音下线明星爱豆榜和明星话题榜#,d943b29c15a66db8_0.html?type=grab,13490,30分钟前,财经网科技,//weibo.com/tigeeker,480,91
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #扫黑风暴何勇炸烂尾楼#,66436f9dbc466390_0.html?type=grab,3755,今天 10:17,微博电视剧,//weibo.com/52dsj,368,328
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #李钟硕林允儿合作TVN新剧#,f7ae892a35cfdc1e_0.html?type=grab,105895,今天 11:19,王牌星探,//weibo.com/u/3925184557,8960,9731
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #解密饭圈追星平台#,65cf583232e87bd7_0.html?type=grab,884235,8月30日 07:30,央视新闻,//weibo.com/cctvxinwen,50407,65291
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #开学第一天#,becc243b16ba2fda_0.html?type=grab,96948,今天 07:35,央视新闻,//weibo.com/cctvxinwen,7314,1659
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #买个床上的桌子写作业#,dbea5f5ad9b6597a_0.html?type=grab,810,今天 12:59,果儿Dora,//weibo.com/u/6568010226,674,1659
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #塔利班叫停罂粟种植#,9fc04a80e9e6553e_0.html?type=grab,33798,今天 08:20,央视网快看,//weibo.com/cntv2011lh,2059,605
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #张艺兴王传君捕鱼行动路透#,3d8500ad2eedcb45_0.html?type=grab,13492,今天 11:22,娱乐榜姐,//weibo.com/u/2653906910,49,95
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #离开梗就不会说话#,3690bf3660a90fd9_0.html?type=grab,6180,今天 15:18,娱乐扒星,//weibo.com/u/2386406982,9,45
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #女主人拿走鹅蛋被公鹅追咬大半年#,64ca424b09c065c0_0.html?type=grab,9187,今天 12:29,清南师兄,//weibo.com/qinghuanandu,939,2148
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #开学第一天家长统一姿势#,ff5f3583bc79bce3_0.html?type=grab,22063,今天 12:54,胡萝卜娱乐,//weibo.com/u/6364347879,65,22
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #樊振东机场和粉丝互拍#,5bb852c7f07625f4_0.html?type=grab,29795,今天 14:37,娱星教父,//weibo.com/xue830717,1953,1449
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #我永远吃细节这套#,312dacaad7c8375c_0.html?type=grab,163140,今天 11:08,大猪蹄子研究所,//weibo.com/u/6700634492,12692,16731
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #与君歌的格局有多大#,8a1de5b154f52d56_0.html?type=grab,32922,今天 14:04,张予曦,//weibo.com/raylizhangyuxi,22481,21236
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #英雄联盟10周年表演赛阵容#,fdcead2014821973_0.html?type=grab,24607,今天 14:01,英雄联盟手游,//weibo.com/lolmobile,2202,7043
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #香菜冰皮月饼#,2748062f9c565b9b_0.html?type=grab,23643,今天 11:59,工具人阿昌,//weibo.com/u/6691387330,7096,3105
09-01 20:05:15 >> [INFO] >> [pool-1-thread-1hread] >> #李子柒品牌助理回应报警#,6384feec3d639f30_0.html?type=grab,83072,今天 08:57,澎湃新闻,//weibo.com/thepapernewsapp,3488,252




 */






