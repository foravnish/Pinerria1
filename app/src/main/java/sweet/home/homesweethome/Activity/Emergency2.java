package sweet.home.homesweethome.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

public class Emergency2 extends AppCompatActivity {

    Button submit;
    EditText person2,personMob2,personRelation2;
    CheckBox checkbox;
    Dialog dialog;
    JSONObject cartItemsObjedct;
    JSONObject parentsInformation;
    JSONObject  jsonObjectChild;
    JSONArray jsonArray123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency2);
        submit=findViewById(R.id.submit);
        checkbox=findViewById(R.id.checkbox);
        person2=findViewById(R.id.person2);
        personMob2=findViewById(R.id.personMob2);
        personRelation2=findViewById(R.id.personRelation2);

        dialog=new Dialog(Emergency2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {

                    if (checkbox.isChecked() == true) {

                        try {

                            JSONObject cartItemsObjedct2;
                            JSONObject cartItemsObjedct3;
                            cartItemsObjedct = new JSONObject();

                            cartItemsObjedct.putOpt("fatherMobileNumber", getIntent().getStringExtra("fatherMob"));
                            cartItemsObjedct.putOpt("motherMobileNumber", getIntent().getStringExtra("motherMob"));

                            cartItemsObjedct2 = new JSONObject();
                            cartItemsObjedct3 = new JSONObject();

                            cartItemsObjedct2.putOpt("mobileNumber", getIntent().getStringExtra("motherMob"));
                            cartItemsObjedct2.putOpt("name", getIntent().getStringExtra("person1"));
                            cartItemsObjedct2.putOpt("relationShip", getIntent().getStringExtra("personRelation"));

                            cartItemsObjedct3.putOpt("mobileNumber", personMob2.getText().toString());
                            cartItemsObjedct3.putOpt("name", person2.getText().toString());
                            cartItemsObjedct3.putOpt("relationShip", personRelation2.getText().toString());

                            cartItemsObjedct.putOpt("otherPerson1", cartItemsObjedct2);
                            cartItemsObjedct.putOpt("otherPerson2", cartItemsObjedct3);

                            Log.d("sdfsdfsdfdfdfddfsdf", cartItemsObjedct.toString());


                            Log.d("fatherData", getIntent().getStringExtra("fatherData"));
                            Log.d("motherData", getIntent().getStringExtra("motherData"));
                            Log.d("driver", getIntent().getStringExtra("driver"));
                            Log.d("language", getIntent().getStringExtra("language"));
                            Log.d("maritalStatus", getIntent().getStringExtra("maritalStatus"));
                            Log.d("reason", getIntent().getStringExtra("reason"));


                            JSONObject father = new JSONObject(getIntent().getStringExtra("fatherData"));

                            JSONObject jfather = new JSONObject();
                            String fadd = father.optString("address");
                            String fedu = father.optString("education");
                            String fema = father.optString("emailId");
                            String fmob = father.optString("mobileNumber");
                            String fname = father.optString("name");
                            String focc = father.optString("occupation");
                            String foffi = father.optString("officeNumber");
                            String fwork = father.optString("workPlace");

                            jfather.put("address", fadd);
                            jfather.put("education", fedu);
                            jfather.put("emailId", fema);
                            jfather.put("mobileNumber", fmob);
                            jfather.put("name", fname);
                            jfather.put("occupation", focc);
                            jfather.put("officeNumber", foffi);
                            jfather.put("workPlace", fwork);

                            JSONObject mother = new JSONObject(getIntent().getStringExtra("motherData"));

                            JSONObject jmother = new JSONObject();
                            String madd = mother.optString("address");
                            String medu = mother.optString("education");
                            String mema = mother.optString("emailId");
                            String mmob = mother.optString("mobileNumber");
                            String mname = mother.optString("name");
                            String mocc = mother.optString("occupation");
                            String moffi = mother.optString("officeNumber");
                            String mwork = mother.optString("workPlace");

                            jmother.put("address", madd);
                            jmother.put("education", medu);
                            jmother.put("emailId", mema);
                            jmother.put("mobileNumber", mmob);
                            jmother.put("name", mname);
                            jmother.put("occupation", mocc);
                            jmother.put("officeNumber", moffi);
                            jmother.put("workPlace", mwork);


                            JSONObject driver = new JSONObject(getIntent().getStringExtra("driver"));

                            JSONObject jdri = new JSONObject();
                            String jdriMob = driver.optString("mobileNumber");
                            String jdriname = driver.optString("name");

                            jdri.put("mobileNumber", jdriMob);
                            jdri.put("name", jdriname);


                            parentsInformation = new JSONObject();

                            Log.d("language", getIntent().getStringExtra("language"));
                            Log.d("maritalStatus", getIntent().getStringExtra("maritalStatus"));
                            Log.d("reason", getIntent().getStringExtra("reason"));

                            parentsInformation.put("father", jfather);
                            parentsInformation.put("mother", jmother);
                            parentsInformation.put("driver", jdri);

                            parentsInformation.put("language", getIntent().getStringExtra("language"));
                            parentsInformation.put("maritalStatus", getIntent().getStringExtra("maritalStatus"));
                            parentsInformation.put("reason", getIntent().getStringExtra("reason"));


                            Log.d("gfsdgdfgdfgdfgd",getIntent().getStringExtra("childData"));

                            Log.d("childData", getIntent().getStringExtra("childData"));
                            Log.d("emergencyInformation", cartItemsObjedct.toString());

                            JSONArray jsonArrayChild = new JSONArray(getIntent().getStringExtra("childData"));

                            jsonArray123 = new JSONArray();

                            for (int i = 0; i < jsonArrayChild.length(); i++) {
                                JSONObject jsonObjectData = jsonArrayChild.getJSONObject(i);
                                jsonObjectData.put("_id", jsonObjectData.get("_id"));
                                jsonObjectData.put("address", jsonObjectData.get("address"));
                                jsonObjectData.put("childAllergy", jsonObjectData.get("childAllergy"));
                                jsonObjectData.put("childHealth", jsonObjectData.get("childHealth"));
                                jsonObjectData.put("childNote", jsonObjectData.get("childNote"));
                                jsonObjectData.put("childProblems", jsonObjectData.get("childProblems"));
                                jsonObjectData.put("gender", jsonObjectData.get("gender"));
                                jsonObjectData.put("dob", jsonObjectData.get("dob"));
                                jsonObjectData.put("name", jsonObjectData.get("name"));

                                //jsonObjectData.put("siblingDetail",jsonObjectData.get("siblingDetail"));


                                // jsonObjectData.put("classes",jsonObjectData.get("classes"));
                                //jsonObjectData.put("dob",jsonObjectData.get("dob"));
                                //  jsonObjectData.put("gender",jsonObjectData.get("gender"));
                                // jsonObjectData.put("image",jsonObjectData.get("image"));
                                // jsonObjectData.put("name",jsonObjectData.get("name"));
                                jsonObjectData.put("nationality", jsonObjectData.get("nationality"));
                                jsonObjectData.put("previousSchoolAttended", jsonObjectData.get("previousSchoolAttended"));
                                jsonObjectData.put("previousSchoolDetail", jsonObjectData.get("previousSchoolDetail"));


                                JSONArray jsonArraySubling = new JSONArray(jsonObjectData.get("siblingDetail").toString());
                                JSONArray jsonArray14 = new JSONArray();
                                for (int j = 0; j < jsonArraySubling.length(); j++) {
                                    JSONObject jsonObjectDataSib = jsonArraySubling.getJSONObject(j);
                                    Log.d("fdsgsdgfdgdfgd1", jsonObjectDataSib.get("age").toString());
                                    Log.d("fdsgsdgfdgdfgd2", jsonObjectDataSib.get("name").toString());

                                    jsonArray14.put(jsonObjectDataSib);
                                }

                                jsonObjectChild = new JSONObject();
                                jsonObjectChild.put("_id", jsonObjectData.get("_id"));
                                jsonObjectChild.put("address", jsonObjectData.get("address"));
                                jsonObjectChild.put("childAllergy", jsonObjectData.get("childAllergy"));
                                jsonObjectChild.put("childHealth", jsonObjectData.get("childHealth"));
                                jsonObjectChild.put("childNote", jsonObjectData.get("childNote"));
                                jsonObjectChild.put("childProblems", jsonObjectData.get("childProblems"));
                                jsonObjectChild.put("nationality", jsonObjectData.get("nationality"));
                                jsonObjectChild.put("previousSchoolAttended", jsonObjectData.get("previousSchoolAttended"));
                                jsonObjectChild.put("previousSchoolDetail", jsonObjectData.get("previousSchoolDetail"));
                                jsonObjectChild.put("gender", jsonObjectData.get("gender"));
                                jsonObjectChild.put("dob", jsonObjectData.get("dob"));
                                jsonObjectChild.put("name", jsonObjectData.get("name"));
                                jsonObjectChild.put("siblingDetail", jsonArray14);

                                jsonArray123.put(jsonObjectChild);


                            }

                            Log.d("sfsdfsdgsfdgsd", jsonArray123.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        childDataSubmit(cartItemsObjedct, parentsInformation, jsonArray123);
                        Util.showPgDialog(dialog);

                    } else {
                        Util.errorDialog2(Emergency2.this,"Please check I have read Home Sweet Home terms.");
//                        Toast.makeText(Emergency2.this, "Please check I have read...", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void childDataSubmit(JSONObject jsonObjectEmergency,JSONObject jsoFather,JSONArray arrChild) {

        JSONObject jsonObject1=new JSONObject();
        try {
            jsonObject1.put("child",arrChild);
            jsonObject1.put("parentsInformation",jsoFather);
            jsonObject1.put("emergencyInformation",jsonObjectEmergency);

            Log.d("gfsdsdgdgd", String.valueOf(arrChild));
            Log.d("gfsdsdgdgd", String.valueOf(jsoFather));
            Log.d("gfsdsdgdgd", String.valueOf(jsonObjectEmergency));

            Log.d("dfsdfgsdfgsdgsg",jsonObject1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(Emergency2.this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, Api.Registration, jsonObject1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Util.cancelPgDialog(dialog);
                        Log.d("dfsdfsdfggdfgdfgdf", response.toString());

                        Intent intent=new Intent(Emergency2.this,Successfully.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Util.cancelPgDialog(dialog);
                VolleyLog.d("dsfsdfsdfsdf", "Error: " + error.getMessage());
//                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headnoers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
//
                String authToken = MyPrefrences.getToken(getApplicationContext());
                String bearer = "Bearer ".concat(authToken);
                headers.put("Authorization", bearer);
//

                return headers;
            }
        };
        queue.add(jsonObjReq);


//        RequestQueue queue = Volley.newRequestQueue(Emergency2.this);
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                Api.Registration, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Util.cancelPgDialog(dialog);
//                Log.e("dfsjfdfsdfgd", "submit Response: " + response);
//
//                Intent intent=new Intent(Emergency2.this,Successfully.class);
//                startActivity(intent);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Util.cancelPgDialog(dialog);
//                Log.e("fdgdfgdfgd", "Some Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }){
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Log.e("fgdfgdfgdf","Inside getParams");
//
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<>();
//                params.put("child", getIntent().getStringExtra("childData"));
//                params.put("parentsInformation", jsonObject.toString().replace("\\", ""));
//                params.put("emergencyInformation", jsonObjectEm.toString());
//
//                Log.d("childData", getIntent().getStringExtra("childData"));
//                Log.d("parentsInformation", jsonObject.toString().replace("\\\\\\", ""));
//                Log.d("emergencyInformation", jsonObjectEm.toString().replace("\\\\\\", ""));
//
//                return params;
//            }
//
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> header = new HashMap<>();
//
//                String authToken = MyPrefrences.getToken(getApplicationContext());
//                String bearer = "Bearer ".concat(authToken);
//                header.put("Authorization", bearer);
//
//                header.put("Content-Type", "application/json; charset=utf-8");
//
//                return header;
//            }
//
//        };
//
//        queue.add(strReq);


//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            String URL = Api.Registration;
//            JSONObject jsonBody = new JSONObject();
//
////
////            Log.d("childData", getIntent().getStringExtra("childData"));
////            Log.d("parentsInformation", jsonObject.toString().replaceAll("\\\\", ""));
////            Log.d("emergencyInformation", cartItemsObjedct.toString());
//
//
//
//            jsonBody.putOpt("child", getIntent().getStringExtra("childData").replaceAll("\\\\", ""));
//            jsonBody.putOpt("parentsInformation", jsonObject.toString().replaceAll("\\\\", ""));
//            jsonBody.putOpt("emergencyInformation", cartItemsObjedct.toString().replaceAll("\\\\", ""));
//
//            final String requestBody = jsonBody.toString();
//
//            Log.d("sdfdsfsdfsdfsdfs",requestBody);
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.i("VOLLEY123", response);
//                    Util.cancelPgDialog(dialog);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("VOLLEY321", error.toString());
//                    Util.cancelPgDialog(dialog);
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return requestBody == null ? null : requestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                        return null;
//                    }
//                }
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> header = new HashMap<>();
//                   // header.put("Content-Type", "application/json; charset=utf-8");
//                    String authToken = MyPrefrences.getToken(getApplicationContext());
//                    String bearer = "Bearer ".concat(authToken);
//                    header.put("Authorization", bearer);
//
//                    return header;
//                }
//
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//                        responseString = String.valueOf(response.statusCode);
//                        // can get more details such as response.headers
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
//            };
//
//            requestQueue.add(stringRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }


    private boolean validate(){

//        if (TextUtils.isEmpty(person2.getText().toString()))
//        {
//            person2.setError("Oops! Person 2 Name blank");
//            person2.requestFocus();
//            return false;
//        }
//
//        else if (TextUtils.isEmpty(personMob2.getText().toString()))
//        {
//            personMob2.setError("Oops! Person 2 Mobile blank");
//            personMob2.requestFocus();
//            return false;
//        }
//        else if (TextUtils.isEmpty(personRelation2.getText().toString()))
//        {
//            personRelation2.setError("Oops! Person 2 Relationship blank");
//            personRelation2.requestFocus();
//            return false;
//        }

        return true;

    }


}
