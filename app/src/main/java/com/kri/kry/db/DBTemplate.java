package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 18/2/16.
 */
public final class DBTemplate {

    public static abstract class TemplateInfo implements BaseColumns {

        //Table
        public static final String TableTemplate = "Template";

        //Columns
        public static final String ColumnID = "ID";
        public static final String ColumnVersion = "Version";
        public static final String ColumnTypeID = "TypeID";
        public static final String ColumnAddressInfo = "AddressInfo";
        public static final String ColumnAddressData = "AddressData";
        public static final String ColumnTemplateInfo = "Info";
        public static final String ColumnTemplateData = "Data";

    }

    public static final String SQL_Create_Template =
                "CREATE TABLE " + TemplateInfo.TableTemplate + " (" +
                        TemplateInfo.ColumnID + " INTEGER PRIMARY KEY, " +
                        TemplateInfo.ColumnVersion + " TEXT NOT NULL, " +
                        TemplateInfo.ColumnTypeID + " INTEGER, " +
                        TemplateInfo.ColumnAddressInfo + " TEXT NOT NULL, " +
                        TemplateInfo.ColumnAddressData + " TEXT NOT NULL, " +
                        TemplateInfo.ColumnTemplateInfo + " TEXT NOT NULL, " +
                        TemplateInfo.ColumnTemplateData + " TEXT NOT NULL " + " );";

    public static final String SQL_Insert_Template =
            "INSERT INTO " + TemplateInfo.TableTemplate + " (" +
                    TemplateInfo.ColumnID + ", " +
                    TemplateInfo.ColumnVersion + ", " +
                    TemplateInfo.ColumnTypeID + ", " +
                    TemplateInfo.ColumnAddressInfo + ", " +
                    TemplateInfo.ColumnAddressData + ", " +
                    TemplateInfo.ColumnTemplateInfo + ", " +
                    TemplateInfo.ColumnTemplateData + " ) VALUES([" + TemplateInfo.ColumnID + "], '[" + TemplateInfo.ColumnVersion +
                    "]', [" + TemplateInfo.ColumnTypeID +
                    "], '[" + TemplateInfo.ColumnAddressInfo + "]', '[" + TemplateInfo.ColumnAddressData +
                    "]', '[" + TemplateInfo.ColumnTemplateInfo + "]', '[" + TemplateInfo.ColumnTemplateData + "]');";

    public static final String SQL_Select_Template =
            "SELECT T." + TemplateInfo.ColumnID + ", " +
                    "T." + TemplateInfo.ColumnVersion + ", " +
                    "T." + TemplateInfo.ColumnTypeID + ", " +
                    "TT." + DBTemplateType.TemplateTypeInfo.ColumnName + ", " +
                    TemplateInfo.ColumnAddressInfo + ", " +
                    TemplateInfo.ColumnAddressData + ", " +
                    TemplateInfo.ColumnTemplateInfo + ", " +
                    TemplateInfo.ColumnTemplateData +
                    " FROM " + TemplateInfo.TableTemplate + " T " +
                    "INNER JOIN " + DBTemplateType.TemplateTypeInfo.TableTemplateType + " TT " +
                    "ON T." + TemplateInfo.ColumnTypeID + " = " + "TT." + DBTemplateType.TemplateTypeInfo.ColumnID + " ";

    public static final String SQL_Truncate_Template = " DROP TABLE IF EXISTS " + TemplateInfo.TableTemplate + ";";

}
