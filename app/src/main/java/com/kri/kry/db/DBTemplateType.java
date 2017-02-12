package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 21/2/16.
 */
public final class DBTemplateType {

    public static abstract class TemplateTypeInfo implements BaseColumns {

        //Table
        public static final String TableTemplateType = "TemplateType";

        //Columns
        public static final String ColumnID = "ID";
        public static final String ColumnVersion = "Version";
        public static final String ColumnName = "Name";

    }

    public static final String SQL_Create_TemplateType =
            "CREATE TABLE " + TemplateTypeInfo.TableTemplateType + " (" +
                    TemplateTypeInfo.ColumnID + " INTEGER PRIMARY KEY, " +
                    TemplateTypeInfo.ColumnVersion + " TEXT NOT NULL, " +
                    TemplateTypeInfo.ColumnName + " TEXT NOT NULL " + " );";

    public static final String SQL_Insert_TemplateType =
            "INSERT INTO " + TemplateTypeInfo.TableTemplateType + " (" +
                    TemplateTypeInfo.ColumnID + ", " +
                    TemplateTypeInfo.ColumnVersion + ", " +
                    TemplateTypeInfo.ColumnName + " ) VALUES([" + TemplateTypeInfo.ColumnID +
                    "], '[" + TemplateTypeInfo.ColumnVersion + "]', '[" + TemplateTypeInfo.ColumnName + "]');";

    public static final String SQL_Select_TemplateType =
            "SELECT " + TemplateTypeInfo.ColumnID + ", " +
                    TemplateTypeInfo.ColumnVersion + ", " +
                    TemplateTypeInfo.ColumnName +
                    " FROM " + TemplateTypeInfo.TableTemplateType + " ";

    public static final String SQL_Truncate_TemplateType = " DROP TABLE IF EXISTS " + TemplateTypeInfo.TableTemplateType + ";";

}
