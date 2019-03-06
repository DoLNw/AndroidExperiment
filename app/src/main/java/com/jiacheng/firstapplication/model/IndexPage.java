package com.jiacheng.firstapplication.model;

import java.util.ArrayList;

public class IndexPage {
    static final String SERVER = "http://localhost:8080/giftems";
    public String[] recommendTop;
    public String recommendBottomLeft;
    public String recommendBottomRight;
    public String giftCommon;
    public String strategy;

    public IndexPage(ArrayList<IndexPageResource>settings) {
        ArrayList<String>recommendTopList = new ArrayList<String>();
        for(IndexPageResource s : settings){
            switch (s.place){
                case 1:
                    recommendTopList.add(SERVER + s.picUrl);
                    break;
                case 2:
                    recommendBottomLeft = SERVER + s.picUrl;
                    break;
                case 3:
                    recommendBottomRight = SERVER + s.picUrl;
                    break;
                case 4:
                    giftCommon = SERVER + s.picUrl;
                    break;
                case 5:
                    strategy = SERVER + s.picUrl;
                    break;
            }
        }
        recommendTop = recommendTopList.toArray(new String[recommendTopList.size()]);}
}
