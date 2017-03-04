package com.kri.kry;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kri.kry.utility.Parameter;

/**
 * Created by dj on 25/2/17.
 */

public class KryFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        FirebaseMessaging.getInstance().subscribeToTopic(Parameter.FCMTopic);

        Log.e("FCMInstance", FirebaseInstanceId.getInstance().getToken());

        SendRegistrationToServer(refreshedToken);

    }

    private void SendRegistrationToServer(String token) {

        // TODO: Implement this method to send token to your app server.

    }

}
