package com.shanghai.templateapp.models.entity;

/**
 * @author chensong
 * @date 2019/4/16 12:00
 */
public class FriendEntity {
    private String icon;

    private int id;

    private String link;

    private String name;

    private int order;

    private int visible;

    public void setIcon(String icon){
        this.icon = icon;
    }
    public String getIcon(){
        return this.icon;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setOrder(int order){
        this.order = order;
    }
    public int getOrder(){
        return this.order;
    }
    public void setVisible(int visible){
        this.visible = visible;
    }
    public int getVisible(){
        return this.visible;
    }

}
