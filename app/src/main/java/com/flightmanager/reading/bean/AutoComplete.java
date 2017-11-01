package com.flightmanager.reading.bean;


import android.os.Parcel;

import com.flightmanager.reading.bean.base.Base;

import java.util.List;

public class AutoComplete extends Base {
    protected AutoComplete(Parcel in) {
        super(in);
    }
    public List<String> keywords;
}
