package com.shanghai.templateapp.models.entity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/26 16:44
 */
public class ProjectEntity {
    private List<WanArticleEntity> articles;

    private int cid;

    private String name;

    public void setArticles(List<WanArticleEntity> articles) {
        this.articles = articles;
    }

    public List<WanArticleEntity> getArticles() {
        return this.articles;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return this.cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
