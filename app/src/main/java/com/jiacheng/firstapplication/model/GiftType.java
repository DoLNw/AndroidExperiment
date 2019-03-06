package com.jiacheng.firstapplication.model;

public class GiftType implements Comparable<GiftType> {
    public int id;
    public String name;
    public String pic;
    public int orderNumber;

    public int compareTo(GiftType another) {
        return orderNumber < another.orderNumber ? -1 : (orderNumber == another.orderNumber ? 0 : 1);
    }
}
