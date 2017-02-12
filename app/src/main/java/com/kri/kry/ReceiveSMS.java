package com.kri.kry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.kri.kry.utility.ExceptionHandling;
import com.kri.kry.utility.GeoLocation;

import java.util.Date;
import java.util.Locale;

/**
 * Created by dj on 16/2/16.
 */
public class ReceiveSMS extends BroadcastReceiver {

    public void onReceive(final Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        String message = "";
        String address = "";

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                SmsMessage currentMessage = null;

                String format = bundle.getString("format");

                for (int i = 0; i < pdusObj.length; i++) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    } else {
                        currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }

                    if(address != currentMessage.getDisplayOriginatingAddress())
                        address = currentMessage.getDisplayOriginatingAddress();

                    message += currentMessage.getDisplayMessageBody();

                }

                final SMSInbox objSMSInbox = new SMSInbox();

                objSMSInbox._ID = new Long(-1);
                objSMSInbox.Date = new Date();
                objSMSInbox.Address = address;
                objSMSInbox.Body = message;

                GeoLocation geoLocation = new GeoLocation();

                geoLocation.GetLocation(context, new GeoLocation.LocationResult() {
                    @Override
                    public void gotLocation(Location location) {

                        objSMSInbox.GeoLocation = location;

                        Intent intent = new Intent(context, KryProcessSMSService.class);
                        intent.putExtra("SMSInbox", objSMSInbox);
                        context.startService(intent);

                    }
                });
            }
        }
        catch(Exception ex)
        {

            ExceptionHandling.ProcessException(ex);

        }
    }
}