package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sweet.home.homesweethome.Activity.Login;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeMgs extends Fragment {


    public ComposeMgs() {
        // Required empty public constructor
    }


    Spinner spiner;
    String[] str = { "To", "Principal", "Class Teacher", "Dean" };
    String spiVal,spiVal2;
    EditText edit_sub,edit_msg;
    Button submitdata;
    Dialog dialog;
    ArrayAdapter aa;
    List<String> CatList = new ArrayList<String>();
    List<HashMap<String,String>> AllProducts ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_compose_mgs, container, false);
        spiner=view.findViewById(R.id.spiner);
        edit_sub=view.findViewById(R.id.edit_sub);
        edit_msg=view.findViewById(R.id.edit_msg);
        submitdata=view.findViewById(R.id.submitdata);

        AllProducts = new ArrayList<>();

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spiVal=AllProducts.get(i).get("name").toString();
                spiVal2=AllProducts.get(i).get("value").toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                sendData();
                Log.d("dfsfsdfsdfsd",spiVal);
                Log.d("dfsfsdfsdfsd",spiVal2);

                if (!spiVal.equalsIgnoreCase("To")) {
                    if (!TextUtils.isEmpty(edit_sub.getText().toString())) {
                        if (!TextUtils.isEmpty(edit_msg.getText().toString())) {
                            sendData();
                        }
                        else{
                            Toast.makeText(getActivity(), "Please input Message", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Please input Subject", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please select Role", Toast.LENGTH_SHORT).show();
                }
            }
        });


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.userroles, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseRole",response.toString());
                Util.cancelPgDialog(dialog);

                CatList.clear();
                AllProducts.clear();
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("name", "To");
                map2.put("value", "To");
                AllProducts.add(map2);
                CatList.add("To");
//.
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();

                        map.put("name", jsonObject.optString("name"));
                        map.put("value", jsonObject.optString("value"));


                        CatList.add(jsonObject.optString("value"));

                        AllProducts.add(map);

                        aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,CatList);
                        aa.setDropDownViewResource(R.layout.simple_spinner_item);
                        spiner.setAdapter(aa);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorMGS",error.toString());
                Util.cancelPgDialog(dialog);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                String authToken = MyPrefrences.getToken(getActivity());
                String bearer = "Bearer ".concat(authToken);
                header.put("Authorization", bearer);

                return header;

            }
        };


        Log.d("sdfghfhfhfghfsdfsdfsdf",Profile.ClassId);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(25000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


        return view;
    }

    private void sendData() {

        Util.showPgDialog(dialog);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Api.PostMsg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Util.cancelPgDialog(dialog);
                Log.e("dfsjfdfsdsdffdffgd", "MGS Response: " + response);

                Toast.makeText(getActivity(), "Message Post Successfully... ", Toast.LENGTH_SHORT).show();

                Fragment fragment = new Message();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.cancelPgDialog(dialog);
                Log.e("fdgdfgdfdfdfsdfgd", "MGS Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.e("fgdfgdfgdf","Inside getParams");

                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("recievedByRole", spiVal.toString());
                params.put("displayRole", spiVal2.toString());
                params.put("subject", edit_sub.getText().toString());
                params.put("description", edit_msg.getText().toString());
                params.put("classId", Profile.ClassId);
                params.put("childId", Profile.sId);


                Log.d("Sfsdsdgsdgsd1",spiVal.toString());
                Log.d("Sfsdsdgsdgsd2",spiVal2.toString());
                Log.d("Sfsdsdgsdgsd3",edit_sub.getText().toString());
                Log.d("Sfsdsdgsdgsd4",edit_msg.getText().toString());
                Log.d("Sfsdsdgsdgsd5",Profile.ClassId);
                Log.d("Sfsdsdgsdgsd6",Profile.sId);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                String authToken = MyPrefrences.getToken(getActivity());
                String bearer = "Bearer ".concat(authToken);
                header.put("Authorization", bearer);
                return header;
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
