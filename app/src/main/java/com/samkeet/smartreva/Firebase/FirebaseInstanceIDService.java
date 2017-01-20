package com.samkeet.smartreva.Firebase;



import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.samkeet.smartreva.Constants;


/**
 * Created by Sam on 13-Dec-16.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token);
    }

    private void registerToken(String token) {

        Constants.FireBase.token=token;
    }


}