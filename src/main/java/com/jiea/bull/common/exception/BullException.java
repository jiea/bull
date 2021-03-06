package com.jiea.bull.common.exception;

public class BullException extends RuntimeException{

    private String msg;

    private int code = 500;

    public BullException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BullException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
