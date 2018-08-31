package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.Const;
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
    ViewPager viewPager;
    GridView lvExp;
    CustomPagerAdapter mCustomPagerAdapter;
    List<Const> AllEvents=new ArrayList<>();
    int currPos=0;
    JSONObject jsonObject1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_week_plan, container, false);

        ImageView textBack= view.findViewById(R.id.textBack);


        viewPager = (ViewPager) view.findViewById(R.id.slider);
//        leftarrow=(TextView)view.findViewById(R.id.leftarrow);
//        rightarrow=(TextView)view.findViewById(R.id.rightarrow);
//        date=(TextView)view.findViewById(R.id.date);

        mCustomPagerAdapter=new CustomPagerAdapter(getActivity());
        AllProducts=new ArrayList<>();
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
//
//        TextView bnt_Week1= view.findViewById(R.id.bnt_Week1);
//        TextView bnt_Week2= view.findViewById(R.id.bnt_Week2);
//        TextView bnt_Week3= view.findViewById(R.id.bnt_Week3);
//        TextView bnt_Week4= view.findViewById(R.id.bnt_Week4);
//        TextView bnt_Week5= view.findViewById(R.id.bnt_Week5);
//        lvExp= view.findViewById(R.id.lvExp);


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


//        AllEvents.add(new Const(jsonObject1.optString("name"),"2","3","4","5"));
//        viewPager.setAdapter(mCustomPagerAdapter);
//        mCustomPagerAdapter.notifyDataSetChanged();

        getWeekPlanData();



        //Log.d("sdfsdfsdfgsdfs",jsonObject1.toString());







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

                            Iterator<String> iter = response.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                try {
                                    Object value = response.get(key);

                                    Log.d("dgdfgdfhdfhg",value.toString());

                                    JSONObject jsonObject=new JSONObject(value.toString());
                                    if (jsonObject.has("1")){
                                       JSONObject jsonObject1=new JSONObject(jsonObject.optString("1"));
                                       Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject1.toString());
                                        AllEvents.add(new Const(jsonObject1.optString("name"),jsonObject1.optString("weeks"),"","",""));
                                    }
                                    if (jsonObject.has("2")){
                                        JSONObject jsonObject2=new JSONObject(jsonObject.optString("2"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject2.toString());
                                        AllEvents.add(new Const(jsonObject2.optString("name"),jsonObject2.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("3")){
                                        JSONObject jsonObject3=new JSONObject(jsonObject.optString("3"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject3.toString());
                                        AllEvents.add(new Const(jsonObject3.optString("name"),jsonObject3.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("4")){
                                        JSONObject jsonObject4=new JSONObject(jsonObject.optString("4"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject4.toString());
                                        AllEvents.add(new Const(jsonObject4.optString("name"),jsonObject4.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("5")){
                                        JSONObject jsonObject5=new JSONObject(jsonObject.optString("5"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject5.toString());
                                        AllEvents.add(new Const(jsonObject5.optString("name"),jsonObject5.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("6")){
                                        JSONObject jsonObject6=new JSONObject(jsonObject.optString("6"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject6.toString());
                                        AllEvents.add(new Const(jsonObject6.optString("name"),jsonObject6.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("7")){
                                        JSONObject jsonObject7=new JSONObject(jsonObject.optString("7"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject7.toString());
                                        AllEvents.add(new Const(jsonObject7.optString("name"),jsonObject7.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("8")){
                                        JSONObject jsonObject8=new JSONObject(jsonObject.optString("8"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject8.toString());
                                        AllEvents.add(new Const(jsonObject8.optString("name"),jsonObject8.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("9")){
                                        JSONObject jsonObject9=new JSONObject(jsonObject.optString("9"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject9.toString());
                                        AllEvents.add(new Const(jsonObject9.optString("name"),jsonObject9.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("10")){
                                        JSONObject jsonObject10=new JSONObject(jsonObject.optString("10"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject10.toString());
                                        AllEvents.add(new Const(jsonObject10.optString("name"),jsonObject10.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("11")){
                                        JSONObject jsonObject11=new JSONObject(jsonObject.optString("11"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject11.toString());
                                        AllEvents.add(new Const(jsonObject11.optString("name"),jsonObject11.optString("weeks"),"","",""));

                                    }
                                    if (jsonObject.has("12")){
                                        JSONObject jsonObject12=new JSONObject(jsonObject.optString("12"));
                                        Log.d("Sdfsdfsertrdgdgddfsdf",jsonObject12.toString());
                                        AllEvents.add(new Const(jsonObject12.optString("name"),jsonObject12.optString("weeks"),"","",""));

                                    }

                                    viewPager.setAdapter(mCustomPagerAdapter);
                                    mCustomPagerAdapter.notifyDataSetChanged();

                                    Log.d("sdvdsvxdvxdvsd",AllEvents.get(0).getId().toString());


                                } catch (JSONException e) {
                                    // Something went wrong!
                                }
                            }


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







    public class  ViewHolder{
        GridView expListView;
        LinearLayout layoutToAdd;
        LinearLayout mainLayout;
        LinearLayout liner1,liner2,liner3;

        TextView br1,br2;
        TextView br3,br4;
        TextView br5,br6;
        TextView date,leftarrow,rightarrow,txtNoData;
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
            View itemView = mLayoutInflater.inflate(R.layout.page_weely_plan, container, false);

            viewHolder=new ViewHolder();
            viewHolder.date=itemView.findViewById(R.id.date);
            viewHolder.leftarrow=itemView.findViewById(R.id.leftarrow);
            viewHolder.rightarrow=itemView.findViewById(R.id.rightarrow);

            viewHolder.expListView=itemView.findViewById(R.id.lvExp);
            viewHolder.txtNoData=itemView.findViewById(R.id.txtNoData);

            TextView bnt_Week1= itemView.findViewById(R.id.bnt_Week1);
            TextView bnt_Week2= itemView.findViewById(R.id.bnt_Week2);
            TextView bnt_Week3= itemView.findViewById(R.id.bnt_Week3);
            TextView bnt_Week4= itemView.findViewById(R.id.bnt_Week4);
            TextView bnt_Week5= itemView.findViewById(R.id.bnt_Week5);

            viewHolder.date.setText(AllEvents.get(position).getId().toString());


            try {
                JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                JSONObject jsonObject2=new JSONObject(jsonObject.optString("1"));
                JSONArray jsonArray=jsonObject2.getJSONArray("data");

                if (jsonArray!=null) {
                    viewHolder.txtNoData.setVisibility(View.GONE);
                    viewHolder.expListView.setVisibility(View.VISIBLE);
                    AllProducts.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject12 = jsonArray.optJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("title", jsonObject12.optString("title"));
                        map.put("description", jsonObject12.optString("description"));
                        map.put("image", jsonObject12.optString("image"));

                        Adapter adapter = new Adapter("1");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }
                }
                else{
                    viewHolder.txtNoData.setVisibility(View.VISIBLE);
                    viewHolder.expListView.setVisibility(View.GONE);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            bnt_Week1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                    JSONObject jsonObject2=new JSONObject(jsonObject.optString("1"));
                    JSONArray jsonArray=jsonObject2.getJSONArray("data");

                    AllProducts.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject  jsonObject12=jsonArray.optJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("title",jsonObject12.optString("title"));
                        map.put("description",jsonObject12.optString("description"));
                        map.put("image",jsonObject12.optString("image"));
                        Adapter adapter=new Adapter("1");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        bnt_Week2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                    JSONObject jsonObject2=new JSONObject(jsonObject.optString("2"));
                    JSONArray jsonArray=jsonObject2.getJSONArray("data");

                    AllProducts.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject  jsonObject12=jsonArray.optJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("title",jsonObject12.optString("title"));
                        map.put("description",jsonObject12.optString("description"));
                        map.put("image",jsonObject12.optString("image"));
                        Adapter adapter=new Adapter("2");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        bnt_Week3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                    JSONObject jsonObject2=new JSONObject(jsonObject.optString("3"));
                    JSONArray jsonArray=jsonObject2.getJSONArray("data");

                    AllProducts.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject  jsonObject12=jsonArray.optJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("title",jsonObject12.optString("title"));
                        map.put("description",jsonObject12.optString("description"));
                        map.put("image",jsonObject12.optString("image"));
                        Adapter adapter=new Adapter("3");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        bnt_Week4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                    JSONObject jsonObject2=new JSONObject(jsonObject.optString("4"));
                    JSONArray jsonArray=jsonObject2.getJSONArray("data");

                    AllProducts.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject  jsonObject12=jsonArray.optJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("title",jsonObject12.optString("title"));
                        map.put("description",jsonObject12.optString("description"));
                        map.put("image",jsonObject12.optString("image"));
                        Adapter adapter=new Adapter("4");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        bnt_Week5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject=new JSONObject(AllEvents.get(position).getCatid().toString());
                    JSONObject jsonObject2=new JSONObject(jsonObject.optString("5"));
                    JSONArray jsonArray=jsonObject2.getJSONArray("data");

                    AllProducts.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject  jsonObject12=jsonArray.optJSONObject(i);
                        HashMap<String,String> map=new HashMap<>();
                        map.put("title",jsonObject12.optString("title"));
                        map.put("description",jsonObject12.optString("description"));
                        map.put("image",jsonObject12.optString("image"));
                        Adapter adapter=new Adapter("5");
                        viewHolder.expListView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



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



            Log.d("Sdgdsfgdfgdfgdfg",AllEvents.get(position).getCatid().toString());


            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }


        class Adapter extends BaseAdapter {

            LayoutInflater inflater;
            TextView title,desc;
            NetworkImageView networkImg;
            RelativeLayout relForColor;
            String colorSet;

            Adapter(String colorSet) {
                inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                this.colorSet=colorSet;
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
                desc=convertView.findViewById(R.id.desc);
                networkImg=convertView.findViewById(R.id.networkImg);
                relForColor=convertView.findViewById(R.id.relForColor);

                title.setText(AllProducts.get(position).get("title").toString());
                desc.setText(AllProducts.get(position).get("description").toString());

                String imageUrl="http://35.184.93.23:3000/api/upload/"+AllProducts.get(position).get("image").toString();

                Log.d("dsfdsfdfgdgdfgdfgdfg",AllProducts.get(position).get("image").toString());

                if (AllProducts.get(position).get("image").toString().equals("")){
                    networkImg.setVisibility(View.GONE);
                }
                else {
                    networkImg.setVisibility(View.VISIBLE);
                    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                    networkImg.setImageUrl(imageUrl, imageLoader);
                }

                final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
                title.setTypeface(tvFont);
//                desc.setTypeface(tvFont);

                if (colorSet.equals("1")){
                   //Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                    relForColor.setBackgroundResource(R.drawable.strock_week_p1);
                }
                else if (colorSet.equals("2")){
                    relForColor.setBackgroundResource(R.drawable.strock_week_p2);
                   // Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                }
                else if (colorSet.equals("3")){
                    relForColor.setBackgroundResource(R.drawable.strock_week_p3);
                   // Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                }
                else if (colorSet.equals("4")){
                    relForColor.setBackgroundResource(R.drawable.strock_week_p4);
                    //Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                }
                else if (colorSet.equals("5")){
                    relForColor.setBackgroundResource(R.drawable.strock_week_p5);
                   // Toast.makeText(getActivity(), "5", Toast.LENGTH_SHORT).show();
                }

                return convertView;
            }
        }

    }




}
