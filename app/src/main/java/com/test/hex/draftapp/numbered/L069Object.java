package com.test.hex.draftapp.numbered;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class L069Object implements Parcelable {

    final static String LOG_TAG = "myLogs";

    public String s;
    public int i;

    private L069Object(Parcel parcel) {
        Log.d(LOG_TAG, "L069Object(parcel)");
        s = parcel.readString();
        i = parcel.readInt();
    }

    public L069Object(String s, int i) {
        Log.d(LOG_TAG, "L069Object(s, i)");
        this.s = s;
        this.i = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }

    public static final Parcelable.Creator<L069Object> CREATOR =
            new Parcelable.Creator<L069Object>() {

                @Override
                public L069Object createFromParcel(Parcel source) {
                    Log.d(LOG_TAG, "createFromParcel");
                    return new L069Object(source);
                }

                @Override
                public L069Object[] newArray(int size) {
                    return new L069Object[size];
                }
            };
}
