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


    public static class URLs {
        public static String BASE = "http://35.154.54.229/SmartReva/";
        public static String ALUMNI_BASE = "http://35.154.54.229/SmartReva/alumni/";
        public static String PLACEMENT_BASE = "http://35.154.54.229/SmartReva/placement/";
        public static String PLACEMENT_COURSE_DEPT = "course_dept.php";
        public static String PLACEMENT_PROFILE = "profile.php";
        public static String PLACEMENT_REGISTRATION = "placement_reg.php";
        public static String PLACEMENT_BACKLOG = "back_log.php";
        public static String PLACEMENT_ACADEMIC = "academic_details.php";
        public static String PLACEMENT_BTECH = "btech.php";
        public static String PLACEMENT_MTECH = "mtech.php";
        public static String PLACEMENT_MBAMCOM = "mba_mcom.php";
        public static String PLACEMENT_DEGREE = "degree.php";
        public static String PLACEMENT_MCA = "mca.php";
        public static String PLACEMENT_CHECK = "check_reg.php";
        public static String PLACEMENT_VIEW = "viewprofile.php";
        public static String PLACEMENT_INVITE = "placement_invite.php";
        public static String PLACEMENT_DRIVE_REG = "placement_drives_reg.php";
        public static String PLACEMENT_MY_DRIVE = "mydrives.php";
        public static String PLACEMENT_MENTOR = "mentor.php";
        public static String PLACEMENT_MENTOR_VIEWPROFILE = "mentor_getdata.php";
        public static String PLACEMENT_MENTOR_GETLIST = "mentor_getlist.php";
        public static String LOGIN = "login.php";
        public static String ALUMNI_ABOUT_US = "about.html";
        public static String ALUMNI_CONTACT_UD = "contactUs.html";
        public static String FIREBASE = "firebase_reg.php";
        public static String ALUMNI_LOGIN = "login_alumni.php";
        public static String ALUMNI_FIREBASE = "alumni_firebase.php";
        public static String ALUMNI_REG = "alumni_reg.php";
        public static String ALUMNI_DISSCUSSION = "alumni_disscussion.php";
        public static String ALUMNI_DISSCUSSION_STARS = "alumni_disscussion_stars.php";
        public static String ALUMNI_DISSCUSSION_REPLY = "alumni_disscussion_replies.php";
        public static String ALUMNI_REFER_JOB = "alumni_refer_job.php";
        public static String ALUMNI_NOTIFICATION = "alumni_notification.php";
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
        public static String PLACEMENT_PERSONAL_DETAILS = "placement_registration.php";
        public static String PLACEMENT_UG_DETAILS = "placement_ug.php";
        public static String PLACEMENT_PG_DETAILS = "placement_pg.php";
        public static String PLACEMENT_BACK_DETAILS = "placement_backlogs.php";
        public static String PLACEMENT_TANDC = "placement_tandc.php";
        public static String PLACEMENT_DRIVE_REGISTRATION = "placement_drive_registration.php";
        public static String COURSE_SEM = "course_sem.php";
        public static String COURSE_DEPT = "course_dept.php";
        public static String GET_TIMETABLE = "get_timetable.php";
        public static String TERMS_AND_CONDITION = "termsandcondition.html";
        public static String ABOUTUS = "http://revacounselling.16mb.com/about.html";
    }

    public static class SharedPreferenceData {

        public static SharedPreferences sharedPreferences = null;
        public static SharedPreferences.Editor editor = null;

        public static String SHAREDPREFERENCES = "SmartReva";
        public static String IS_LOGGED_IN = "isloggedin";
        public static String USER_ID = "user_ID";
        public static String NAME = "name";
        public static String TOKEN = "token";
        public static String IS_ALUMNI = "alumni";
        public static String IS_PLACEMENT = "placement";

        public static void initSharedPreferenceData(SharedPreferences sharedPreferences1) {
            sharedPreferences = sharedPreferences1;
            editor = sharedPreferences.edit();
        }

        public static boolean isSharedPreferenceInited() {
            if (sharedPreferences != null)
                return true;
            return false;
        }

        public static String getIsLoggedIn() {
            return sharedPreferences.getString(IS_LOGGED_IN, "NOT_AVALIBLE");
        }

        public static String getUserId() {
            return sharedPreferences.getString(USER_ID, "NOT_AVALIBLE");
        }

        public static String getNAME() {
            return sharedPreferences.getString(NAME, "NOT_AVALIBLE");
        }

        public static String getTOKEN() {
            return sharedPreferences.getString(TOKEN, "NOT_AVALIBLE");
        }

        public static String getIsAlumni() {
            return sharedPreferences.getString(IS_ALUMNI, "NOT_AVALIBLE");
        }

        public static String getIsPlacement() {
            return sharedPreferences.getString(IS_PLACEMENT, "NOT AVALIBLE");
        }

        public static void setNAME(String name) {
            editor.putString(NAME, name);
            editor.apply();
        }

        public static void setIsPlacement(String isPlacement) {
            editor.putString(IS_PLACEMENT, "yes");
            editor.apply();
        }

        public static void setIsAlumni(String isAlumni) {
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
            return b;
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

    public static class FireBase {
        public static String token;
    }

    public static final String passingYears[] = {"dec/jan 2010/11",
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

    public static final String[] Country_name = {"India", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica (Australian bases)", "Antigua and Barbuda",
            "Argentina", "Armenia", "Aruba", "Ascension", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
            "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Indian Ocean Territory", "British Virgin Island", "Brunei", "Bulgaria",
            "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic",
            "Congo, Republic", "Cook Iseland", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Curaçao", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
            "Dominica Republic", "Dominica Republic", "Dominica Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Iseland", "Faroe Iseland", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana",
            "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland",
            "Indonesia", "Iraq", "Iran", "Ireland(Eire)", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kosovo", "Kosovo", "Kuwait", "Kyrgyzstan",
            "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia Republic", "Madagascar", "Malawi",
            "Malaysia", "Maldives", "Mali", "Malta", "Marshall Iseland", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia",
            "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Netherlands", "Netherlands Antilles", "Nepal", "New Caledonia", "New Zealand",
            "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Iseland", "North Korea", "Northern Ireland", "Northern Mariana Iseland", "Norway", "Oman", "Pakistan", "Palau",
            "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico ", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda",
            "Saint-Barthélemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and Grenadines",
            "Samoa", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Sint Maarten", "Singapore", "Slovakia", "Slovenia", "Solomon Iseland",
            "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
            "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Iseland", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
            "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "U.S. Virgin Iseland", "Wallis and Futuna", "West Bank",
            "Yemen", "Zambia", "Zimbabwe"};

    public static final String[] Country_code = {"+91", "+93", "+355", "+213", "+1684", "+376", "+244", "+1264", "+6721", "+1268", "+54", "+374", "+297", "+247", "+61", "+43", "+994", "+1242", "+973",
            "+880", "+1246", "+375", "+32", "501", "+229", "+1441", "+975", "+591", "+387", "+267", "+55", "+243", "+1284", "+673", "+359", "+226", "+257", "+855", "+237", "+1", "+238",
            "+1345", "+236", "+235", "+56", "+86", "+57", "+269", "+243", "+242", "+682", "+506", "+255", "+385", "+53", "599", "+357", "+420", "+45", "+253", "+1767", "+1809", "+1829",
            "+1849", "+670", "+593", "+20", "+503", "+240", "+291", "+372", "+251", "+500", "+298", "+679", "+358", "+33", "+594", "+689", "+241", "+220", "+970", "+995", "+49", "+233", "+350",
            "+30", "+299", "+1473", "+590", "+1671", "+502", "+244", "+245", "+592", "+509", "+504", "+852", "+36", "+354", "+62", "+964", "+98", "+353", "+972", "+39", "+1876",
            "+81", "+962", "+7", "+254", "+686", "+377", "+381", "+386", "+965", "+966", "+856", "+371", "+961", "+266", "+231", "+218", "+423", "+370", "+352", "+853", "+389", "+261", "+265",
            "+60", "+960", "+233", "+356", "+692", "+596", "+222", "+230", "+262", "+52", "+691", "+373", "+377", "+976", "+382", "+1664", "+212", "+258", "+95", "+264", "+674", "+31",
            "+599", "+977", "+687", "+64", "+505", "+227", "+234", "+683", "+6723", "+850", "+4428", "+1670", "+47", "+968", "+92", "+680", "+507", "+675", "+595", "+51", "+63", "+48",
            "+351", "+1787", "+1939", "+974", "+262", "+40", "+7", "+250", "+590", "+290", "+1869", "+1758", "+590", "+508", "+1784", "+658", "+239", "+966", "+221", "+381", "+248", "+232", "+1721"
            , "+65", "+421", "+386", "+677", "+252", "+27", "+82", "+211", "+34", "+94", "+249", "+597", "+268", "+46", "+41", "+963", "+886", "+992", "255", "+66", "+228", "+690", "+676"
            , "+1868", "+216", "+90", "+993", "+1649", "+688", "+256", "+380", "+971", "+44", "+1", "+598", "+998", "+678", "+58", "+84", "+1340", "+681", "+970", "+967", "+260", "+263"};


}
