package com.shanghai.templateapp.models.entity.rxbus;

/**
 * @author chensong
 * @date 2019/4/22 17:42
 */
public class CollectEvent {
    boolean isCollect;

    public CollectEvent(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
}
