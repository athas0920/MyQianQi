package com.qianqi.bean.SportListBean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 14041578 on 2017/5/3.
 */

public class SportRankBean implements Serializable {

    /**
     * rankList : [{"id":7,"icon":null,"createTime":"2017-05-03","rank":1,"num":0,"name":"222","userId":2,"goods":0,"isGood":0}]
     * status : 0
     * totalrows : 1
     * maxpage : 1
     * my : null
     * info : OK
     */

    private int status;
    private int totalrows;
    private int maxpage;
    private Object my;
    private String info;
    private List<RankListBean> rankList;

    public static SportRankBean objectFromData(String str) {

        return new Gson().fromJson(str, SportRankBean.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalrows() {
        return totalrows;
    }

    public void setTotalrows(int totalrows) {
        this.totalrows = totalrows;
    }

    public int getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(int maxpage) {
        this.maxpage = maxpage;
    }

    public Object getMy() {
        return my;
    }

    public void setMy(Object my) {
        this.my = my;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<RankListBean> getRankList() {
        return rankList;
    }

    public void setRankList(List<RankListBean> rankList) {
        this.rankList = rankList;
    }

    public static class RankListBean {
        /**
         * id : 7
         * icon : null
         * createTime : 2017-05-03
         * rank : 1
         * num : 0
         * name : 222
         * userId : 2
         * goods : 0
         * isGood : 0
         */

        private int id;
        private Object icon;
        private String createTime;
        private int rank;
        private int num;
        private String name;
        private int userId;
        private int goods;
        private int isGood;

        public static RankListBean objectFromData(String str) {

            return new Gson().fromJson(str, RankListBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getGoods() {
            return goods;
        }

        public void setGoods(int goods) {
            this.goods = goods;
        }

        public int getIsGood() {
            return isGood;
        }

        public void setIsGood(int isGood) {
            this.isGood = isGood;
        }
    }
}
