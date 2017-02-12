package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 27/3/16.
 */
public final class DBFinancialData {

    public static abstract class FinancialDataInfo implements BaseColumns {

        //Table
        public static final String TableFinancialData = "FinancialData";

        //Columns
        public static final String ColumnID = "ID";
        public static final String ColumnSMSID = "SMSID";
        public static final String ColumnTemplateID = "TemplateID";
        public static final String ColumnOrganization = "Organization";
        public static final String ColumnAccountNumber = "AccountNumber";
        public static final String ColumnTransactionNumber = "TransactionNumber";
        public static final String ColumnDate = "Date";
        public static final String ColumnTime = "Time";
        public static final String ColumnSMSLocationName = "SMSLocationName";
        public static final String ColumnLocation = "Location";
        public static final String ColumnGeoLocationName = "GeoLocationName";
        public static final String ColumnTransactionType = "TransactionType";
        public static final String ColumnCurrency = "Currency";
        public static final String ColumnAmount = "Amount";
        public static final String ColumnBalance = "Balance";
        public static final String ColumnMisc = "Misc";

    }

    public static final String SQL_Create_FinancialData =
            "CREATE TABLE " + FinancialDataInfo.TableFinancialData + " (" +
                    FinancialDataInfo.ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FinancialDataInfo.ColumnSMSID + " INTEGER NOT NULL, " +
                    FinancialDataInfo.ColumnTemplateID + " INTEGER NOT NULL, " +
                    FinancialDataInfo.ColumnOrganization + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnAccountNumber + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnTransactionNumber + " TEXT, " +
                    FinancialDataInfo.ColumnDate + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnTime + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnSMSLocationName + " TEXT, " +
                    FinancialDataInfo.ColumnLocation + " TEXT, " +
                    FinancialDataInfo.ColumnGeoLocationName + " TEXT, " +
                    FinancialDataInfo.ColumnTransactionType + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnCurrency + " TEXT NOT NULL, " +
                    FinancialDataInfo.ColumnAmount + " REAL NOT NULL, " +
                    FinancialDataInfo.ColumnBalance + " REAL, " +
                    FinancialDataInfo.ColumnMisc + " TEXT " + " );";

    public static final String SQL_Insert_FinancialData =
            "INSERT INTO " + FinancialDataInfo.TableFinancialData + " (" +
                    FinancialDataInfo.ColumnSMSID + ", " +
                    FinancialDataInfo.ColumnTemplateID + ", " +
                    FinancialDataInfo.ColumnOrganization + ", " +
                    FinancialDataInfo.ColumnAccountNumber + ", " +
                    FinancialDataInfo.ColumnTransactionNumber + ", " +
                    FinancialDataInfo.ColumnDate + ", " +
                    FinancialDataInfo.ColumnTime + ", " +
                    FinancialDataInfo.ColumnSMSLocationName + ", " +
                    FinancialDataInfo.ColumnLocation + ", " +
                    FinancialDataInfo.ColumnGeoLocationName + ", " +
                    FinancialDataInfo.ColumnTransactionType + ", " +
                    FinancialDataInfo.ColumnCurrency + ", " +
                    FinancialDataInfo.ColumnAmount + ", " +
                    FinancialDataInfo.ColumnBalance + ", " +
                    FinancialDataInfo.ColumnMisc + " ) VALUES([" + FinancialDataInfo.ColumnSMSID +
                    "], [" + FinancialDataInfo.ColumnTemplateID + "], '[" + FinancialDataInfo.ColumnOrganization + "]', '[" + FinancialDataInfo.ColumnAccountNumber + "]', '[" + FinancialDataInfo.ColumnTransactionNumber +
                    "]', '[" + FinancialDataInfo.ColumnDate + "]', '[" + FinancialDataInfo.ColumnTime + "]', '[" + FinancialDataInfo.ColumnSMSLocationName +
                    "]', '[" + FinancialDataInfo.ColumnLocation + "]', '[" + FinancialDataInfo.ColumnGeoLocationName + "]', '[" + FinancialDataInfo.ColumnTransactionType + "]', '[" + FinancialDataInfo.ColumnCurrency +
                    "]', [" + FinancialDataInfo.ColumnAmount + "], [" + FinancialDataInfo.ColumnBalance + "], '[" + FinancialDataInfo.ColumnMisc + "]');";

    public static final String SQL_Select_FinancialData =
            "SELECT " + FinancialDataInfo.ColumnID + ", " +
                    FinancialDataInfo.ColumnSMSID + ", " +
                    FinancialDataInfo.ColumnTemplateID + ", " +
                    FinancialDataInfo.ColumnOrganization + ", " +
                    FinancialDataInfo.ColumnAccountNumber + ", " +
                    FinancialDataInfo.ColumnTransactionNumber + ", " +
                    FinancialDataInfo.ColumnDate + ", " +
                    FinancialDataInfo.ColumnTime + ", " +
                    FinancialDataInfo.ColumnSMSLocationName + ", " +
                    FinancialDataInfo.ColumnLocation + ", " +
                    FinancialDataInfo.ColumnGeoLocationName + ", " +
                    FinancialDataInfo.ColumnTransactionType + ", " +
                    FinancialDataInfo.ColumnCurrency + ", " +
                    FinancialDataInfo.ColumnAmount + ", " +
                    FinancialDataInfo.ColumnBalance + ", " +
                    FinancialDataInfo.ColumnMisc +
                    " FROM " + FinancialDataInfo.TableFinancialData + " ";

    public static final String SQL_Truncate_FinancialData = " DROP TABLE IF EXISTS " + FinancialDataInfo.TableFinancialData + ";";

}
