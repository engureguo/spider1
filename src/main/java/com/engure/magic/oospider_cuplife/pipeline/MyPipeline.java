package com.engure.magic.oospider_cuplife.pipeline;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.engure.magic.oospider_cuplife.model.NoticeModel;
import com.engure.magic.oospider_cuplife.pojo.Article;
import com.engure.magic.oospider_cuplife.service.IArticleService;
import com.engure.magic.oospider_cuplife.util.Model2Pojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


/**
 * 问题：
 * * 有的文章有标题没得内容，====》是纯照片的文章，不管了
 * * 使用 saveOrUpdate 有哪些问题？
 */


@Component
@Slf4j
public class MyPipeline implements PageModelPipeline<Object> {

    @Autowired
    private IArticleService articleService;

    @Override
    public void process(Object o, Task task) {

        if (o instanceof NoticeModel) {
            NoticeModel model = (NoticeModel) o;
            Article article = Model2Pojo.model2(model);
            try {
                boolean saveOrUpdate = articleService.saveOrUpdate(article,
                        new UpdateWrapper<Article>()
                                .eq("title", article.getTitle()));
                log.info("saveOrUpdate " + saveOrUpdate + ", title = " + article.getTitle());

            } catch (Exception e) {
                e.printStackTrace();
                log.error("SQL语句执行失败>>>" + e.getCause());
            }
        }

    }
}
