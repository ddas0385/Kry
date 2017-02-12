package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 23/2/16.
 */
public final class DBPotentialTemplate {

    public static abstract class PotentialTemplateInfo implements BaseColumns {

        //Table
        public static final String TablePotentialTemplate = "PotentialTemplate";

        //Columns
        public static final String ColumnID = "ID";
        public static final String ColumnAddressInfo = "AddressInfo";
        public static final String ColumnTemplateInfo = "Info";
        public static final String ColumnScore = "Score";

    }

    public static final String SQL_Create_PotentialTemplate =
            "CREATE TABLE " + PotentialTemplateInfo.TablePotentialTemplate + " (" +
                    PotentialTemplateInfo.ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PotentialTemplateInfo.ColumnAddressInfo + " TEXT NOT NULL, " +
                    PotentialTemplateInfo.ColumnTemplateInfo + " TEXT NOT NULL, " +
                    PotentialTemplateInfo.ColumnScore + " INT NOT NULL " + " );";

    public static final String SQL_Insert_PotentialTemplate =
            "INSERT INTO " + PotentialTemplateInfo.TablePotentialTemplate + " (" +
                    PotentialTemplateInfo.ColumnAddressInfo + ", " +
                    PotentialTemplateInfo.ColumnTemplateInfo + ", " +
                    PotentialTemplateInfo.ColumnScore + " ) VALUES('[" + PotentialTemplateInfo.ColumnAddressInfo +
                    "]', '[" + PotentialTemplateInfo.ColumnTemplateInfo + "]', [" + PotentialTemplateInfo.ColumnScore + "]);";

    public static final String SQL_Select_PotentialTemplate =
            "SELECT " + PotentialTemplateInfo.ColumnID + ", " +
                    PotentialTemplateInfo.ColumnAddressInfo + ", " +
                    PotentialTemplateInfo.ColumnTemplateInfo + ", " +
                    PotentialTemplateInfo.ColumnScore +
                    " FROM " + PotentialTemplateInfo.TablePotentialTemplate + " ";

    public static final String SQL_Truncate_PotentialTemplate = " DROP TABLE IF EXISTS " + PotentialTemplateInfo.TablePotentialTemplate + ";";

}
