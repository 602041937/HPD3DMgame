package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.hpdxay.hpd3dmgame.entities");
        schema.setDefaultJavaPackageDao("com.hpdxay.hpd3dmgame.dao");

//                 id: "3545013",
//                typeid: "2",
//                typeid2: "0",
//                sortrank: "1453874296",
//                flag: "",
//                ismake: "1",
//                channel: "1",
//                arcrank: "0",
//                click: "0",
//                money: "0",
//                title: "EVO2016比赛项目确定 妮娜身穿婚纱参战铁拳7FR",
//                shorttitle: "铁拳7FR妮娜参战视频",
//                color: "",
//                writer: "某某",
//                source: "3DM新闻组-某某",
//                litpic: "/uploads/allimg/160127/303-16012G34S50-L.jpg",
//                pubdate: "1453874296",
//                senddate: "1453874288",
//                mid: "303",
//                keywords: "EVO2016,铁拳7FR",
//                lastpost: "0",
//                scores: "0",
//                goodpost: "0",
//                badpost: "0",
//                voteid: "0",
//                notpost: "0",
//                description: "2016年的EVO会从7月15日开始在美国拉斯维加斯召开，格斗大会将持续三天。今天，本次大会的比赛项目得到了最终确定，一共有以下9个项目：",
//                filename: "",
//                dutyadmin: "303",
//                tackid: "0",
//                mtype: "0",
//                weight: "382249",
//                fby_id: "0",
//                game_id: "0",
//                feedback: "0",
//                typedir: "{cmspath}/a/news",
//                typename: "游戏新闻",
//                corank: "0",
//                isdefault: "-1",
//                defaultname: "index.html",
//                namerule: "{typedir}/{Y}{M}/{aid}.html",
//                namerule2: "{typedir}/list_{tid}_{page}.html",
//                ispart: "0",
//                moresite: "0",
//                siteurl: "",
//                sitepath: "{cmspath}/a/info",
//                arcurl: "http://www.3dmgame.com/news/201601/3545013.html",
//                typeurl: "http://www.3dmgame.com/news/",
//                videolist: {
//            0: {
//                body: null
//            }
//        }


        //                typeid: "2",
//                typeid2: "0",
//                sortrank: "1453874296",
//                flag: "",
//                ismake: "1",
//                channel: "1",
//                arcrank: "0",
//                click: "0",
//                money: "0",
        Entity chapterListItem = schema.addEntity("ChapterListItemDataItem");
        chapterListItem.addIdProperty();
        chapterListItem.addStringProperty("typeid");
        chapterListItem.addStringProperty("typeid2");
        chapterListItem.addStringProperty("sortrank");
        chapterListItem.addStringProperty("flag");
        chapterListItem.addStringProperty("ismake");
        chapterListItem.addStringProperty("channel");
        chapterListItem.addStringProperty("arcrank");
        chapterListItem.addStringProperty("click");
        chapterListItem.addStringProperty("money");


        //                title: "EVO2016比赛项目确定 妮娜身穿婚纱参战铁拳7FR",
//                shorttitle: "铁拳7FR妮娜参战视频",
//                color: "",
//                writer: "某某",
//                source: "3DM新闻组-某某",
//                litpic: "/uploads/allimg/160127/303-16012G34S50-L.jpg",
//                pubdate: "1453874296",
//                senddate: "1453874288",
//                mid: "303",
//                keywords: "EVO2016,铁拳7FR",
//                lastpost: "0",
//                scores: "0",
        chapterListItem.addStringProperty("title");
        chapterListItem.addStringProperty("shorttitle");
        chapterListItem.addStringProperty("color");
        chapterListItem.addStringProperty("writer");
        chapterListItem.addStringProperty("source");
        chapterListItem.addStringProperty("litpic");
        chapterListItem.addStringProperty("pubdate");
        chapterListItem.addStringProperty("senddate");
        chapterListItem.addStringProperty("mid");
        chapterListItem.addStringProperty("keywords");
        chapterListItem.addStringProperty("lastpost");
        chapterListItem.addStringProperty("scores");

        //                goodpost: "0",
//                badpost: "0",
//                voteid: "0",
//                notpost: "0",
//                description: "2016年的EVO会从7月15日开始在美国拉斯维加斯召开，格斗大会将持续三天。今天，本次大会的比赛项目得到了最终确定，一共有以下9个项目：",
//                filename: "",
//                dutyadmin: "303",
//                tackid: "0",
//                mtype: "0",
//                weight: "382249",
//                fby_id: "0",


        chapterListItem.addStringProperty("goodpost");
        chapterListItem.addStringProperty("badpost");
        chapterListItem.addStringProperty("voteid");
        chapterListItem.addStringProperty("notpost");
        chapterListItem.addStringProperty("description");
        chapterListItem.addStringProperty("filename");
        chapterListItem.addStringProperty("dutyadmin");
        chapterListItem.addStringProperty("tackid");
        chapterListItem.addStringProperty("mtype");
        chapterListItem.addStringProperty("weight");
        chapterListItem.addStringProperty("fby_id");

        //                game_id: "0",
//                feedback: "0",
//                typedir: "{cmspath}/a/news",
//                typename: "游戏新闻",
//                corank: "0",
//                isdefault: "-1",
//                defaultname: "index.html",
//                namerule: "{typedir}/{Y}{M}/{aid}.html",
//                namerule2: "{typedir}/list_{tid}_{page}.html",
//                ispart: "0",
//                moresite: "0",


        chapterListItem.addStringProperty("game_id");
        chapterListItem.addStringProperty("feedback");
        chapterListItem.addStringProperty("typedir");
        chapterListItem.addStringProperty("typename");
        chapterListItem.addStringProperty("corank");
        chapterListItem.addStringProperty("isdefault");
        chapterListItem.addStringProperty("defaultname");
        chapterListItem.addStringProperty("namerule");
        chapterListItem.addStringProperty("namerule2");
        chapterListItem.addStringProperty("ispart");
        chapterListItem.addStringProperty("moresite");

        //                siteurl: "",
//                sitepath: "{cmspath}/a/info",
//                arcurl: "http://www.3dmgame.com/news/201601/3545013.html",
//                typeurl: "http://www.3dmgame.com/news/",
//                videolist: {
//            0: {
//                body: null
//            }
//        }

        chapterListItem.addStringProperty("siteurl");
        chapterListItem.addStringProperty("sitepath");
        chapterListItem.addStringProperty("arcurl");
        chapterListItem.addStringProperty("typeurl");


        Entity chapterCommentListItemDataItem = schema.addEntity("ChapterCommentListItemDataItem");

//                  "id": "4415672",
//                "aid": "3315273",
//                "typeid": "0",
//                "username": "用户名",
//                "ip": "27.41.225.233",
//                "ip1": "274118623214025",
//                "ip2": "59093204db05a12",

        chapterCommentListItemDataItem.addIdProperty();
        chapterCommentListItemDataItem.addStringProperty("aid");
        chapterCommentListItemDataItem.addStringProperty("typeid");
        chapterCommentListItemDataItem.addStringProperty("username");
        chapterCommentListItemDataItem.addStringProperty("ip");
        chapterCommentListItemDataItem.addStringProperty("ip1");
        chapterCommentListItemDataItem.addStringProperty("ip2");

//                "ischeck": "1",
//                "dtime": "1402624904",
//                "mid": "0",
//                "bad": "0",
//                "good": "0",
//                "ftype": "",
//                "face": "0",
        chapterCommentListItemDataItem.addStringProperty("ischeck");
        chapterCommentListItemDataItem.addStringProperty("dtime");
        chapterCommentListItemDataItem.addStringProperty("mid");
        chapterCommentListItemDataItem.addStringProperty("bad");
        chapterCommentListItemDataItem.addStringProperty("good");
        chapterCommentListItemDataItem.addStringProperty("ftype");
        chapterCommentListItemDataItem.addStringProperty("face");
//                "msg": "评论消息",
//                "cid": "",
//                "reid": "0",
//                "topid": "0",
//                "floor": "3",
//                "reply": "0"
        chapterCommentListItemDataItem.addStringProperty("msg");
        chapterCommentListItemDataItem.addStringProperty("cid");
        chapterCommentListItemDataItem.addStringProperty("reid");
        chapterCommentListItemDataItem.addStringProperty("topid");
        chapterCommentListItemDataItem.addStringProperty("floor");
        chapterCommentListItemDataItem.addStringProperty("reply");
        try {
            new DaoGenerator().generateAll(schema, "lib/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
