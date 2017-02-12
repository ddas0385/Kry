package com.kri.kry;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.kri.kry.utility.ExceptionHandling;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class KryReadSMSService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    //private static final String ACTION_FOO = "com.kri.kry.action.FOO";
    //private static final String ACTION_BAZ = "com.kri.kry.action.BAZ";
    public static final String ACTION_READ_SMS = "com.kri.kry.intent.action.READ_SMS";

    // TODO: Rename parameters
    //private static final String EXTRA_PARAM1 = "com.kri.kry.extra.PARAM1";
    //private static final String EXTRA_PARAM2 = "com.kri.kry.extra.PARAM2";

    public KryReadSMSService() {
        super("KryReadSMSService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    /*public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, KryReadSMSService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }*/

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    /*public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, KryReadSMSService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }*/

    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            if (intent != null) {
            /*final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }*/

                new ReadSMS().GetSMS(this.getApplicationContext());

                Log.i("ReadSMSService", "ReadSMS completed service");

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(this.ACTION_READ_SMS);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);

            }
        }
        catch (Exception ex) {

            ExceptionHandling.ProcessException(ex);

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    /*private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }*/

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    /*private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}
