package com.flightmanager.reading.bean;


import android.os.Parcel;

import com.flightmanager.reading.bean.base.Base;

public class PostCount extends Base {

    public String postCount;
    public String count;

    protected PostCount(Parcel in) {
        super(in);
    }
}
