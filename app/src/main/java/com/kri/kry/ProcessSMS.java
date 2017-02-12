package com.kri.kry;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import com.kri.kry.db.DBFinancialData;
import com.kri.kry.db.DBLastProcessedSMSID;
import com.kri.kry.db.DBPotentialTemplate;
import com.kri.kry.db.DBRegex;
import com.kri.kry.db.DBSMSPotentialTemplateMapping;
import com.kri.kry.utility.ExceptionHandling;
import com.kri.kry.utility.Parameter;
import com.kri.kry.db.DBMain;
import com.kri.kry.db.DBTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dj on 17/2/16.
 */
public class ProcessSMS {

    private Context ApplicationContext = null;

    private class TemplateData {

        private int ID;

        private String SMSType;

        private String Address;

        private String Data;

    }

    public void ProcessSMSInbox(Context context, SMSInbox objSMSInbox) {

        List objectList;

        try {

            ApplicationContext = context;

            objSMSInbox = CheckSMSID(objSMSInbox);

            if (!objSMSInbox.Address.startsWith("+") && objSMSInbox._ID > 0) {

                Log.i("ProcessSMS", "Processing SMS ID" + objSMSInbox._ID);

                SMSInbox objSMSTemplate = TemplateSMS(objSMSInbox);

                String query = DBTemplate.SQL_Select_Template +
                        "WHERE " + DBTemplate.TemplateInfo.ColumnAddressInfo + " = '" + objSMSTemplate.Address.replace("'", "''") + "'" +
                        " AND " + DBTemplate.TemplateInfo.ColumnTemplateInfo + " = '" + objSMSTemplate.Template.replace("'", "''") + "';";

                DBMain dbMain = new DBMain(ApplicationContext);

                objectList = dbMain.ReadData(query);

                TemplateData objTemplateData = new TemplateData();

                if (objectList.size() > 0) {

                    objTemplateData.ID = Integer.parseInt(((List) objectList.get(0)).get(0).toString());
                    objTemplateData.SMSType = ((List) objectList.get(0)).get(3).toString();
                    objTemplateData.Address = ((List) objectList.get(0)).get(5).toString();
                    objTemplateData.Data = ((List) objectList.get(0)).get(7).toString();


                    if (objTemplateData.SMSType.equals(Parameter.SMSTypeFinancial)) {

                        HashMap hashMap = CreateFinancialDataHashMap(objSMSTemplate, objTemplateData);

                        dbMain.WriteData(DBFinancialData.SQL_Insert_FinancialData.replace("[" + DBFinancialData.FinancialDataInfo.ColumnSMSID + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnSMSID).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnTemplateID + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnTemplateID).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnOrganization + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnOrganization).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnAccountNumber + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnAccountNumber).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnTransactionNumber + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnTransactionNumber).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnDate + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnDate).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnTime + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnTime).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnSMSLocationName + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnSMSLocationName).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnLocation + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnLocation).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnGeoLocationName + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnGeoLocationName).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnTransactionType + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnTransactionType).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnCurrency + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnCurrency).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnAmount + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnAmount).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnBalance + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnBalance).toString())
                                        .replace("[" + DBFinancialData.FinancialDataInfo.ColumnMisc + "]", hashMap.get(DBFinancialData.FinancialDataInfo.ColumnMisc).toString()));

                    }
                }
                else {

                    query = DBPotentialTemplate.SQL_Select_PotentialTemplate +
                            " WHERE " + DBPotentialTemplate.PotentialTemplateInfo.ColumnAddressInfo + " = '" + objSMSTemplate.Address.replace("'", "''") + "'" +
                            " AND " + DBPotentialTemplate.PotentialTemplateInfo.ColumnTemplateInfo + " = '" + objSMSTemplate.Template.replace("'", "''") + "'";

                    objectList = dbMain.ReadData(query);

                    if (objectList.size() == 0) {

                        dbMain.WriteData(DBPotentialTemplate.SQL_Insert_PotentialTemplate.replace("[" +
                                DBPotentialTemplate.PotentialTemplateInfo.ColumnAddressInfo + "]", objSMSTemplate.Address.replace("'", "''")).
                                replace("[" + DBPotentialTemplate.PotentialTemplateInfo.ColumnTemplateInfo + "]", objSMSTemplate.Template.replace("'", "''")).
                                replace("[" + DBPotentialTemplate.PotentialTemplateInfo.ColumnScore + "]", String.valueOf(objSMSTemplate.Score)));

                    }

                    query = DBPotentialTemplate.SQL_Select_PotentialTemplate +
                            " WHERE " + DBPotentialTemplate.PotentialTemplateInfo.ColumnAddressInfo + " = '" + objSMSTemplate.Address.replace("'", "''") + "'" +
                            " AND " + DBPotentialTemplate.PotentialTemplateInfo.ColumnTemplateInfo + " = '" + objSMSTemplate.Template.replace("'", "''") + "'";

                    objectList = dbMain.ReadData(query);

                    if (objectList.size() > 0) {

                        dbMain.WriteData(DBSMSPotentialTemplateMapping.SQL_Insert_SMSPotentialTemplateMappingInfo.replace("[" + DBSMSPotentialTemplateMapping.SMSPotentialTemplateMappingInfo.ColumnSMSID + "]", objSMSInbox._ID.toString()).
                                replace("[" + DBSMSPotentialTemplateMapping.SMSPotentialTemplateMappingInfo.ColumnPotentialTemplateID + "]", ((List) objectList.get(0)).get(0).toString()).
                                replace("[" + DBSMSPotentialTemplateMapping.SMSPotentialTemplateMappingInfo.ColumnPotentialTemplatewithValues + "]", objSMSTemplate.TemplateValues.replace("'", "''")));

                    }
                }

                dbMain.WriteData(DBLastProcessedSMSID.SQL_Update_LastProcessedSMSID.replace("[" +
                        DBLastProcessedSMSID.LastProcessedSMSIDInfo.ColumnLastProcessedSMSID + "]", objSMSInbox._ID.toString()));
                new LastProcessedSMS().GetLastProcessedSMSID(context);

            }
        }
        catch(Exception ex) {

            throw ex;

        }
    }

    private SMSInbox CheckSMSID(SMSInbox objSMSInbox) {

        try {

            if(objSMSInbox._ID > 0)
                return objSMSInbox;
            else
            {
                int attemptCount = 0;

                Cursor cursor = null;

                new LastProcessedSMS().GetLastProcessedSMSID(ApplicationContext);

                do {

                    Uri inboxURI = Uri.parse("content://sms/inbox");

                    String[] reqCols = new String[]{Telephony.Sms._ID, Telephony.Sms.DATE, Telephony.Sms.ADDRESS, Telephony.Sms.BODY};

                    ContentResolver cr = ApplicationContext.getContentResolver();

                    cursor = cr.query(inboxURI, reqCols, Telephony.Sms._ID + " > ?", new String[]{Parameter.LastProcessedSMSID.toString()}, Telephony.Sms._ID + " DESC");

                    if(cursor.moveToFirst()) {

                        while (!cursor.isAfterLast()) {

                            if(objSMSInbox.Address.equals(cursor.getString(2)) && objSMSInbox.Body.equals(cursor.getString(3))) {

                                objSMSInbox._ID = cursor.getLong(0);
                                objSMSInbox.Date = new Date(cursor.getLong(1));

                                break;

                            }

                            cursor.moveToNext();

                        }
                    }

                    if(cursor != null)
                        cursor.close();

                    if(objSMSInbox._ID > 0)
                        break;
                    else {

                        android.os.SystemClock.sleep(1000);
                        attemptCount++;

                    }
                } while (attemptCount < Parameter.AttemptCount);

                return objSMSInbox;

            }
        }
        catch (Exception ex) {

            throw ex;

        }
    }

    private class RegexMatcherResult {

        private String Template;

        private String TemplateValues;

    }

    private RegexMatcherResult RegexMatcher(RegexMatcherResult objRegexMatcherResult, String regex, String placeHolder) {

        StringBuffer templateBuffer = new StringBuffer();
        StringBuffer templatewithValuesBuffer = new StringBuffer();
        int count = 0;

        try {

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(objRegexMatcherResult.Template);

            while (matcher.find()) {

                count++;

                matcher.appendReplacement(templateBuffer, "[{" + placeHolder + String.valueOf(count) + "}]");
                templatewithValuesBuffer.append("[{" + placeHolder + String.valueOf(count) + "::" + matcher.group() + "}]");

            }

            matcher.appendTail(templateBuffer);

            objRegexMatcherResult.Template = templateBuffer.toString();
            objRegexMatcherResult.TemplateValues += templatewithValuesBuffer.toString();

        }
        catch (Exception ex) {

            throw ex;

        }

        return objRegexMatcherResult;
    }

    private SMSInbox TemplateSMS(SMSInbox objSMSInbox) {

        RegexMatcherResult objRegexMatcherResult = new RegexMatcherResult();

        objRegexMatcherResult.Template = objSMSInbox.Body;
        objRegexMatcherResult.TemplateValues = "";

        int score = 0;

        try {

            List objectList = new DBMain(ApplicationContext).ReadData(DBRegex.SQL_Select_Regex);

            if (objectList.size() > 0) {

                for (int i = 0; i < objectList.size(); i++) {

                    objRegexMatcherResult = RegexMatcher(objRegexMatcherResult, ((List) objectList.get(i)).get(3).toString(), ((List) objectList.get(i)).get(4).toString());

                    if(objRegexMatcherResult.Template.contains(((List) objectList.get(i)).get(4).toString()))
                        score +=  Integer.parseInt(((List) objectList.get(i)).get(5).toString());

                }
            }

            objSMSInbox.Template = objRegexMatcherResult.Template;
            objSMSInbox.TemplateValues = objRegexMatcherResult.TemplateValues;

            if(objSMSInbox.GeoLocation != null)
            {
                objSMSInbox.TemplateValues += "[{location::" + String.valueOf(objSMSInbox.GeoLocation.getLatitude()) + " " + String.valueOf(objSMSInbox.GeoLocation.getLongitude()) + "}]";
            }

            objSMSInbox.Score = score;

        }
        catch(Exception ex) {

            throw ex;

        }

        return objSMSInbox;

    }

    private String GetValue(TemplateData objTemplateData, String key, String searchString) {

        String value = "";

        for (int searchFrom = 0, keyIndex = 0; (keyIndex = objTemplateData.Data.indexOf("::" + key + "}]", searchFrom)) >= 0; searchFrom += keyIndex + key.length() + 4) {

            value += " + ";

            int i = 0;
            String placeHolder = "";

            i = keyIndex - 1;

            while (objTemplateData.Data.charAt(i) != '{' && objTemplateData.Data.charAt(i - 1) != '[') {
                placeHolder = objTemplateData.Data.charAt(i) + placeHolder;
                i--;
            }

            int searchIndex = searchString.indexOf("[{" + placeHolder + "::", 0) + placeHolder.length() + 4;

            i = searchIndex;

            while (searchString.charAt(i) != '}' && searchString.charAt(i) != ']') {

                value = value + searchString.charAt(i);
                i++;
            }

        }

        return value.replaceFirst(" \\+ ", "");

    }

    private String GetReverseGeoCoding(double latitude, double longitude) {

        String result = "";

        try {

            Geocoder geocoder = new Geocoder(ApplicationContext, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {

                Address address = addresses.get(0);

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {

                    sb.append(address.getAddressLine(i)).append("\n");

                }

                result = sb.toString();

            }
        }
        catch (IOException ex) {

            ExceptionHandling.ProcessException(ex);

        }

        return result;

    }

    private HashMap CreateFinancialDataHashMap(SMSInbox objSMSTemplate, TemplateData objTemplateData) {

        HashMap hashMap = new HashMap();

        String value = "";

        try {

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnSMSID, objSMSTemplate._ID);
            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnTemplateID, objTemplateData.ID);
            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnOrganization, objTemplateData.Address);

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnAccountNumber,
                    GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnAccountNumber.toLowerCase(), objSMSTemplate.TemplateValues));

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnTransactionNumber,
                    GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnTransactionNumber.toLowerCase(), objSMSTemplate.TemplateValues));


            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnDate,
                    (value = GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnDate.toLowerCase(), objSMSTemplate.TemplateValues)) == "" ? new Date().toString() : value);

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnTime,
                    GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnTime.toLowerCase(), objSMSTemplate.TemplateValues));

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnSMSLocationName,
                    GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnSMSLocationName.toLowerCase(), objSMSTemplate.TemplateValues));

            if(objSMSTemplate.TemplateValues.indexOf("[{location::") >= 0) {

                String[] latitudelongitude =  objSMSTemplate.TemplateValues.substring(objSMSTemplate.TemplateValues.indexOf("[{location::") + 12,
                        objSMSTemplate.TemplateValues.lastIndexOf("}]")).split(" ");

                hashMap.put(DBFinancialData.FinancialDataInfo.ColumnLocation, latitudelongitude[0] + " " + latitudelongitude[1]);
                hashMap.put(DBFinancialData.FinancialDataInfo.ColumnGeoLocationName,
                        GetReverseGeoCoding(Double.parseDouble(latitudelongitude[0]), Double.parseDouble(latitudelongitude[1])));

            }
            else {

                hashMap.put(DBFinancialData.FinancialDataInfo.ColumnLocation, "");
                hashMap.put(DBFinancialData.FinancialDataInfo.ColumnGeoLocationName, "");

            }

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnTransactionType, Parameter.FinancialTransactionTypeDebit);

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnCurrency,
                    GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnCurrency.toLowerCase(), objSMSTemplate.TemplateValues));

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnAmount,
                    (value = GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnAmount.toLowerCase(), objSMSTemplate.TemplateValues)) == "" ? 0 : value);

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnBalance,
                    (value = GetValue(objTemplateData, DBFinancialData.FinancialDataInfo.ColumnBalance.toLowerCase(), objSMSTemplate.TemplateValues)) == "" ? 0 : value );

            hashMap.put(DBFinancialData.FinancialDataInfo.ColumnMisc, "");

        }
        catch(Exception ex) {

            throw ex;

        }

        return hashMap;

    }
}
