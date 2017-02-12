package com.kri.kry;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.kri.kry.db.DBMain;
import com.kri.kry.utility.ExceptionHandling;
import com.kri.kry.utility.Parameter;

/**
 * Created by dj on 29/2/16.
 */
public class Kry extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        try {

            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

            boolean firstRun = settings.getBoolean(Parameter.FirstRun, true);

            if (firstRun) {

                DBMain objDBMain = new DBMain(this);

                SQLiteDatabase db = objDBMain.getWritableDatabase();
                objDBMain.onUpgrade(db, 0, 1);

                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(Parameter.FirstRun, false);
                editor.apply();

            }
        }
        catch(Exception ex) {

            ExceptionHandling.ProcessException(ex);

        }
    }
}
