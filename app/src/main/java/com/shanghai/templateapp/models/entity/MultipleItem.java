package com.shanghai.templateapp.models.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/28 14:55
 */
public class MultipleItem implements MultiItemEntity {
    public static final int STAR = 1;
    public static final int ARTICLE = 2;
    private int itemType;
    WanArticleEntity entities;


    public MultipleItem(int itemType, WanArticleEntity entities) {
        this.itemType = itemType;
        this.entities = entities;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public WanArticleEntity getEntities() {
        return entities;
    }

    public void setEntities(WanArticleEntity entities) {
        this.entities = entities;
    }
}
