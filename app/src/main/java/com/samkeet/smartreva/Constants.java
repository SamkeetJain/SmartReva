package com.samkeet.smartreva;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sam on 12-Nov-16.
 */

public class Constants {

    public static class URLs {
        public static String LOGIN = "http://revacounselling.16mb.com/reva_login.php";
        public static String AUTHENTICATION = "http://revacounselling.16mb.com/getAuthentication.php";
        public static String GENERALLIST = "http://revacounselling.16mb.com/getGeneralList.php";
        public static String GETSTUDENTLIST = "http://revacounselling.16mb.com/getStudentsList.php";
        public static String INSERTINTOATTENDENCE = "http://revacounselling.16mb.com/insertIntoAttendence.php";
        public static String GETATTENDENCE = "http://revacounselling.16mb.com/getMyAttendence.php";
        public static String GETALLEVENTS = "http://revacounselling.16mb.com/getAllEvents.php";
        public static String NEWEVENT = "http://revacounselling.16mb.com/newEvent.php";
        public static String ABOUTUS = "http://revacounselling.16mb.com/about.html";
        public static String EVENTMANAGER = "http://revacounselling.16mb.com/EventManager.php";
        public static String FEE = "http://revacounselling.16mb.com/Fee.php";
        public static String NOTES = "http://revacounselling.16mb.com/uploadNotes.php";
        public static String GETNOTES = "http://revacounselling.16mb.com/viewNotes.php";



        public static String GET_WALL_POSTS="http://revacounselling.16mb.com/getWallPosts.php";
        public static String GET_RESERVATION_DETAILS="http://revacounselling.16mb.com/getReservationDetails.php";
        public static String MAKE_APPOINTMENTS="http://revacounselling.16mb.com//makeAppoint.php";
        public static String GET_APPOINMENTS="http://revacounselling.16mb.com//getMyAppoinments.php";
        public static String SEND_NEW_POST="http://revacounselling.16mb.com//newWallPost.php";

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

        public static void clearData(){
        }
    }

    public static class SharedPreferenceData {

        public static SharedPreferences sharedPreferences;
        public static SharedPreferences.Editor editor;

        public static String SHAREDPREFERENCES="SmartReva";
        public static String IS_LOGGED_IN = "isloggedin";
        public static String USER_ID = "user_ID";
        public static String TOKEN = "token";

        public static void initSharedPreferenceData(SharedPreferences sharedPreferences1){
            sharedPreferences=sharedPreferences1;
            editor=sharedPreferences.edit();
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

        public static void clearData(){
            editor.clear();
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

    public static class TimeSlots {

        public static ArrayList<String> SLOTS=new ArrayList<String>();
        public static ArrayList<String> AVALIBILITY=new ArrayList<String>();

        public static void clearData() {
            SLOTS.clear();
            AVALIBILITY.clear();
        }
    }

    public static String[] subjectlist={"btcs14f5100","btcs14f5200","btcs14f5300","btcs14f5400","btcs14f5500","btcs14f5650","btcs14f5660","btcs14f5700","btcs14f5800"};

}
