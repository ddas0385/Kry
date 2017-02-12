package com.kri.kry.db;

import android.provider.BaseColumns;

/**
 * Created by dj on 24/2/16.
 */
public final class DBSMSPotentialTemplateMapping {

    public static abstract class SMSPotentialTemplateMappingInfo implements BaseColumns {

        //Table
        public static final String TableSMSPotentialTemplateMapping = "SMSPotentialTemplateMapping";

        //Columns
        public static final String ColumnSMSID = "SMSID";
        public static final String ColumnPotentialTemplateID = "PotentialTemplateID";
        public static final String ColumnPotentialTemplatewithValues = "PotentialTemplatewithValues";

    }

    public static final String SQL_Create_SMSPotentialTemplateMapping =
            "CREATE TABLE " + SMSPotentialTemplateMappingInfo.TableSMSPotentialTemplateMapping + " (" +
                    SMSPotentialTemplateMappingInfo.ColumnSMSID + " INTEGER NOT NULL, " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplateID + " INTEGER NOT NULL, " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplatewithValues + " TEXT NOT NULL " + " );";

    public static final String SQL_Insert_SMSPotentialTemplateMappingInfo =
            "INSERT INTO " + SMSPotentialTemplateMappingInfo.TableSMSPotentialTemplateMapping + " (" +
                    SMSPotentialTemplateMappingInfo.ColumnSMSID + ", " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplateID + ", " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplatewithValues + " ) VALUES([" +
                    SMSPotentialTemplateMappingInfo.ColumnSMSID + "], [" +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplateID + "], '[" +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplatewithValues + "]');";

    public static final String SQL_Select_SMSPotentialTemplateMappingInfo =
            "SELECT " + SMSPotentialTemplateMappingInfo.ColumnSMSID + ", " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplateID + ", " +
                    SMSPotentialTemplateMappingInfo.ColumnPotentialTemplatewithValues +
                    " FROM " + SMSPotentialTemplateMappingInfo.TableSMSPotentialTemplateMapping + " ";

    public static final String SQL_Truncate_SMSPotentialTemplateMapping = " DROP TABLE IF EXISTS " + SMSPotentialTemplateMappingInfo.TableSMSPotentialTemplateMapping + ";";

}
