package com.shanghai.templateapp.models.entity;

import java.io.Serializable;

/**
 * @author chensong
 * @date 2019/2/18 11:49
 */
public class ApiResponse<T> implements Serializable {
    private int count;
    private String next;
    private String prev;
    private T results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
