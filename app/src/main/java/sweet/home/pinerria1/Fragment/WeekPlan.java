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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_week_plan, container, false);

        ImageView textBack= view.findViewById(R.id.textBack);


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

        getWeekPlanData();
        return view;
    }

    private void getWeekPlanData() {
//




//        Util.showPgDialog(dialog);
//
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        StringRequest strReq = new StringRequest(Request.Method.GET,
//               "http://35.184.93.23:3000/api/weekly-plan/getmonthandweekbydata/5b01bbac5965f71240c7cdef", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Util.cancelPgDialog(dialog);
//                Log.e("dfsjfdfsdsdffdffgd", "PWD Response: " + response);
//
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Util.cancelPgDialog(dialog);
//                Log.e("fdgdfgdfdfdfsdfgd", "PWD Error: " + error.getMessage());
//                //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//               // Util.errorDialog(getActivity(),"Old Password not Correct");
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Log.e("fgdfgdfgdf","Inside getParams");
//
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<>();
////                params.put("oldpassword", edit_pwdOld.getText().toString());
////                params.put("password", edit_pwdNew.getText().toString());
////
////                Log.d("dsfsdfsdfsdfs",edit_pwdOld.getText().toString());
////                Log.d("dsfsdfsdfsdfs",edit_pwdNew.getText().toString());
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> header = new HashMap<>();
//                String authToken = MyPrefrences.getToken(getActivity());
//                String bearer = "Bearer ".concat(authToken);
//                header.put("Authorization", bearer);
//                return header;
//            }
//
////                        @Override
////                        public Map<String, String> getHeaders() throws AuthFailureError {
////                            Log.e("fdgdfgdfgdfg","Inside getHeaders()");
////                            Map<String,String> headers=new HashMap<>();
////                            headers.put("Content-Type","application/x-www-form-urlencoded");
////                            return headers;
////                        }
//
//        };
//
//
//        // Adding request to request queue
//        queue.add(strReq);


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

//    public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {
//
//        ArrayList<String> alName;
//        ArrayList<Integer> alImage;
//        List<HashMap<String,String>> allProducts;
//        Context context;
//
//        public HLVAdapter(Context context, List<HashMap<String, String>> allProducts) {
//            super();
//            this.context = context;
//            this.allProducts = allProducts;
//            this.alImage = alImage;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.list_week_plan_week, viewGroup, false);
//            ViewHolder viewHolder = new ViewHolder(v);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder viewHolder, int i) {
//            //viewHolder.tvSpecies.setText(AllProducts.get(i).get("name"));
//            //viewHolder.imgThumbnail.setImageResource(alImage.get(i));
//
//            Log.d("fvsdgsdgdfg",allProducts.get(i).get("name"));
//            viewHolder.bnt_Week.setText("Week "+allProducts.get(i).get("name"));
//
////            viewHolder.setClickListener(new ItemClickListener() {
////                @Override
////                public void onClick(View view, int position, boolean isLongClick) {
////                    if (isLongClick) {
////                        Toast.makeText(context, "#" + position + " - " + alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
////                        context.startActivity(new Intent(context, MainActivity.class));
////                    } else {
////                        Toast.makeText(context, "#" + position + " - " + alName.get(position), Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return AllProducts.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//
//            public Button bnt_Week;
//            public TextView tvSpecies;
//            //private ItemClickListener clickListener;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                bnt_Week = (Button) itemView.findViewById(R.id.bnt_Week);
//            }
//
//            @Override
//            public void onClick(View view) {
//
//            }
//
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//
////            public void setClickListener(ItemClickListener itemClickListener) {
////                this.clickListener = itemClickListener;
////            }
//
////            @Override
////            public void onClick(View view) {
////                clickListener.onClick(view, getPosition(), false);
////            }
//
////            @Override
////            public boolean onLongClick(View view) {
////                clickListener.onClick(view, getPosition(), true);
////                return true;
////            }
//        }
//
//    }

}
