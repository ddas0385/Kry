package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 16/2/16.
 */
public final class DBLastProcessedSMSID {

    public static abstract class LastProcessedSMSIDInfo implements BaseColumns {

        //Table
        public static final String TableLastProcessedSMSID = "LastProcessedSMSID";

        //Columns
        public static final String ColumnLastProcessedSMSID = "LastProcessedSMSID";

    }

    public static final String SQL_Create_LastProcessedSMSID =
            "CREATE TABLE " + LastProcessedSMSIDInfo.TableLastProcessedSMSID + " (" +
                    LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + " INTEGER NOT NULL " + " );";

    public static final String SQL_Insert_LastProcessedSMSID =
            "INSERT INTO " + LastProcessedSMSIDInfo.TableLastProcessedSMSID + " (" +
                    LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + " ) VALUES([" + LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + "]);";

    public static final String SQL_Update_LastProcessedSMSID =
            "UPDATE " + LastProcessedSMSIDInfo.TableLastProcessedSMSID + " SET " +
                    LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + " = [" + LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + "];";

    public static final String SQL_Select_LastProcessedSMSID =
            "SELECT " + LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + " FROM " +
                    LastProcessedSMSIDInfo.TableLastProcessedSMSID + ";";

    public static final String SQL_Truncate_LastProcessedSMSID = " DROP TABLE IF EXISTS " + LastProcessedSMSIDInfo.TableLastProcessedSMSID + ";";

}
