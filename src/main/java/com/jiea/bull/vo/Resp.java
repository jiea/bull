package com.jiea.bull.vo;




import org.apache.http.HttpStatus;

import java.io.Serializable;

public class Resp<T> implements Serializable {

    private static final int OK_CODE = 200;
    private static final String OK_MSG = "success";

    private int code;

    private String msg;

    private T data;

    public Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Resp ok(){
        return new Resp(OK_CODE, OK_MSG);
    }

    public static Resp ok(String msg){
        return new Resp(OK_CODE, msg);
    }

    public static<T> Resp ok(T data){
        return new Resp(OK_CODE, OK_MSG, data);
    }

    public static<T> Resp ok(String msg, T data){
        return new Resp(OK_CODE, msg, data);
    }

    public static Resp error(){
        return new Resp(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统错误, 请联系管理员.");
    }

    public static Resp error(String msg){
        return new Resp(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Resp error(int code, String msg){
        return new Resp(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
