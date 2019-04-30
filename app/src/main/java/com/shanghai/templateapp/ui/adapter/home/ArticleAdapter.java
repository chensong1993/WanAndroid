package com.shanghai.templateapp.ui.adapter.home;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.models.entity.BannerEntity;
import com.shanghai.templateapp.models.entity.MultipleItem;
import com.shanghai.templateapp.models.entity.WanArticleEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author chensong
 * @date 2019/4/15 11:10
 */
public class ArticleAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    public ArticleAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ARTICLE, R.layout.item_article_list);
        addItemType(MultipleItem.STAR, R.layout.item_star);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.ARTICLE:
                Date date = new Date(item.getEntities().getPublishTime());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date);
                String top, chapterName, collect;

                if (item.getEntities().getType() == 1) {
                    top = "置顶";
                } else {
                    top = "";
                }
                if ("".equals(item.getEntities().getSuperChapterName())) {
                    chapterName = "";
                } else {
                    chapterName = "/" + item.getEntities().getSuperChapterName();
                }
                if (item.getEntities().isCollect()) {
                    collect = "取消收藏";
                } else {
                    collect = "收藏";
                }
                helper.setText(R.id.tv_news_detail_title, item.getEntities().getTitle())
                        .setText(R.id.tv_author, item.getEntities().getAuthor())
                        .setText(R.id.tv_time, time + "发布")
                        .setText(R.id.tv_news_title, chapterName)
                        .setText(R.id.tv_top, top);

                break;
            case MultipleItem.STAR:
                helper.setText(R.id.tv_news_detail_title, item.getEntities().getTitle())
                        .setText(R.id.tv_news_title, item.getEntities().getAuthor())
                        .addOnClickListener(R.id.tv_collect);
                break;
            default:
                break;
        }
    }
}
