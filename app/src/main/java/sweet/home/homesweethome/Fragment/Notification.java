package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {


    public Notification() {
        // Required empty public constructor
    }

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;

    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);

//        HashMap<String,String> map=new HashMap<>();
//        for (int i=0;i<20;i++) {
//            map.put("name", "Name");
//            Adapter adapter=new Adapter();
//            expListView.setAdapter(adapter);
//            AllProducts.add(map);
//        }

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        NotificationApi();


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

        return view;
    }

    private void NotificationApi() {

        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Notification+"?pagenum=&perpage=", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseNotification",response.toString());
                Util.cancelPgDialog(dialog);

                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);

                        HashMap<String,String> map=new HashMap<>();

                        map.put("_id", jsonObject.optString("_id"));
                        map.put("recordId", jsonObject.optString("recordId"));
                        map.put("type", jsonObject.optString("type"));
                        map.put("title", jsonObject.optString("title"));
                        map.put("language", jsonObject.optString("language"));
                        map.put("description", jsonObject.optString("description"));
                        map.put("createdByRole", jsonObject.optString("createdByRole"));
                        map.put("createdOn", jsonObject.optString("createdOn"));


                        Adapter adapter=new Adapter();
                        expListView.setAdapter(adapter);
                        AllProducts.add(map);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errorcala",error.toString());
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
        TextView title,desc,byUser,type,dateBox,dateSet;

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


            convertView=inflater.inflate(R.layout.list_notifiction,parent,false);

            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
            byUser=convertView.findViewById(R.id.byUser);
            type=convertView.findViewById(R.id.type);
            dateBox=convertView.findViewById(R.id.dateBox);
            dateSet=convertView.findViewById(R.id.dateSet);


            title.setText(AllProducts.get(position).get("title"));
            desc.setText(AllProducts.get(position).get("description"));
            byUser.setText(AllProducts.get(position).get("createdByRole"));
            type.setText(AllProducts.get(position).get("type"));

            String year1=AllProducts.get(position).get("createdOn").substring(0,4);
            String month1=AllProducts.get(position).get("createdOn").substring(5,7);
            String date1=AllProducts.get(position).get("createdOn").substring(8,10);

            dateSet.setText(date1+"-"+month1+"-"+year1);



            String year=AllProducts.get(position).get("createdOn").substring(0,4);
            String month=AllProducts.get(position).get("createdOn").substring(5,7);

            String year2= null;
            String month2= null;
            try {
                year2 = AllProducts.get(position-1).get("createdOn").substring(0,4);
                month2 = AllProducts.get(position-1).get("createdOn").substring(5,7);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (year.equals(year2)&& month.equals(month2)){

                dateBox.setVisibility(View.GONE);
                dateBox.setText("");

            }
            else {
                dateBox.setVisibility(View.VISIBLE);
                String date=month+"-"+year;
                dateBox.setText(date);

            }

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);
            type.setTypeface(tvFont);


            return convertView;
        }
    }


}
