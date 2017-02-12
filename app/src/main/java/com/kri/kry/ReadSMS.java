package com.kri.kry;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

import com.kri.kry.utility.Parameter;

import java.util.Date;

/**
 * Created by dj on 16/2/16.
 */
public class ReadSMS {

    public void GetSMS(Context context) {

        Cursor cursor = null;

        try {

            new LastProcessedSMS().GetLastProcessedSMSID(context);

            Uri inboxURI = Uri.parse("content://sms/inbox");

            String[] reqCols = new String[]{Telephony.Sms._ID, Telephony.Sms.DATE, Telephony.Sms.ADDRESS, Telephony.Sms.BODY};

            ContentResolver cr = context.getContentResolver();

            cursor = cr.query(inboxURI, reqCols, Telephony.Sms._ID + " > ?", new String[]{Parameter.LastProcessedSMSID.toString()}, Telephony.Sms._ID + " ASC");

            ProcessSMS objProcessSMS = new ProcessSMS();

            if(cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    SMSInbox objSMSInbox = new SMSInbox();

                    objSMSInbox._ID = cursor.getLong(0);
                    objSMSInbox.Date = new Date(cursor.getLong(1));
                    objSMSInbox.Address = cursor.getString(2);
                    objSMSInbox.Body = cursor.getString(3);

                    objProcessSMS.ProcessSMSInbox(context, objSMSInbox);

                    cursor.moveToNext();

                }
            }
        }
        catch(Exception ex)
        {

            throw ex;

        }
        finally {

            if(cursor != null)
                cursor.close();

        }
    }
}
