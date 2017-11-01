package com.flightmanager.reading.bean;


import android.os.Parcel;

import com.flightmanager.reading.bean.base.Base;

import java.util.List;

public class CategoryList extends Base {
    protected CategoryList(Parcel in) {
        super(in);
    }

    /**
     * male : [{"name":"玄幻","bookCount":188244},{"name":"奇幻","bookCount":24183}]
     * ok : true
     */

    public List<MaleBean> male;
    /**
     * name : 古代言情
     * bookCount : 125103
     */

    public List<MaleBean> female;

    public static class MaleBean {
        public String name;
        public int bookCount;
    }
}
