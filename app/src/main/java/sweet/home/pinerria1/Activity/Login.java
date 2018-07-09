package sweet.home.pinerria1.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

public class Login extends AppCompatActivity {
    Button  bnt_signin;
    EditText edit_email,edit_pwd;
    Dialog dialog;
    Map<String, String> params=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bnt_signin=findViewById(R.id.bnt_signin);
        edit_email=findViewById(R.id.edit_email);
        edit_pwd=findViewById(R.id.edit_pwd);

        dialog=new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);




        bnt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){

                    Util.showPgDialog(dialog);

                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            Api.login, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Util.cancelPgDialog(dialog);
                            Log.e("dfsjfdfsdfgd", "Login Response: " + response);
                            //parse your response here

                            try {
                                JSONObject jsonObject=new JSONObject(response);

                              //  Toast.makeText(getApplicationContext(),"Login Successfully..." , Toast.LENGTH_SHORT).show();

                                MyPrefrences.setUserLogin(getApplicationContext(), true);
                                MyPrefrences.setToken(getApplicationContext(),jsonObject.optString("token") );
//                                            MyPrefrences.setUserID(getApplicationContext(),jsonObject1.optString("id").toString());
//                                            MyPrefrences.setCatID(getApplicationContext(),jsonObject1.optString("cat_id").toString());
//                                            MyPrefrences.setSCatID(getApplicationContext(),jsonObject1.optString("subcat").toString());
//                                            MyPrefrences.setUSENAME(getApplicationContext(),jsonObject1.optString("company_name").toString());
//                                            MyPrefrences.setEMAILID(getApplicationContext(),jsonObject1.optString("c1_email").toString());
//                                            MyPrefrences.setMobile(getApplicationContext(),jsonObject1.optString("c1_mobile1").toString());


                                childList();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Util.cancelPgDialog(dialog);
                            Log.e("fdgdfgdfgd", "Login Error: " + error.getMessage());
                            Toast.makeText(getApplicationContext(),"Please Connect to the Internet or Wrong Password", Toast.LENGTH_LONG).show();
                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Log.e("fgdfgdfgdf","Inside getParams");

                            // Posting parameters to login url
                            Map<String, String> params = new HashMap<>();
                            params.put("username", edit_email.getText().toString());
                            params.put("password", edit_pwd.getText().toString());

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
                   // Adding request to request queue
                    queue.add(strReq);

                }

            }
        });
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


//                                if (response.optString("registrationStatus").equalsIgnoreCase("true")){
//                                    Intent intent =new Intent(Login.this, MainActivitie.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                else if (response.optString("registrationStatus").equalsIgnoreCase("false")){
//
//                                    Intent intent =new Intent(Login.this, WellcomeScr.class);
//                                    startActivity(intent);
//                                    finish();

                                    Intent intent =new Intent(Login.this, WellcomeScr.class);
                                    startActivity(intent);
                                    finish();


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


    private boolean validate(){

        if (TextUtils.isEmpty(edit_email.getText().toString()))
        {
            edit_email.setError("Oops! Mobile field blank");
            edit_email.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(edit_pwd.getText().toString()))
        {
            edit_pwd.setError("Oops! Password field blank");
            edit_pwd.requestFocus();
            return false;
        }

        return true;

    }


    private void childDataSubmit() {

        RequestQueue queue = Volley.newRequestQueue(Login.this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Api.Registration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Util.cancelPgDialog(dialog);
                Log.e("dfsjfdfsdfgd", "submit Response: " + response);
                //parse your response here


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.cancelPgDialog(dialog);
                Log.e("fdgdfgdfgd", "Some Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.e("fgdfgdfgdf","Inside getParams");

                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("child","");
                params.put("parentsInformation", "");
                params.put("emergencyInformation", "");

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




        }


                ;

        // Adding request to request queue
        queue.add(strReq);

    }


}
