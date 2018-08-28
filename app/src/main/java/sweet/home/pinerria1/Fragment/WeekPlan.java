package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekPlan extends Fragment {


    public WeekPlan() {
        // Required empty public constructor
    }

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Dialog dialog;
    TextView bnt_Week1,bnt_Week2,bnt_Week3,bnt_Week4,bnt_Week5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_week_plan, container, false);

        ImageView textBack= view.findViewById(R.id.textBack);

        ImageView bnt_Week1= view.findViewById(R.id.bnt_Week1);
        ImageView bnt_Week2= view.findViewById(R.id.bnt_Week2);
        ImageView bnt_Week3= view.findViewById(R.id.bnt_Week3);
        ImageView bnt_Week4= view.findViewById(R.id.bnt_Week4);
        ImageView bnt_Week5= view.findViewById(R.id.bnt_Week5);


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Profile();
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });


        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);

        bnt_Week1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bnt_Week2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bnt_Week3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bnt_Week4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bnt_Week5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getWeekPlanData();
        return view;
    }

    private void getWeekPlanData() {

        Log.d("sdgfdsgdgdfgd",getArguments().getString("ClassId"));
            Util.showPgDialog(dialog);
            //// TODO Header APi
            JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.WeeklyPlan+getArguments().getString("ClassId"),null,
//            JsonObjectRequest parentMeRequest = new JsonObjectRequest("http://35.184.93.23:3000/api/weekly-plan/getmonthandweekbydata/5b01bbac5965f71240c7cdef",null,
//            JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.parent,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.print(response);
                            Util.cancelPgDialog(dialog);
                            Log.d("WeeklyPlanResponse",response.toString());

                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    System.out.println(volleyError.toString());
                    Util.cancelPgDialog(dialog);
                    Log.d("WeeklyErrorResponse",volleyError.toString());
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> header = new HashMap<>();
                    Log.d("vcvxcvcvdfgfgd",MyPrefrences.getToken(getActivity()));

                    String authToken = MyPrefrences.getToken(getActivity());
                    String bearer = "Bearer ".concat(authToken);
                    header.put("Authorization", bearer);

                    return header;
                }
            };
            //System.out.print("called twice");
//                    SingletonRequestQueue.getInstance(getActivity()).getRequestQueue().add(parentMeRequest);
            AppController.getInstance().addToRequestQueue(parentMeRequest);
    }

    class Adapter extends BaseAdapter {

        LayoutInflater inflater;
        TextView title;

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


            convertView=inflater.inflate(R.layout.list_week_plan,parent,false);
            title=convertView.findViewById(R.id.title);


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);



            return convertView;
        }
    }


}
