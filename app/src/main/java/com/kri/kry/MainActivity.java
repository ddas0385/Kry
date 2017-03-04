package com.kri.kry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kri.kry.utility.ExceptionHandling;

/**
 * Created by dj on 3/5/16.
 */
public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mReadSMSBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(R.string.title_activity_main);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            TextView view = (TextView) findViewById(R.id.textView);
            view.setText("Reading SMS Started");

            Intent intent = new Intent(this, KryReadSMSService.class);
            startService(intent);

            mReadSMSBroadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {

                    if(intent.getAction().equals(KryReadSMSService.ACTION_READ_SMS)){

                        Log.i("ReadSMS", "ReadSMSCompleted Activity");

                        TextView view = (TextView) findViewById(R.id.textView);
                        view.setText("Reading SMS Completed");

                    }
                }
            };

        }
        catch (Exception ex) {

            ExceptionHandling.ProcessException(ex);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReadSMSBroadcastReceiver);

    }
}