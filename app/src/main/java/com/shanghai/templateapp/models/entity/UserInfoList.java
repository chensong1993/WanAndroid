package com.shanghai.templateapp.models.entity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/19 15:16
 */
public class UserInfoList {
    private List<UserInfoEntity> lists ;

    public void setLists(List<UserInfoEntity> lists){
        this.lists = lists;
    }
    public List<UserInfoEntity> getLists(){
        return this.lists;
    }

}
