package com.flightmanager.reading.bean;


import android.os.Parcel;

import com.flightmanager.reading.bean.base.Base;

import java.util.List;

public class HotWord extends Base {
    protected HotWord(Parcel in) {
        super(in);
    }

    public List<String> hotWords;
}
