package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.Const;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.SingletonRequestQueue;
import sweet.home.pinerria1.Utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class Food extends Fragment {


    public Food() {
        // Required empty public constructor
    }

    int currPos=0;
    public static String poso,pos1;


    List<HashMap<String,String>> AllProducts ;
    ViewPager viewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    List<Const> AllEvents=new ArrayList<>();
   // TextView rightarrow,leftarrow,date;
    Dialog dialog;
    JSONObject jsonArrayBr,jsonSnacks,jsonLunch;

    List<HashMap<String,String>> MonBr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_food, container, false);


        viewPager = (ViewPager) view.findViewById(R.id.slider);
//        leftarrow=(TextView)view.findViewById(R.id.leftarrow);
//        rightarrow=(TextView)view.findViewById(R.id.rightarrow);
//        date=(TextView)view.findViewById(R.id.date);

        mCustomPagerAdapter=new CustomPagerAdapter(getActivity());

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
      //  Util.showPgDialog(dialog);

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

//        leftarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currPos=viewPager.getCurrentItem();
//                viewPager.setCurrentItem(currPos-1);
//            }
//        });
//
//        rightarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currPos=viewPager.getCurrentItem();
//                viewPager.setCurrentItem(currPos+1);
//            }
//        });
//
//        if (currPos==1){
//            date.setText("Monday");
//        }

        MonBr=new ArrayList<>();

        getFoodData();


        return  view;
    }

    private void getFoodData() {



        Util.showPgDialog(dialog);
        JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.food,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Util.cancelPgDialog(dialog);

                        AllEvents.clear();
                        Log.d("gdfgdfghdfhdhgf",response.toString());
                        try {
                            JSONObject jsonObjectSunday=response.getJSONObject("Sunday");
                            JSONObject jsonObjectMon=response.getJSONObject("Monday");
                            JSONObject jsonObjectTue=response.getJSONObject("Tuesday");
                            JSONObject jsonObjectWed=response.getJSONObject("Wednesday");
                            JSONObject jsonObjectThu=response.getJSONObject("Thursday");

//                             jsonArrayBr=jsonObjectSunday.getJSONObject("Breakfast");
//                             jsonSnacks=jsonObjectSunday.getJSONObject("Snacks");
//                             jsonLunch=jsonObjectSunday.getJSONObject("Lunch");


                            for (int i=0;i<5;i++) {

                                if (i==0) {
                                    AllEvents.add(new Const(jsonObjectSunday.toString(), null, null, null, null));
                                }
                                else if (i==1) {
                                    AllEvents.add(new Const(jsonObjectMon.toString(), null, null, null, null));
                                }
                                else if (i==2) {
                                    AllEvents.add(new Const(jsonObjectTue.toString(), null, null, null, null));
                                }
                                else if (i==3){
                                    AllEvents.add(new Const(jsonObjectWed.toString(), null, null, null, null));
                                }
                                else if (i==4) {
                                    AllEvents.add(new Const(jsonObjectThu.toString(), null, null, null, null));
                                }

//                                AllEvents.add(new Const(jsonArrayBr.toString(),"2","3","4","5"));

                                viewPager.setAdapter(mCustomPagerAdapter);

                                mCustomPagerAdapter.notifyDataSetChanged();

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Util.cancelPgDialog(dialog);
                Log.d("gdfgdfghdfhdhgf",volleyError.toString());
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
        //System.out.print("called twice");
//                    SingletonRequestQueue.getInstance(getActivity()).getRequestQueue().add(parentMeRequest);
        AppController.getInstance().addToRequestQueue(parentMeRequest);



    }


    public class  ViewHolder{
        GridView expListView;
        LinearLayout layoutToAdd;
        LinearLayout mainLayout;
        LinearLayout liner1,liner2,liner3;

        TextView br1,br2;
        TextView br3,br4;
        TextView br5,br6;
        TextView date,leftarrow,rightarrow;
    }
    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        View view2;
        ViewHolder viewHolder;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return AllEvents.size();
        }



        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = mLayoutInflater.inflate(R.layout.page_item, container, false);

            viewHolder=new ViewHolder();
            viewHolder.liner1=itemView.findViewById(R.id.liner1);
            viewHolder.liner2=itemView.findViewById(R.id.liner2);
            viewHolder.liner3=itemView.findViewById(R.id.liner3);

            viewHolder.br1=itemView.findViewById(R.id.br1);
            viewHolder.br2=itemView.findViewById(R.id.br2);

            viewHolder.br3=itemView.findViewById(R.id.br3);
            viewHolder.br4=itemView.findViewById(R.id.br4);

            viewHolder.br5=itemView.findViewById(R.id.br5);
            viewHolder.br6=itemView.findViewById(R.id.br6);

            viewHolder.date=itemView.findViewById(R.id.date);
            viewHolder.leftarrow=itemView.findViewById(R.id.leftarrow);
            viewHolder.rightarrow=itemView.findViewById(R.id.rightarrow);


            viewHolder.mainLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);
            viewHolder.layoutToAdd = (LinearLayout) itemView.findViewById(R.id.existedlayout);


            Log.d("dfsdfdsdsgdfgdf",AllEvents.get(position).getId().toString());

                try {
                    JSONObject jsonObject = new JSONObject(AllEvents.get(position).getId().toString());

                    JSONObject jsonObject1 = jsonObject.optJSONObject("Breakfast");
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");


                        JSONObject jsonObject31 = jsonArray.getJSONObject(0);
                        viewHolder.br1.setText(jsonObject31.optString("name"));

                        JSONObject jsonObject32 = jsonArray.getJSONObject(1);
                        viewHolder.br2.setText(jsonObject32.optString("name"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject jsonObject = new JSONObject(AllEvents.get(position).getId().toString());

                    JSONObject jsonObject1 = jsonObject.optJSONObject("Snacks");
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");


                    JSONObject jsonObject31 = jsonArray.getJSONObject(0);
                    viewHolder.br3.setText(jsonObject31.optString("name"));
                    JSONObject jsonObject32 = jsonArray.getJSONObject(1);
                    viewHolder.br4.setText(jsonObject32.optString("name"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject jsonObject = new JSONObject(AllEvents.get(position).getId().toString());

                    JSONObject jsonObject1 = jsonObject.optJSONObject("Lunch");
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");

                    JSONObject jsonObject31 = jsonArray.getJSONObject(0);
                    viewHolder.br5.setText(jsonObject31.optString("name"));
                    JSONObject jsonObject32 = jsonArray.getJSONObject(1);
                    viewHolder.br6.setText(jsonObject32.optString("name"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            viewHolder.leftarrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currPos=viewPager.getCurrentItem();
                        viewPager.setCurrentItem(currPos-1);
                    }
                });

            viewHolder.rightarrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currPos=viewPager.getCurrentItem();
                        viewPager.setCurrentItem(currPos+1);
                    }
                });

                if (currPos==1){
                    viewHolder.date.setText("Monday");
                }


            Log.d("dfdsgvdgdfgdfg",AllEvents.get(0).toString());
                Log.d("dfsfsdfsdfsd", String.valueOf(position));
            if (position==0) {
                viewHolder.date.setText("Sunday");
            }
            else if (position==1){

                viewHolder.date.setText("Monday");
            }
            else if (position==2){
                viewHolder.date.setText("Tuesday");

            }
            else if (position==3){
                viewHolder.date.setText("Wednesday");

            }
            else if (position==4){
                viewHolder.date.setText("Thursday");
//                date.setText("Sunday");
            }

            viewHolder.liner1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new Food1();
                    Bundle bundle=new Bundle();
                    bundle.putString("type","breakfast");
                    bundle.putString("json",AllEvents.get(position).getId().toString());
                    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    fragment.setArguments(bundle);
                    ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                }
            });

            viewHolder.liner2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new Food2();
                    Bundle bundle=new Bundle();
                    bundle.putString("type","snack");
                    bundle.putString("json",AllEvents.get(position).getId().toString());
                    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    fragment.setArguments(bundle);
                    ft.replace(R.id.container, fragment).addToBackStack(null).commit();
                }
            });


            viewHolder.liner3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new Food3();
                    Bundle bundle=new Bundle();
                    bundle.putString("type","lunch");
                    bundle.putString("json",AllEvents.get(position).getId().toString());
                    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    fragment.setArguments(bundle);
                    ft.replace(R.id.container, fragment).addToBackStack(null).commit();


                }
            });


            Log.d("dsfdsfsdfsdfsd", String.valueOf(AllEvents.size()));




            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }


}
