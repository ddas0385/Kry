package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 7/4/16.
 */
public final class DBRegex {

    public static abstract class RegexInfo implements BaseColumns {

        //Table
        public static final String TableRegex = "Regex";

        //Columns
        public static final String ColumnID = "ID";
        public static final String ColumnVersion = "Version";
        public static final String ColumnType = "Type";
        public static final String ColumnExpression = "Expression";
        public static final String ColumnPlaceHolder = "PlaceHolder";
        public static final String ColumnWeight = "Weight";

    }

    public static final String SQL_Create_Regex =
            "CREATE TABLE " + RegexInfo.TableRegex + " (" +
                    RegexInfo.ColumnID + " INTEGER PRIMARY KEY, " +
                    RegexInfo.ColumnVersion + " TEXT NOT NULL, " +
                    RegexInfo.ColumnType + " TEXT NOT NULL, " +
                    RegexInfo.ColumnExpression + " TEXT NOT NULL, " +
                    RegexInfo.ColumnPlaceHolder + " TEXT NOT NULL, " +
                    RegexInfo.ColumnWeight + " INTEGER NOT NULL " + " );";

    public static final String SQL_Insert_Regex =
            "INSERT INTO " + RegexInfo.TableRegex + " (" +
                    RegexInfo.ColumnID + ", " +
                    RegexInfo.ColumnVersion + ", " +
                    RegexInfo.ColumnType + ", " +
                    RegexInfo.ColumnExpression + ", " +
                    RegexInfo.ColumnPlaceHolder + ", " +
                    RegexInfo.ColumnWeight + " ) VALUES([" + RegexInfo.ColumnID + "], '[" + RegexInfo.ColumnVersion +
                    "]', '[" + RegexInfo.ColumnType + "]', '[" + RegexInfo.ColumnExpression + "]', '[" + RegexInfo.ColumnPlaceHolder + "]', [" + RegexInfo.ColumnWeight + "]);";

    public static final String SQL_Select_Regex =
            "SELECT " +
                    RegexInfo.ColumnID + ", " +
                    RegexInfo.ColumnVersion + ", " +
                    RegexInfo.ColumnType + ", " +
                    RegexInfo.ColumnExpression + ", " +
                    RegexInfo.ColumnPlaceHolder + ", " +
                    RegexInfo.ColumnWeight +
                    " FROM " + RegexInfo.TableRegex + " ";

    public static final String SQL_Truncate_Regex = " DROP TABLE IF EXISTS " + RegexInfo.TableRegex + ";";

}
