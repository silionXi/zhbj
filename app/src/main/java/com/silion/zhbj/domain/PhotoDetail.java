package com.silion.zhbj.domain;

import java.util.List;

/**
 * Created by silion on 2016/8/22.
 */
public class PhotoDetail {
    public String retcode;
    public PhotoData data;

    public class PhotoData {
        public String more;
        public List<PhotoNew> news;
    }

    public class PhotoNew {
        public String id;
        public String listImage;
        public String pubdate;
        public String title;
    }
}
