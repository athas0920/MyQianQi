package com.qianqi.bean.SportListBean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 14041578 on 2017/4/29.
 */

public class SportListBean implements Serializable {


    /**
     * sportsList : [{"id":4,"createTime":"2017-04-28","rank":2,"num":100,"userId":1,"goods":0},{"id":3,"createTime":"2017-04-25","rank":1,"num":100,"userId":1,"goods":0},{"id":1,"createTime":"2017-04-17","rank":2,"num":1,"userId":1,"goods":1}]
     * status : 0
     * totalrows : 3
     * maxpage : 1
     * info : OK
     */

    private int status;
    private int totalrows;
    private int maxpage;
    private String info;
    private List<SportsListBean> sportsList;

    public static SportListBean objectFromData(String str) {

        return new Gson().fromJson(str, SportListBean.class);
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<SportsListBean> getSportsList() {
        return sportsList;
    }

    public void setSportsList(List<SportsListBean> sportsList) {
        this.sportsList = sportsList;
    }

    public static class SportsListBean {
        /**
         * id : 4
         * createTime : 2017-04-28
         * rank : 2
         * num : 100
         * userId : 1
         * goods : 0
         */

        private int id;
        private String createTime;
        private int rank;
        private int num;
        private int userId;
        private int goods;

        public static SportsListBean objectFromData(String str) {

            return new Gson().fromJson(str, SportsListBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
