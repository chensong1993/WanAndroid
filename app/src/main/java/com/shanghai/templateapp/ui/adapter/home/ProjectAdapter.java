package com.shanghai.templateapp.ui.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.models.entity.ProjectEntity;
import com.shanghai.templateapp.models.entity.WanArticleEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/26 18:12
 */
public class ProjectAdapter extends BaseQuickAdapter<WanArticleEntity,BaseViewHolder>{
    public ProjectAdapter( @Nullable List<WanArticleEntity> data) {
        super(R.layout.item_project, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WanArticleEntity item) {
        helper.setText(R.id.tv_project,item.getTitle());
    }
}
