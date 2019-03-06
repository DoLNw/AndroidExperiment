package com.jiacheng.firstapplication.model;

import java.util.ArrayList;

public class Gift<arrayli> {
    public int id;
    public String name;
    public String remark;
    public int likes;
    public int sales;
    public boolean collected;
    public ArrayList<GiftStyle>styles = new ArrayList<GiftStyle>();
}
