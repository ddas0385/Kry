package com.kri.kry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kri.kry.db.DBLastProcessedSMSID;
import com.kri.kry.db.DBMain;
import com.kri.kry.utility.Parameter;

/**
 * Created by dj on 12/06/2016.
 */
public class LastProcessedSMS {

    public void GetLastProcessedSMSID(Context context) {

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            DBMain dbMain = new DBMain(context);

            db = dbMain.getReadableDatabase();

            cursor = db.rawQuery(DBLastProcessedSMSID.SQL_Select_LastProcessedSMSID, null);

            if (cursor.moveToFirst()) {

                Parameter.LastProcessedSMSID = cursor.getLong(0);

            }
        }
        catch(Exception ex)
        {

            throw ex;

        }
        finally {

            if(cursor != null)
                cursor.close();

            if(db != null)
                db.close();

        }
    }
}
