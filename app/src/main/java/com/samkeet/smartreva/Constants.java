package com.samkeet.smartreva;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sam on 12-Nov-16.
 */

public class Constants {

    public static class URLs {
        public static String LOGIN = "";
    }

    public static class UserData {
        public static String USER_ID;
        public static String TOKEN;

        public static String getUserId() {
            return USER_ID;
        }

        public static String getTOKEN() {
            return TOKEN;
        }

        public static void setUserId(String userId) {
            USER_ID = userId;
        }

        public static void setTOKEN(String TOKEN) {
            UserData.TOKEN = TOKEN;
        }
    }

    public static class SharedPreferenceData {

        public static SharedPreferences sharedPreferences;
        public static SharedPreferences.Editor editor;

        public static String SHAREDPREFERENCES="SmartReva";
        public static String IS_LOGGED_IN = "isloggedin";
        public static String USER_ID = "user_ID";
        public static String TOKEN = "token";

        public static void initSharedPreferenceData(SharedPreferences sharedPreferences1, SharedPreferences.Editor editor1){
            sharedPreferences=sharedPreferences1;
            editor=editor1;
        }

        public static String getIsLoggedIn() {
            return sharedPreferences.getString(IS_LOGGED_IN,"NOT_AVALIBLE");
        }

        public static String getUserId() {
            return sharedPreferences.getString(USER_ID,"NOT_AVALIBLE");
        }

        public static String getTOKEN(){
            return sharedPreferences.getString(TOKEN,"NOT_AVALIBLE");
        }

        public static void setIsLoggedIn(String isLoggedIn) {
            editor.putString(IS_LOGGED_IN,"yes");
            editor.apply();
        }

        public static void setTOKEN(String token) {
            editor.putString(TOKEN,token);
            editor.apply();
        }

        public static void setUserId(String userId) {
            editor.putString(USER_ID,userId);
            editor.apply();
        }
    }

    public static class Methods {

        public static boolean networkState(Context context, ConnectivityManager comman) {

            boolean wifi = comman.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
            boolean data = comman.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();

            if (wifi || data) {
                return true;
            } else {
                Toast toast = Toast.makeText(context, "No Internet Connnection found!! Try Again", Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        }
    }

}
