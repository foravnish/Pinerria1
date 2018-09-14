package sweet.home.pinerria1.Fragment;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import sweet.home.pinerria1.Activity.Login;
import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment {


    public ChangePassword() {
        // Required empty public constructor
    }
    Button bnt_submit;
    EditText edit_pwdOld,edit_pwdNew,edit_pwdConfirm;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_change_password, container, false);

        edit_pwdConfirm=view.findViewById(R.id.edit_pwdConfirm);
        edit_pwdNew=view.findViewById(R.id.edit_pwdNew);
        edit_pwdOld=view.findViewById(R.id.edit_pwdOld);

        bnt_submit=view.findViewById(R.id.bnt_submit);


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        ImageView textBack= view.findViewById(R.id.textBack);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Profile();
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        bnt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                    Util.showPgDialog(dialog);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            Api.ChangePwd, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Util.cancelPgDialog(dialog);
                            Log.e("dfsjfdfsdsdffdffgd", "PWD Response: " + response);

                            Toast.makeText(getActivity(), "Password Change Successfully... ", Toast.LENGTH_SHORT).show();

                            Fragment fragment = new Profile();
                            FragmentManager manager = getActivity().getSupportFragmentManager();
                            FragmentTransaction ft = manager.beginTransaction();
                            ft.replace(R.id.container, fragment).addToBackStack(null).commit();



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Util.cancelPgDialog(dialog);
                            Log.e("fdgdfgdfdfdfsdfgd", "PWD Error: " + error.getMessage());
                            //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

                            Util.errorDialog(getActivity(),"Old Password not Correct");
                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Log.e("fgdfgdfgdf","Inside getParams");

                            // Posting parameters to login url
                            Map<String, String> params = new HashMap<>();
                            params.put("oldpassword", edit_pwdOld.getText().toString());
                            params.put("password", edit_pwdNew.getText().toString());

                            Log.d("dsfsdfsdfsdfs",edit_pwdOld.getText().toString());
                            Log.d("dsfsdfsdfsdfs",edit_pwdNew.getText().toString());
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
        });

        return view;
    }


    private boolean validate(){

        if (TextUtils.isEmpty(edit_pwdOld.getText().toString()))
        {
            edit_pwdOld.setError("Old Password blank");
            edit_pwdOld.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(edit_pwdNew.getText().toString()))
        {
            edit_pwdNew.setError("Oops! New Password blank");
            edit_pwdNew.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(edit_pwdConfirm.getText().toString()))
        {
            edit_pwdConfirm.setError("Oops! Confirm New Password blank");
            edit_pwdConfirm.requestFocus();
            return false;
        }
        else if (!edit_pwdNew.getText().toString().equalsIgnoreCase(edit_pwdConfirm.getText().toString())){
            Toast.makeText(getActivity(), "Confirm Password should be same.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }



}
