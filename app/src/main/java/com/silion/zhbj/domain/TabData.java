package com.silion.zhbj.domain;

import java.util.List;

/**
 * Created by silion on 2016/8/17.
 */
public class TabData {
    public int retcode;
    public TapDetail data;

    @Override
    public String toString() {
        return "TabData{" +
                "data=" + data +
                '}';
    }

    public class TapDetail {
        public String more;
        public String title;
        public List<TabNewsData> news;
        public List<TopNewsData> topnews;

        @Override
        public String toString() {
            return "TapDetail{" +
                    "title='" + title + '\'' +
                    ", news=" + news +
                    ", topnews=" + topnews +
                    '}';
        }
    }

    public class TabNewsData {
        public String id;
        public String listimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "TabNewsData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    public class TopNewsData {
        public String id;
        public String topimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "TopNewsData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }
}
