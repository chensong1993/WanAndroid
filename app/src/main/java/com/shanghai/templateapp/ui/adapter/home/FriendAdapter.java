package com.shanghai.templateapp.ui.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.models.entity.FriendEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/16 13:43
 */
public class FriendAdapter extends BaseQuickAdapter<FriendEntity,BaseViewHolder> {
    public FriendAdapter( @Nullable List<FriendEntity> data) {
        super(R.layout.item_article_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, FriendEntity item) {
        helper.setText(R.id.tv_news_detail_title, item.getName());

    }
}
