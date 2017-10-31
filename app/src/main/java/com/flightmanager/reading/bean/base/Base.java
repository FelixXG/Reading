package com.flightmanager.reading.bean.base;


import android.os.Parcel;
import android.os.Parcelable;

public class Base implements Parcelable {
    public boolean ok;

    protected Base(Parcel in) {
        ok = in.readByte() != 0;
    }

    public static final Creator<Base> CREATOR = new Creator<Base>() {
        @Override
        public Base createFromParcel(Parcel in) {
            return new Base(in);
        }

        @Override
        public Base[] newArray(int size) {
            return new Base[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (ok ? 1 : 0));
    }
}
