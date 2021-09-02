package com.engure.magic.oospider_cuplife.util;

import com.engure.magic.oospider_cuplife.model.NoticeModel;
import com.engure.magic.oospider_cuplife.pojo.Article;

public class Model2Pojo {

    public static Article model2(NoticeModel noticeModel) {
        Article a = new Article();
        a.setTitle(noticeModel.getTitle());
        a.setArticle(noticeModel.getArticle());
        a.setPubdate(noticeModel.getPubDate());
        return a;
    }

}
