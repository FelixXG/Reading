package com.flightmanager.reading.bean;


import android.os.Parcel;

import com.flightmanager.reading.bean.base.Base;

import java.util.List;

public class CategoryListLv2 extends Base {
    protected CategoryListLv2(Parcel in) {
        super(in);
    }

    /**
     * major : 玄幻
     * mins : ["东方玄幻","异界大陆","异界争霸","远古神话"]
     */

    public List<MaleBean> male;
    /**
     * major : 古代言情
     * mins : ["穿越时空","古代历史","古典架空","宫闱宅斗","经商种田"]
     */

    public List<MaleBean> female;

    public static class MaleBean {
        public String major;
        public List<String> mins;
    }
}
