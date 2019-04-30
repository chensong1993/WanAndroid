package com.shanghai.templateapp.ui.adapter.collect;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.models.entity.CollectEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/18 14:32
 */
public class CollectListAdapter extends BaseQuickAdapter<CollectEntity, BaseViewHolder> {
    public CollectListAdapter( @Nullable List<CollectEntity> data) {
        super(R.layout.item_article_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectEntity item) {

    }
}
