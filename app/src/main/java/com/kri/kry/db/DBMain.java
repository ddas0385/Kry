package com.kri.kry.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kri.kry.utility.ExceptionHandling;
import com.kri.kry.utility.Parameter;
import com.kri.kry.XMLParser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dj on 16/2/16.
 */
public class DBMain extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kry.db";

    private Context ApplicationContext = null;

    public DBMain(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        ApplicationContext = context;

    }

    public void onCreate(SQLiteDatabase db) {

    }

    private void onBackup() {

        try {

        }
        catch (Exception ex) {

            throw ex;

        }
    }

    private void onDrop() {

        try {

            WriteData(DBFinancialData.SQL_Truncate_FinancialData);
            WriteData(DBLastProcessedSMSID.SQL_Truncate_LastProcessedSMSID);
            WriteData(DBPotentialTemplate.SQL_Truncate_PotentialTemplate);
            WriteData(DBRegex.SQL_Truncate_Regex);
            WriteData(DBSMSPotentialTemplateMapping.SQL_Truncate_SMSPotentialTemplateMapping);
            WriteData(DBTemplate.SQL_Truncate_Template);
            WriteData(DBTemplateType.SQL_Truncate_TemplateType);

        }
        catch (Exception ex) {

            throw ex;

        }
    }

    private void onCreate() {

        try {

            WriteData(DBFinancialData.SQL_Create_FinancialData);
            WriteData(DBLastProcessedSMSID.SQL_Create_LastProcessedSMSID);
            WriteData(DBPotentialTemplate.SQL_Create_PotentialTemplate);
            WriteData(DBRegex.SQL_Create_Regex);
            WriteData(DBSMSPotentialTemplateMapping.SQL_Create_SMSPotentialTemplateMapping);
            WriteData(DBTemplate.SQL_Create_Template);
            WriteData(DBTemplateType.SQL_Create_TemplateType);

        }
        catch (Exception ex) {

            throw ex;

        }
    }

    private void onInsertMasterData() throws ParserConfigurationException, SAXException, IOException {

        try {

            String query = DBLastProcessedSMSID.SQL_Insert_LastProcessedSMSID.replace("[" + DBLastProcessedSMSID.LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + "]", Parameter.LastProcessedSMSIDFirstValue);
            WriteData(query);

            new XMLParser().SaveData(ApplicationContext);

        }
        catch(Exception ex) {

            throw ex;

        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {

        try {

            //onBackup();

            onDrop();

            onCreate();

            onInsertMasterData();

        }
        catch(Exception ex) {

            ExceptionHandling.ProcessException(ex);

        }
    }

    public List ReadData(String sqlCommand) {

        SQLiteDatabase db = null;
        Cursor dbCursor = null;
        List objectList = new LinkedList();

        try {

            db = this.getReadableDatabase();

            dbCursor = db.rawQuery(sqlCommand, null);

            if (dbCursor.getCount() > 0 && dbCursor.moveToFirst()) {

                while (!dbCursor.isAfterLast()) {

                    List row = new LinkedList();

                    for(int i = 0; i < dbCursor.getColumnCount(); i++)
                        row.add(dbCursor.getString(i));

                    objectList.add(row);

                    dbCursor.moveToNext();

                }
            }
        }
        catch(Exception ex) {

            throw ex;

        }
        finally {

            if(dbCursor != null)
                dbCursor.close();

            if(db != null)
                db.close();

        }

        return objectList;

    }

    public void WriteData(String sqlCommand) {

        SQLiteDatabase db = null;

        try {

            db = this.getWritableDatabase();

            db.execSQL(sqlCommand);

        }
        catch (Exception ex) {

            throw ex;

        }
        finally {

            if(db != null)
                db.close();

        }
    }
}
