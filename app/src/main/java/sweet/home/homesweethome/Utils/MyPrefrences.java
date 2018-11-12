package sweet.home.homesweethome.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 2/7/2017.
 */

public class MyPrefrences {

    static SharedPreferences loginPreferences;
    public static SharedPreferences userIdPrefences;
    static SharedPreferences cataPrefences;
    static SharedPreferences subCataPrefences;
    static SharedPreferences username;
    static SharedPreferences emaildid;
    static SharedPreferences Mobile;
    static SharedPreferences Token;
    static SharedPreferences Notification;
    static SharedPreferences NOofChild;
    static SharedPreferences childList;
    static SharedPreferences position;
    static SharedPreferences positionFood;
    static SharedPreferences mySharedPreferencesToken;
    static SharedPreferences myColorGendder;

    public static String USER_LOGIN = "userlogin";
    public static String USER_ID = "user_id";
    public static String CATA_ID = "CATA_ID";
    public static String SCATA_ID = "SCATA_ID";
    public static String USERNAME = "USERNAME";
    public static String EMAILID = "EMAILID";
    public static String MOBILE = "MOBILE";
    public static String NOTI = "NOTI";
    public static String TOKENID = "TOEKNID";
    public static String NOCHILD = "NOCHILD";
    public static String CHILDLIST = "CHILDLIST";


    public static void resetPrefrences(Context context) {

        setUserLogin(context, false);
        setUserID(context, "");
        setCatID(context, "");
        setSCatID(context, "");


    }



    public static void setUserLogin(Context context, boolean is) {
        loginPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putBoolean(USER_LOGIN, is);
        editor.commit();
    }

    public static boolean getUserLogin(Context context) {
        loginPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return loginPreferences.getBoolean(USER_LOGIN,false);

    }

    public static void setNotiStatus(Context context, boolean is) {
        Notification = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = Notification.edit();
        editor.putBoolean(NOTI, is);
        editor.commit();
    }

    public static boolean getNotiStatus(Context context) {
        Notification = PreferenceManager.getDefaultSharedPreferences(context);
        return Notification.getBoolean(NOTI,true);
    }



    public static void setUserID(Context context, String is) {
        userIdPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = userIdPrefences.edit();
        editor.putString(USER_ID, is);
        editor.commit();
    }

    public static String getUserID(Context context) {
        userIdPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        return userIdPrefences.getString(USER_ID,"");
    }

    public static void setCatID(Context context, String is) {
        cataPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = cataPrefences.edit();
        editor.putString(CATA_ID, is);
        editor.commit();
    }

    public static String getCatID(Context context) {
        cataPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        return cataPrefences.getString(CATA_ID,"");
    }


    public static void setSCatID(Context context, String is) {
        subCataPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = subCataPrefences.edit();
        editor.putString(SCATA_ID, is);
        editor.commit();
    }

    public static String getSCatID(Context context) {
        subCataPrefences = PreferenceManager.getDefaultSharedPreferences(context);
        return subCataPrefences.getString(SCATA_ID,"");
    }



    public static void setUSENAME(Context context, String is) {
        username = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = username.edit();
        editor.putString(USERNAME, is);
        editor.commit();
    }

    public static String getUSENAME(Context context) {
        username = PreferenceManager.getDefaultSharedPreferences(context);
        return username.getString(USERNAME,"");
    }




    public static void setEMAILID(Context context, String is) {
        emaildid = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = emaildid.edit();
        editor.putString(EMAILID, is);
        editor.commit();
    }

    public static String getEMAILID(Context context) {
        emaildid = PreferenceManager.getDefaultSharedPreferences(context);
        return emaildid.getString(EMAILID,"");
    }


    public static void setMobile(Context context, String is) {
        Mobile = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = Token.edit();
        editor.putString(MOBILE, is);
        editor.commit();
    }

    public static String getMobile(Context context) {
        Mobile = PreferenceManager.getDefaultSharedPreferences(context);
        return Mobile.getString(MOBILE,"");
    }


     public static void setToken(Context context, String is) {
            Token = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = Token.edit();
            editor.putString(TOKENID, is);
            editor.commit();
        }

        public static String getToken(Context context) {
            Token = PreferenceManager.getDefaultSharedPreferences(context);
            return Token.getString(TOKENID,"");
        }




    public static void setNoOfChild(Context context, String is) {
        NOofChild = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = NOofChild.edit();
        editor.putString(NOCHILD, is);
        editor.commit();
    }

    public static String getNoOfChild(Context context) {
        NOofChild = PreferenceManager.getDefaultSharedPreferences(context);
        return NOofChild.getString(NOCHILD,"");
    }


    public static void setChildList(Context context, String is) {
        childList = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = childList.edit();
        editor.putString(CHILDLIST, is);
        editor.commit();
    }

    public static String getChildList(Context context) {
        childList = PreferenceManager.getDefaultSharedPreferences(context);
        return childList.getString(CHILDLIST,"");
    }





    public static void setPosition(Context context, String is) {
        position = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = position.edit();
        editor.putString("POSITION", is);
        editor.commit();
    }

    public static String getPosition(Context context) {
        position = PreferenceManager.getDefaultSharedPreferences(context);
        return position.getString("POSITION","");
    }


    public static void setPositionFood(Context context, String is) {
        positionFood = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = positionFood.edit();
        editor.putString("POSITIONFOOD", is);
        editor.commit();
    }

    public static String getPositionFood(Context context) {
        positionFood = PreferenceManager.getDefaultSharedPreferences(context);
        return positionFood.getString("POSITIONFOOD","");
    }

    public static String getgcm_token(Context context) {
        mySharedPreferencesToken = PreferenceManager.getDefaultSharedPreferences(context);
        return mySharedPreferencesToken.getString("gcm_token", "");
    }
    public static void setgcm_token(Context context, String Value) {
        mySharedPreferencesToken = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor sharedpreferenceeditor = mySharedPreferencesToken.edit();
        sharedpreferenceeditor.putString("gcm_token", Value);
        sharedpreferenceeditor.commit();
    }



    public static String getColorGender(Context context) {
        myColorGendder = PreferenceManager.getDefaultSharedPreferences(context);
        return myColorGendder.getString("color_gender", "");
    }
    public static void setColorGender(Context context, String Value) {
        myColorGendder = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor sharedpreferenceeditor = myColorGendder.edit();
        sharedpreferenceeditor.putString("color_gender", Value);
        sharedpreferenceeditor.commit();
    }


}
