package sweet.home.homesweethome.Fragment;


import android.annotation.TargetApi;
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
import android.text.format.DateFormat;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import sweet.home.homesweethome.Activity.MainActivitie;
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
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
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
        TextView title,desc,dateM,dayM,dateBox;
        LinearLayout linearColor,linear2;
        Date date;
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

        @TargetApi(Build.VERSION_CODES.O)
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=inflater.inflate(R.layout.list_timeline,parent,false);
            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
            dateM=convertView.findViewById(R.id.dateM);
            dayM=convertView.findViewById(R.id.dayM);
            linearColor=convertView.findViewById(R.id.linearColor);
            linear2=convertView.findViewById(R.id.linear2);
            dateBox=convertView.findViewById(R.id.dateBox);

            title.setText(AllProducts.get(position).get("title"));
            desc.setText(AllProducts.get(position).get("description"));

            String year=AllProducts.get(position).get("start").substring(0,4);
            String months=AllProducts.get(position).get("start").substring(5,7);
            String mDate=AllProducts.get(position).get("start").substring(8,10);


//            String ourdate;
//            String serverdateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//            try {
//                SimpleDateFormat formatter = new SimpleDateFormat(serverdateFormat, Locale.UK);
//                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//                Date value = formatter.parse(AllProducts.get(position).get("start"));
//                TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
//                SimpleDateFormat dateFormatter = new SimpleDateFormat(serverdateFormat, Locale.UK); //this format changeable
//                dateFormatter.setTimeZone(timeZone);
//                ourdate = dateFormatter.format(value);
//
//
//                //Log.d("OurDate", OurDate);
//            } catch (Exception e) {
//                ourdate = "0000-00-00 00:00:00";
//            }
//            Log.d("sdfgdsfgdsgsdfgdf",ourdate);


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            try {
                 date = format.parse(AllProducts.get(position).get("start").replaceAll("Z$", "+0000"));
                System.out.println(date);
                Log.d("sdfgdsfgdsgsdfgdf", String.valueOf(date));

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


//            dateM.setText(mDate+"-"+months+"-"+year);
//            dateM.setText(mDate+"");
            dateM.setText(date.getDate()+"");



            SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date2 = null;
            try {
                date2 = inFormat.parse(AllProducts.get(position).get("start"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat outFormat = new SimpleDateFormat("E");
            String goal = outFormat.format(date);
            Log.d("sdgfdgsdgdf",goal);
            dayM.setText(goal);

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);
            dateM.setTypeface(tvFont);
            dayM.setTypeface(tvFont);
            desc.setTypeface(tvFont);
            dateBox.setTypeface(tvFont);


            if (AllProducts.get(position).get("colorItem").equalsIgnoreCase("blue")){
//                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF26D1E9")));
            }
            else if (AllProducts.get(position).get("colorItem").equalsIgnoreCase("red")){
                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFBCDD5")));
            }

//            Calendar c = Calendar.getInstance();
//            c.setTime(AllProducts.get(position).get("start").toString());
//            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//
//            Date date=
//            String dayOfTheWeek = (String) DateFormat.format("EEEE", AllProducts.get(position).get("start"));
//            Log.d("dfgdfgdfgdfg",dayOfTheWeek);




            String month=AllProducts.get(position).get("start").substring(5,7);

            Calendar c = Calendar.getInstance();
            int monthCurrent = c.get(Calendar.MONTH);

            Log.d("SDfsdfsdfsdfsdfsdf1", String.valueOf(monthCurrent+1));
            Log.d("SDfsdfsdfsdfsdfsdf2", String.valueOf(month));
            String monthsC2;
            String monthsC= String.valueOf(monthCurrent+1);
            if ((monthCurrent+1)>9){
                monthsC2 =monthsC;
            }
            else {
                monthsC2 = "0" + monthsC;
            }
            Log.d("sgfdsgdgdf",monthsC2);
            if (!monthsC2.equals(month)){
                Log.d("sdfadfsfseff","true");
                linear2.setBackgroundResource(R.drawable.strock_noti_gray2);
            }


            String year2= null;
            String month2= null;
            try {
                year2 = AllProducts.get(position-1).get("start").substring(0,4);
                month2 = AllProducts.get(position-1).get("start").substring(5,7);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (year.equals(year2)&& month.equals(month2)){

                dateBox.setVisibility(View.GONE);
                dateBox.setText("");

            }
            else {
                dateBox.setVisibility(View.VISIBLE);


                if (month.equals("01")){
                    month2="January";
                }else if(month.equals("02")){
                    month2="February";
                }else if(month.equals("03")){
                    month2="March";
                }else if(month.equals("04")){
                    month2="April";
                }else if(month.equals("05")){
                    month2="May";
                } else if(month.equals("06")){
                    month2="June";
                } else if(month.equals("07")){
                    month2="July";
                } else if(month.equals("08")){
                    month2="August";
                } else if(month.equals("09")){
                    month2="September";
                } else if(month.equals("10")){
                    month2="October";
                } else if(month.equals("11")){
                    month2="November";
                } else if(month.equals("12")){
                    month2="December";
                }

                String date=month2+" "+year;
                dateBox.setText(date);


            }
            return convertView;
        }
    }

}
