package com.kri.kry;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by dj on 16/2/16.
 */
public class SMSInbox implements Parcelable {

    public Long _ID;

    public Date Date;

    public String Address;

    public String Body;

    public Location GeoLocation;

    public String Template;

    public String TemplateValues;

    public int Score;

    public SMSInbox(){

    }

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        try {

            out.writeLong(_ID);
            out.writeLong(Date.getTime());
            out.writeString(Address);
            out.writeString(Body);
            GeoLocation.writeToParcel(out, flags);
            out.writeString(Template);
            out.writeString(TemplateValues);
            out.writeInt(Score);

        }
        catch (Exception ex) {

            throw ex;

        }
    }

    public static final Parcelable.Creator<SMSInbox> CREATOR = new Parcelable.Creator<SMSInbox>() {

        public SMSInbox createFromParcel(Parcel in) {

            return new SMSInbox(in);

        }

        public SMSInbox[] newArray(int size) {

            return new SMSInbox[size];

        }
    };

    private SMSInbox(Parcel in) {

        try {

            _ID = in.readLong();
            Date = new Date(in.readLong());
            Address = in.readString();
            Body = in.readString();
            GeoLocation = Location.CREATOR.createFromParcel(in);
            Template = in.readString();
            TemplateValues = in.readString();
            Score = in.readInt();

        }
        catch (Exception ex) {

            throw ex;

        }
    }
}
