package com.shanghai.templateapp.models.entity;

import java.io.Serializable;

/**
 * @author chensong
 * @date 2019/2/18 11:49
 */
public class ApiResponse<T> implements Serializable {

    private T data;
    private int errorCode;

    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }


}
