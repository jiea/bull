package com.jiea.bull.common.enums;

public enum UserStatusType {

    NORMAL(1, "正常"), DISABLE(2, "禁用");

    private int index;

    private String name;

    UserStatusType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
