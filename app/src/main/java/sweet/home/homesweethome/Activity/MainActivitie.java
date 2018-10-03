package sweet.home.homesweethome.Activity;

import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sweet.home.homesweethome.Fragment.Calender;
import sweet.home.homesweethome.Fragment.Menual;
import sweet.home.homesweethome.Fragment.MenualPDF;
import sweet.home.homesweethome.Fragment.MenualThreeLevel;
import sweet.home.homesweethome.Fragment.Message;
import sweet.home.homesweethome.Fragment.Notification;
import sweet.home.homesweethome.Fragment.Profile;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

public class MainActivitie extends AppCompatActivity {

    ImageView menual,message,alerm,calender,profile;
    String  androidDeviceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        profile=findViewById(R.id.profile);
        calender=findViewById(R.id.calender);
        alerm=findViewById(R.id.alerm);
        message=findViewById(R.id.message);
        menual=findViewById(R.id.menual);


        //displayFirebaseRegId();

        Log.d("sdfsdfvdgdfsg",getIntent().getStringExtra("type"));

        if (getIntent().getStringExtra("type").equalsIgnoreCase("notification")){

            Fragment fragment = new Notification();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment).commit();
        }

        else{
            Fragment fragment = new Profile();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment).commit();

        }
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Profile();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profilehover);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Calender();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calerdarhove);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });

        alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Notification();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notificationhover);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Message();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.messagehover);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });
        menual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new MenualThreeLevel();
//                Fragment fragment = new Menual();
//                Fragment fragment = new MenualPDF();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannualhover);

            }
        });

        sendRegistrationTokenToServer(MyPrefrences.getgcm_token(getApplicationContext()));


    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        final String regId = pref.getString("regId", null);

        Log.d("djfsakljf;sldkfsdk", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)){
            //txtRegId.setText("Firebase Reg Id: " + regId);


            RequestQueue queue = Volley.newRequestQueue(MainActivitie.this);
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    "", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //  Util.cancelPgDialog(dialog);
                    Log.d("ResponseToken", response);
                    //parse your response here



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Util.cancelPgDialog(dialog);
                    Log.e("fdgdfgdfgd", "Login Error: " + error.getMessage());
                    //Toast.makeText(getApplicationContext(),"Please Connect to the Internet or Wrong Password", Toast.LENGTH_LONG).show();
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Log.e("fgdfgdfgdf","Inside getParams");

                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<>();
                    params.put("deviceToken", regId);

                    Log.d("dfsdsdgfdgdfgdfg",regId);

                    return params;
                }

//                        @Override
//                        public Map<String, String> getHeaders() throws AuthFailureError {
//                            Log.e("fdgdfgdfgdfg","Inside getHeaders()");
//                            Map<String,String> headers=new HashMap<>();
//                            headers.put("Content-Type","application/x-www-form-urlencoded");
//                            return headers;
//                        }
            };
            queue.add(strReq);




//            StringRequest postRequest = new StringRequest(Request.Method.POST, "",
//                    new Response.Listener<String>()
//                    {
//                        @Override
//                        public void onResponse(String response) {
//                            // response
//                            Log.d("ResponseToken", response);
//                          //  Util.cancelPgDialog(dialog);
//                            try {
//                                JSONObject jsonObject=new JSONObject(response);
//                                if (jsonObject.getString("status").equalsIgnoreCase("success")){
//
//
//
//                                }
//                                else{
//                                    // Toast.makeText(getApplicationContext(),jsonObject.getString("message") , Toast.LENGTH_SHORT).show();
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    },
//                    new Response.ErrorListener()
//                    {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            // error
//                          //  Toast.makeText(HomeAct.this, "Error! Please connect to the Internet.", Toast.LENGTH_SHORT).show();
//                           // Util.cancelPgDialog(dialog);
//                        }
//                    }
//            ) {
//                @Override
//                protected Map<String, String> getParams()
//                {
//                    Map<String, String>  params = new HashMap<String, String>();
//                    params.put("deviceToken", regId);
//
//                    Log.d("dfsdsdgfdgdfgdfg",regId);
//
//                    return params;
//                }
//            };
//            postRequest.setRetryPolicy(new DefaultRetryPolicy(27000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            postRequest.setShouldCache(false);
//
//            AppController.getInstance().addToRequestQueue(postRequest);







        }
        else {
//            txtRegId.setText("Firebase Reg Id is not received yet!");
            Log.d("djfsakljf;sldkfsdk", "Firebase Reg Id is not received yet!");
        }
    }


    private void sendRegistrationTokenToServer(final String token) {


        androidDeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

//        TelephonyManager telephonyManager;
//        telephonyManager = (TelephonyManager) getSystemService(Context.
//                TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        String deviceId = telephonyManager.getDeviceId();
//
        Log.d("gdfgdfgdfgdfgd",androidDeviceId);


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Api.URL_STORE_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("dfsdfsdfsdfsdfs", "MGS Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("dfsdfsdfsdfsdfs", "MGS Error: " + error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.e("fgdfgdfgdf","Inside getParams");

                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("deviceId", androidDeviceId);
                params.put("deviceType", "android");
                params.put("deviceToken", token);
                params.put("remarks", "true");
                params.put("assessment", "true");
                params.put("events", "true");
                params.put("news", "true");

                Log.d("sdfsdgdgdfgd",androidDeviceId);
                Log.d("sdfsdgdgdfgd",token);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                String authToken = MyPrefrences.getToken(getApplicationContext());
                String bearer = "Bearer ".concat(authToken);
                header.put("Authorization", bearer);
                return header;
            }


        };


        // Adding request to request queue
        queue.add(strReq);




        //Log.w("GCMRegIntentService", "loadUserid:" + id);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_STORE_TOKEN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        try {
//
//                            Log.d("dfsdfsdfsdfsdfs",s);
//                            JSONObject jsonObject=new JSONObject(s);
//                            if (jsonObject.optString("status").equalsIgnoreCase("failure")){
//                                //Toast.makeText(getApplicationContext(), "Some Error! Contact to Admin...", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        Log.d("gfsdgdgdfgdfgd",volleyError.toString());
//                        //Log.w("GCMRegIntentService", "sendRegistrationTokenToServer! ErrorListener:" );
//                        Toast.makeText(getApplicationContext(), "sendRegistrationTokenToServer! ErrorListener", Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("deviceId", androidDeviceId);
//                params.put("deviceType", "android");
//                params.put("deviceToken", token);
//                params.put("remarks", "true");
//                params.put("assessment", "true");
//                params.put("events", "true");
//                params.put("news", "true");
//
//                Log.d("sdfsdgdgdfgd",androidDeviceId);
//                Log.d("sdfsdgdgdfgd",token);
//
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
