package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sweet.home.pinerria1.Activity.Login;
import sweet.home.pinerria1.Activity.MainActivitie;
import sweet.home.pinerria1.Activity.WellcomeScr;
import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentMgs extends Fragment {


    public SentMgs() {
        // Required empty public constructor
    }
    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Adapter  listAdapter;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sent_mgs, container, false);
            AllProducts = new ArrayList<>();
            expListView = (GridView) view.findViewById(R.id.lvExp);


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);






        sentMgs();


        return view;
    }

    private void sentMgs() {
        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.PostMsg, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseMGS",response.toString());
                Util.cancelPgDialog(dialog);


                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();

                        map.put("_id", jsonObject.optString("_id"));
                        map.put("recievedByRole", jsonObject.optString("recievedByRole"));
                        map.put("subject", jsonObject.optString("subject"));
                        map.put("description", jsonObject.optString("description"));
                        map.put("createdOn", jsonObject.optString("createdOn"));
                        Adapter adapter=new Adapter();
                        expListView.setAdapter(adapter);
                        AllProducts.add(map);


//                        HashMap<String,String> map=new HashMap<>();
//                        for (int i=0;i<20;i++) {
//                            map.put("name", "Name");
//                            Adapter adapter=new Adapter();
//                            expListView.setAdapter(adapter);
//                            AllProducts.add(map);
//                        }
//



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


        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(25000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    class Adapter extends BaseAdapter {

        LayoutInflater inflater;
        TextView title,desc,date,firstChar;

        Adapter() {
            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return AllProducts.size();
        }

        @Override
        public Object getItem(int position) {
            return AllProducts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView=inflater.inflate(R.layout.list_sen_mgs,parent,false);

            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
            date=convertView.findViewById(R.id.date);
            firstChar=convertView.findViewById(R.id.firstChar);


            title.setText(AllProducts.get(position).get("recievedByRole"));
            desc.setText(AllProducts.get(position).get("description"));
            String firtsCha = AllProducts.get(position).get("recievedByRole").substring(0, Math.min(AllProducts.get(position).get("recievedByRole").length(), 1));
            firstChar.setText(firtsCha.toUpperCase());
            String upToNCharacters = AllProducts.get(position).get("createdOn").substring(0, Math.min(AllProducts.get(position).get("createdOn").length(), 10));
            date.setText(upToNCharacters);

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);



            return convertView;
        }
    }

}
