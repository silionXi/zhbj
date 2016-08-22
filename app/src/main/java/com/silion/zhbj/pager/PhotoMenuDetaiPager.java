package com.silion.zhbj.pager;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silion.zhbj.R;
import com.silion.zhbj.domain.PhotoDetail;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/12.
 */
public class PhotoMenuDetaiPager extends BaseMenuDetailPager {
    private final static String DEFAULT_DATA = "{\n" +
            "    \"data\": {\n" +
            "        \"countcommenturl\": \"http://zhbj.qianlong.com/client/content/countComment/\",\n" +
            "        \"more\": \"http://zhbj.qianlong.com/static/api/news/10003/list_2.json\",\n" +
            "        \"news\": [\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/72/82772/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/82772\",\n" +
            "                \"id\": 82772,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/11/07/70/476518773M7R.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356JDGO.jpg\",\n" +
            "                \"pubdate\": \"2014-11-07 11:40\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/11/07/79/485753989TVL.jpg\",\n" +
            "                \"title\": \"北京·APEC绚丽之夜\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/11/07/7743665E4E6B10766F26.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/90/79290/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/79290\",\n" +
            "                \"id\": 79290,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/24/76/47651877XMO1.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356H323.jpg\",\n" +
            "                \"pubdate\": \"2014-10-24 14:40\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/24/53/48575398C8VT.jpg\",\n" +
            "                \"title\": \"带你看世界各地的厨房\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/24/764D6655416111796B20.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/63/79263/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/79263\",\n" +
            "                \"id\": 79263,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/24/23/47651877KIJC.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356PMQ6.jpg\",\n" +
            "                \"pubdate\": \"2014-10-24 13:57\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/24/68/48575398CSR8.jpg\",\n" +
            "                \"title\": \"哥伦比亚“黑超保镖”炼成记\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/24/754E6D5E4E6E197E672F.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/83/78883/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/78883\",\n" +
            "                \"id\": 78883,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/23/0/153729125094E2.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/153821477101GE.jpg\",\n" +
            "                \"pubdate\": \"2014-10-23 10:57\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/23/10/1540061813Y71H.jpg\",\n" +
            "                \"title\": \"中国新闻奖部分照片欣赏\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/23/704B695B406A1C756921.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/92/78592/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/78592\",\n" +
            "                \"id\": 78592,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/22/55/1649960812RQFY.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/1651807854W6KO.jpg\",\n" +
            "                \"pubdate\": \"2014-10-22 14:04\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/22/46/1650884333ZVHP.jpg\",\n" +
            "                \"title\": \"影视剧各版“武则天”PK\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/22/72496D5F4F681D75672E.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/75/77775/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/77775\",\n" +
            "                \"id\": 77775,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/20/51/1505891536XYLA.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/5000725356GRE.jpg\",\n" +
            "                \"pubdate\": \"2014-10-20 09:25\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/20/31/1485574074U25I.jpg\",\n" +
            "                \"title\": \"科技范儿！伦敦新地铁似宇宙飞船\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/20/714A6F52496C197F6B25.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/96/77596/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/77596\",\n" +
            "                \"id\": 77596,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/17/29/1655501938PR0X.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/1656425459S7CD.jpg\",\n" +
            "                \"pubdate\": \"2014-10-17 21:09\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/17/41/165734898034KA.jpg\",\n" +
            "                \"title\": \"看书的外国人与玩手机的中国人\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/17/714A675A486F1F77672A.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/99/77099/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/77099\",\n" +
            "                \"id\": 77099,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/16/62/47651877483X.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356SFJE.jpg\",\n" +
            "                \"pubdate\": \"2014-10-16 11:05\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/16/51/485753981NU5.jpg\",\n" +
            "                \"title\": \"最美公路 难以想象\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/16/764D6855406219716C2E.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/20/76920/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/76920\",\n" +
            "                \"id\": 76920,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/75/1678589963DWQY.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/1679513484QXOC.jpg\",\n" +
            "                \"pubdate\": \"2014-10-15 21:08\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/42/16804370051MYP.jpg\",\n" +
            "                \"title\": \"追风暴的人：丈夫为妻子拍风暴写真\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/15/72496B574D661D7E6922.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/09/76909/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/76909\",\n" +
            "                \"id\": 76909,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/66/15917789896YFY.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/1571461527JCBR.jpg\",\n" +
            "                \"pubdate\": \"2014-10-15 19:25\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/93/1592702510DM5B.jpg\",\n" +
            "                \"title\": \"世界粮食日：饥饿的威胁\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/15/72496D514D661071682A.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/55/76755/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/76755\",\n" +
            "                \"id\": 76755,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/94/4765187717OI.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356I1TZ.jpg\",\n" +
            "                \"pubdate\": \"2014-10-15 13:13\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/6/485753986PLS.jpg\",\n" +
            "                \"title\": \"穿行在地铁\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/15/754E69554D681A7E6A24.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/50/76750/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/76750\",\n" +
            "                \"id\": 76750,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/32/47651877XKBO.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/4672835675CV.jpg\",\n" +
            "                \"pubdate\": \"2014-10-15 12:54\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/15/12/48575398U11O.jpg\",\n" +
            "                \"title\": \"探秘朝鲜高丽航空\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/15/714A6D514F6A1F7B6922.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/70/75170/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/75170\",\n" +
            "                \"id\": 75170,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/09/29/16231787037271.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/1159137654MWR8.jpg\",\n" +
            "                \"pubdate\": \"2014-10-09 20:05\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/09/13/1622255182BIT1.jpg\",\n" +
            "                \"title\": \"萌呆了！汪星人抱玩偶酣睡\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/09/704B68574063197F6E25.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/66/74766/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/74766\",\n" +
            "                \"id\": 74766,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/08/1/47651877DPOH.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356PCLM.jpg\",\n" +
            "                \"pubdate\": \"2014-10-08 15:24\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/08/18/4857539813M1.jpg\",\n" +
            "                \"title\": \"环球小姐素颜排练照\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/08/774C6E504C691D7A6D20.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/92/74292/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/74292\",\n" +
            "                \"id\": 74292,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/10/03/80/1536367729LR1Y.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/444689448KDQ5.jpg\",\n" +
            "                \"pubdate\": \"2014-10-03 10:40\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/10/03/35/15354442085DEE.jpg\",\n" +
            "                \"title\": \"“90后”国旗手是如何炼成的\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/10/03/774C6C524F6F1D756C25.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/81/74181/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/74181\",\n" +
            "                \"id\": 74181,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/09/30/55/2076222180RTEM.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/308245KDDV.jpg\",\n" +
            "                \"pubdate\": \"2014-09-30 16:32\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/09/30/84/207529865969U2.jpg\",\n" +
            "                \"title\": \"北京一家人的20年图片记忆\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/09/30/72496A5441621F766B21.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/48/73148/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/73148\",\n" +
            "                \"id\": 73148,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/09/26/32/1599167157WN8T.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/160009067810KV.jpg\",\n" +
            "                \"pubdate\": \"2014-09-26 13:52\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/09/26/55/16204081400J1C.jpg\",\n" +
            "                \"title\": \"别抖！全球让人腿软的景区阶梯\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/09/26/72496D5440631B7E682B.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/51/72851/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/72851\",\n" +
            "                \"id\": 72851,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/09/25/57/476518775JAU.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356GW3I.jpg\",\n" +
            "                \"pubdate\": \"2014-09-25 14:49\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/09/25/1/48575398JZCR.jpg\",\n" +
            "                \"title\": \"原汁原味的北京人\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/09/25/704B6B534F65197D6B21.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/14/71414/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/71414\",\n" +
            "                \"id\": 71414,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/09/20/47/16231787033RH7.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/115913765474ZV.jpg\",\n" +
            "                \"pubdate\": \"2014-09-20 11:49\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/09/20/56/1622255182UXNV.jpg\",\n" +
            "                \"title\": \"逃脱大师成功挑战百米高空<极限逃生>\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/09/20/774C6B5041671D7D6A25.html\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"comment\": true,\n" +
            "                \"commentlist\": \"http://zhbj.qianlong.com/static/api/news/10003/66/69866/comment_1.json\",\n" +
            "                \"commenturl\": \"http://zhbj.qianlong.com/client/user/newComment/69866\",\n" +
            "                \"id\": 69866,\n" +
            "                \"largeimage\": \"http://zhbj.qianlong.com/static/images/2014/09/15/64/47651877KF8W.jpg\",\n" +
            "                \"listimage\": \"http://10.0.2.2:8080/zhbj/photos/images/46728356YLZ2.jpg\",\n" +
            "                \"pubdate\": \"2014-09-15 11:26\",\n" +
            "                \"smallimage\": \"http://zhbj.qianlong.com/static/images/2014/09/15/25/485753981N83.jpg\",\n" +
            "                \"title\": \"南宁特警主题海报 炫似大片\",\n" +
            "                \"type\": \"news\",\n" +
            "                \"url\": \"http://zhbj.qianlong.com/static/html/2014/09/15/764C6F5C4862187F6825.html\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"title\": \"组图\",\n" +
            "        \"topic\": []\n" +
            "    },\n" +
            "    \"retcode\": 200\n" +
            "}";
    private String mUrl;
    private View mView;
    private ListView listView;
    private GridView gridView;
    private List<PhotoDetail.PhotoNew> mPhotoNews;
    private ListAdapter mListAdapter;
    private ImageButton ibViewType;
    private boolean mIsListType = true;

    public PhotoMenuDetaiPager(Activity activity, String url, ImageButton ibViewType) {
        super(activity);
        mUrl = url;
        this.ibViewType = ibViewType;
        this.ibViewType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewType();
            }
        });
    }

    private void changeViewType() {
        if (mIsListType) {
            listView.setVisibility(View.INVISIBLE);
            gridView.setVisibility(View.VISIBLE);
            ibViewType.setImageResource(R.drawable.photo_menudetail_grid_icon);
            mIsListType = false;
        } else {
            mIsListType = true;
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.INVISIBLE);
            ibViewType.setImageResource(R.drawable.photo_menudetail_list_icon);
        }
    }

    @Override
    public View initViews() {
        mView = View.inflate(mActivity, R.layout.viewpager_photo_menudetail, null);
        listView = (ListView) mView.findViewById(R.id.listview);
        gridView = (GridView) mView.findViewById(R.id.gridView);
        mListAdapter = new ListAdapter();
        listView.setAdapter(mListAdapter);
        gridView.setAdapter(mListAdapter);
        return mView;
    }

    @Override
    public void initData() {
        if (mPhotoNews == null) {
            mPhotoNews = new ArrayList<>();
        }
        getDataFromServer(mUrl);
    }

    private void getDataFromServer(final String url) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                parseData(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                parseData(DEFAULT_DATA);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseData(String s) {
        Gson gson = new Gson();
        PhotoDetail photoDetail = gson.fromJson(s, PhotoDetail.class);
        List<PhotoDetail.PhotoNew> photoNews = photoDetail.data.news;
        mPhotoNews.clear();
        mPhotoNews.addAll(photoNews);
        mListAdapter.notifyDataSetChanged();
    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mPhotoNews != null) {
                return mPhotoNews.size();
            }
            return 0;
        }

        @Override
        public PhotoDetail.PhotoNew getItem(int position) {
            return mPhotoNews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view = convertView;
            if (view == null) {
                view = View.inflate(mActivity, R.layout.listitem_photo_menudetail, null);
                viewHolder = new ViewHolder();
                viewHolder.ivPre = (ImageView) view.findViewById(R.id.ivPre);
                viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            PhotoDetail.PhotoNew photoNew = getItem(position);
//            viewHolder.ivPre.setImageBitmap(null);
            viewHolder.tvTitle.setText(photoNew.title);
            return view;
        }

        class ViewHolder {
            ImageView ivPre;
            TextView tvTitle;
        }
    }
}
