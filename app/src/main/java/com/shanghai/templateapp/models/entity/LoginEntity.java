package com.shanghai.templateapp.models.entity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 11:27
 */
public class LoginEntity {

//    private List<ChapterTops> chapterTops ;
//
//    private List<CollectIds> collectIds ;

    private String email;

    private String icon;

    private int id;

    private String password;

    private String token;

    private int type;

    private String username;

//    public void setChapterTops(List<ChapterTops> chapterTops){
//        this.chapterTops = chapterTops;
//    }
//    public List<ChapterTops> getChapterTops(){
//        return this.chapterTops;
//    }
//    public void setCollectIds(List<CollectIds> collectIds){
//        this.collectIds = collectIds;
//    }
//    public List<CollectIds> getCollectIds(){
//        return this.collectIds;
//    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
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
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

}
