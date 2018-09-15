package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.LinearLayout;
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
public class Timeline extends Fragment {


    public Timeline() {
        // Required empty public constructor
    }
    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_timeline, container, false);
        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);



        TimeLineCalander();

        return view;
    }

    private void TimeLineCalander() {

        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Calender, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseCala",response.toString());
                Util.cancelPgDialog(dialog);


                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();

                        map.put("_id", jsonObject.optString("_id"));
                        map.put("title", jsonObject.optString("title"));
                        map.put("description", jsonObject.optString("description"));
                        map.put("start", jsonObject.optString("start"));
                        map.put("end", jsonObject.optString("end"));
                        map.put("colorItem", jsonObject.optString("colorItem"));
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
        TextView title,desc,dateM,dayM;
        LinearLayout linearColor;

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

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView=inflater.inflate(R.layout.list_timeline,parent,false);
            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
            dateM=convertView.findViewById(R.id.dateM);
            dayM=convertView.findViewById(R.id.dayM);
            linearColor=convertView.findViewById(R.id.linearColor);

            title.setText(AllProducts.get(position).get("title"));
            desc.setText(AllProducts.get(position).get("description"));

            String year=AllProducts.get(position).get("start").substring(0,4);
            String months=AllProducts.get(position).get("start").substring(5,7);
            String mDate=AllProducts.get(position).get("start").substring(8,10);

            dateM.setText(mDate+"-"+months+"-"+year);

//            dateM.setText(AllProducts.get(position).get("start").substring(0, Math.min(AllProducts.get(position).get("start").length(), 10)));
            //dayM.setText(AllProducts.get(position).get("start"));

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);
            dateM.setTypeface(tvFont);
          //  dayM.setTypeface(tvFont);


            if (AllProducts.get(position).get("colorItem").equalsIgnoreCase("blue")){
//                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5ba1cf")));
            }
            else if (AllProducts.get(position).get("colorItem").equalsIgnoreCase("red")){
                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f45c71")));
            }

            return convertView;
        }
    }

}
