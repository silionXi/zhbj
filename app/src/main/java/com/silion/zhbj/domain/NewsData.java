package com.silion.zhbj.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/11.
 */
public class NewsData {
    public int retcode;
    public ArrayList<NewsMenuData> data;

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }

    public class NewsMenuData {
        public String id;
        public String title;
        public String url;
        public int type;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }

        public ArrayList<NewsTabData> children;
    }

    public class NewsTabData {
        public String id;
        public String title;
        public String url;
        public int type;

        @Override
        public String toString() {
            return "TabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }
}
