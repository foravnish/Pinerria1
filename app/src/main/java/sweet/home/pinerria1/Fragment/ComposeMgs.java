package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class ComposeMgs extends Fragment {


    public ComposeMgs() {
        // Required empty public constructor
    }


    Spinner spiner;
    String[] str = { "To", "Principal", "Class Teacher", "Dean" };
    String spiVal;
    EditText edit_sub,edit_msg;
    Button submitdata;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_compose_mgs, container, false);
        spiner=view.findViewById(R.id.spiner);
        edit_sub=view.findViewById(R.id.edit_sub);
        edit_msg=view.findViewById(R.id.edit_msg);
        submitdata=view.findViewById(R.id.submitdata);



        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,str);
        aa.setDropDownViewResource(R.layout.simple_spinner_item);

        spiner.setAdapter(aa);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spiVal=spiner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!spiVal.equalsIgnoreCase("To")) {
                    sendData();
                }
                else{
                    Toast.makeText(getActivity(), "Please select role...", Toast.LENGTH_SHORT).show();
                }
            }
        });



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
                params.put("recievedByRole", spiVal.toLowerCase());
                params.put("subject", edit_sub.getText().toString());
                params.put("description", edit_msg.getText().toString());

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
