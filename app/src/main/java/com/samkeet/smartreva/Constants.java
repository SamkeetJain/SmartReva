package com.samkeet.smartreva;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sam on 12-Nov-16.
 */

public class Constants {

    public static final String passingYears[]={"dec/jan 2010/11",
            "june/july 2011",
            "dec/jan 2011/12",
            "june/july 2012",
            "dec/jan 2012/13",
            "june/july 2013",
            "dec/jan 2013/14",
            "june/july 2014",
            "dec/jan 2014/15",
            "june/july 2015",
            "dec/jan 2015/16",
            "june/july 2016",
            "dec/jan 2016/17",
            "june/july 2017",
            "dec/jan 2017/18",
            "june/july 2018",
            "dec/jan 2018/19",
            "june/july 2019",
            "dec/jan 2019/20"};

    public static class URLs {
        public static String BASE = "http://revacounselling.16mb.com/";
        public static String LOGIN = "login.php";
        public static String ALUMNI_LOGIN = "login_alumni.php";
        public static String ALUMNI_REG = "alumni_reg.php";
        public static String ALUMNI_REFER_JOB = "alumni_refer_job.php";
        public static String AUTHENTICATION = "authenticaton.php";
        public static String GENERALLIST = "get_generalList.php";
        public static String GET_STUDENTLIST = "get_studentList.php";
        public static String PUT_ATTENDANCE = "put_attendance.php";
        public static String GET_ATTENDANCE = "get_attendance_percent.php";
        public static String GET_ATTENDANCE_DETAILS = "get_attendance_details.php";
        public static String COUNC_WALL = "counc_wall.php";
        public static String COUNC_APPOINTMENTS = "counc_appointments.php";
        public static String COUNC_RESERVATIONS = "counc_reservations.php";
        public static String EVENTS = "events.php";
        public static String ALUMNI_EVENTS = "alumni_events.php";
        public static String EVENTMANAGER = "eventManager.php";
        public static String ALUMNI_EVENTMANAGER = "alumni_eventsManager.php";
        public static String NOTESS = "notes.php";
        public static String FEE = "fee.php";
        public static String GET_RESULTS = "get_results.php";
        public static String PLACEMENT_DRVE = "placement_drives.php";
        public static String PLACEMENT_ACADEMIC_DETAILS = "placement_academic_profile.php";
        public static String PLACEMENT_UG_DETAILS = "placement_ug.php";
        public static String PLACEMENT_BACK_DETAILS = "placement_backlogs.php";
        public static String PLACEMENT_TANDC = "placement_tandc.php";
        public static String COURSE_SEM = "course_sem.php";
        public static String COURSE_DEPT = "course_dept.php";
        public static String GET_TIMETABLE= "get_timetable.php";
        public static String TERMS_AND_CONDITION="termsandcondition.html";
        public static String ABOUTUS = "http://revacounselling.16mb.com/about.html";
    }

    public static class SharedPreferenceData {

        public static SharedPreferences sharedPreferences=null;
        public static SharedPreferences.Editor editor=null;

        public static String SHAREDPREFERENCES = "SmartReva";
        public static String IS_LOGGED_IN = "isloggedin";
        public static String USER_ID = "user_ID";
        public static String TOKEN = "token";
        public static String IS_ALUMNI = "alumni";

        public static void initSharedPreferenceData(SharedPreferences sharedPreferences1) {
            sharedPreferences = sharedPreferences1;
            editor = sharedPreferences.edit();
        }

        public static boolean isSharedPreferenceInited(){
            if(sharedPreferences!=null)
                return true;
            return false;
        }

        public static String getIsLoggedIn() {
            return sharedPreferences.getString(IS_LOGGED_IN, "NOT_AVALIBLE");
        }

        public static String getUserId() {
            return sharedPreferences.getString(USER_ID, "NOT_AVALIBLE");
        }

        public static String getTOKEN() {
            return sharedPreferences.getString(TOKEN, "NOT_AVALIBLE");
        }

        public static String getIsAlumni(){
            return sharedPreferences.getString(IS_ALUMNI, "NOT_AVALIBLE");
        }

        public static void setIsAlumni(String isAlumni){
            editor.putString(IS_ALUMNI, "yes");
            editor.apply();
        }

        public static void setIsLoggedIn(String isLoggedIn) {
            editor.putString(IS_LOGGED_IN, "yes");
            editor.apply();
        }

        public static void setTOKEN(String token) {
            editor.putString(TOKEN, token);
            editor.apply();
        }

        public static void setUserId(String userId) {
            editor.putString(USER_ID, userId);
            editor.apply();
        }

        public static void clearData() {
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
                Toast toast = Toast.makeText(context, "No Internet Connnection!!! Try Again", Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        }

        public static boolean checkForSpecial(String s) {
            Pattern p = Pattern.compile("[^A-Za-z0-9 -.@]");
            Matcher m = p.matcher(s);
            boolean b = m.find();
           return  b;
        }
    }

    public static class TimeSlots {

        public static ArrayList<String> SLOTS = new ArrayList<String>();
        public static ArrayList<String> AVALIBILITY = new ArrayList<String>();

        public static void clearData() {
            SLOTS.clear();
            AVALIBILITY.clear();
        }
    }

    public static class FireBase{
        public static String token;
    }




}
