package com.jiacheng.firstapplication.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {

    public static ArrayList<String> getImgs(String html) {
        ArrayList<String> list = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements imgs = doc.select("img");
        Iterator<Element> it = imgs.iterator();
        while (it.hasNext()) {
            Element img = it.next();
            String src = img.attr("src");
            if (!StringUtils.isEmpty(src))
                list.add(src);
        }
        return list;
    }
}
