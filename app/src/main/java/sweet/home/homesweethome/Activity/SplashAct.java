package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(3*1000);
//
//                    Intent intent = new Intent(SplashAct.this, Login.class);
//                    startActivity(intent);
//                    finish();


                    if (MyPrefrences.getUserLogin(SplashAct.this)==true){

                        childList();

//                        Intent intent =new Intent(SplashAct.this, MainActivity.class);
//                        intent.putExtra("isflag","0");
//                        startActivity(intent);
//                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplashAct.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }

    private void childList() {

        JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.parent,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // System.out.print(response);
                        Log.d("gdfgdfghdfhdhgf",response.toString());


                        try {
                            JSONArray jsonArray=response.getJSONArray("child");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);


                                HashMap<String,String> map=new HashMap<>();

                                map.put("_id", jsonObject.optString("_id"));
                                map.put("name", jsonObject.optString("name"));


                                MyPrefrences.setNoOfChild(getApplicationContext(), String.valueOf(jsonArray.length()));
                                MyPrefrences.setChildList(getApplicationContext(), jsonArray.toString());


                                if (response.optString("registrationStatus").equalsIgnoreCase("true")){
                                    Log.d("sdfsdfsdfsdfs","true");
                                    Intent intent =new Intent(SplashAct.this, MainActivitie.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (response.optString("registrationStatus").equalsIgnoreCase("false")) {
                                    Log.d("sdfsdfsdfsdfs","false");
                                    Intent intent = new Intent(SplashAct.this, WellcomeScr.class);
                                    startActivity(intent);
                                    finish();

//                                    Intent intent =new Intent(SplashAct.this, WellcomeScr.class);
//                                    startActivity(intent);
//                                    finish();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //  System.out.println(volleyError.toString());
                Log.d("gdfgdfghdfhdfddddfdhgf",volleyError.toString());
                Intent intent = new Intent(SplashAct.this, Login.class);
                startActivity(intent);
                finish();


            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                String authToken = MyPrefrences.getToken(getApplicationContext());
                String bearer = "Bearer ".concat(authToken);
                header.put("Authorization", bearer);

                return header;
            }
        };
        //System.out.print("called twice");
//                    SingletonRequestQueue.getInstance(getActivity()).getRequestQueue().add(parentMeRequest);
        AppController.getInstance().addToRequestQueue(parentMeRequest);

    }

}
