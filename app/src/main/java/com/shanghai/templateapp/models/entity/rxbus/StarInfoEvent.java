package com.shanghai.templateapp.models.entity.rxbus;

/**
 * @author chensong
 * @date 2019/4/23 15:55
 */
public class StarInfoEvent {
    int id;
    boolean collect;


    public StarInfoEvent(int id, boolean collect) {
        this.id = id;
        this.collect = collect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }


}
