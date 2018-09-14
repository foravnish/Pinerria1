package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
public class Monthely extends Fragment {


    public Monthely() {
        // Required empty public constructor
    }

    CalendarView calender;
    Dialog dialog,dialog4;
    MaterialCalendarView calendarView;
    JSONObject jsonObject;
    List<String> Years=new ArrayList<>();
    List<String> Months=new ArrayList<>();
    List<String> Days=new ArrayList<>();

    List<String> Years1=new ArrayList<>();
    List<String> Months1=new ArrayList<>();
    List<String> Days1=new ArrayList<>();


    List<HashMap<String,String>> AllProducts ;
    List<HashMap<String,String>> AllProducts2 ;

    Boolean flag=false;
    String FianlDate2;
    String colorItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_monthely, container, false);

        RadioButton rb  = (RadioButton)view. findViewById(R.id.radiobutton1);
        RadioButton rb2  = (RadioButton)view. findViewById(R.id.radiobutton2);
//

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        AllProducts = new ArrayList<>();
        AllProducts2 = new ArrayList<>();

//        calender=(CalendarView) view.findViewById(R.id.calender) ;
//
//        //calender.getDate(); // get selected date in milliseconds
//        calender.setSelectedDateVerticalBar(getResources().getDrawable(R.drawable.food)); // set the drawable for the vertical bar
//
//        Calendar cal = Calendar.getInstance();
//
//        cal.add(Calendar.DATE, -1);

         calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        //calendarView.setSelectionMode(SELECTION_MODE_MULTIPLE); // Removes onClick functionality




        Calendar cal = Calendar.getInstance();


//        cal.add(Calendar.DATE, -1);
//        calendarView.setDateSelected(cal.getTime(), true);
//        calendarView.setDateSelected(CalendarDay.today(), true);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
        rb.setTypeface(font);
        rb2.setTypeface(font);

        TimeLineCalander();




        return view;

    }


    private void TimeLineCalander() {

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Calender, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d("ResponseCalaMonthly",response.toString());
                Util.cancelPgDialog(dialog);

                for (int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();

                        map.put("_id", jsonObject.optString("_id"));
                        map.put("colorItem", jsonObject.optString("colorItem"));


                        AllProducts.add(map);


                        Log.d("dfsdfsdfsdf", String.valueOf(calendarView.getCurrentDate()));
                       // calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4))), Integer.parseInt(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10))), Integer.parseInt(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)))), true);
                        //calendarView.setDateSelected(CalendarDay.from(2018, 3, 20), true);

                        Log.d("year_data",jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                        Log.d("months_data",jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7)));
                        Log.d("date_data",jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)));


                        Years.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                        Months.add(jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7)));
                        Days.add(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)));




                        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                            @Override
                            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                                //Log.d("fsdfsdfsdfsdfs", String.valueOf(date));
                                Log.d("fsdfsdfsdfsdfs1", String.valueOf(date.getDay()));
                                Log.d("fsdfsdfsdfsdfs2", String.valueOf(date.getMonth()+1));
                                Log.d("fsdfsdfsdfsdfs3", String.valueOf(date.getYear()));

                                Log.d("fddsfsdfsdfgsFinal",0+date.getDay()+"-"+0+String.valueOf(date.getMonth()+1)+"-"+date.getYear());
                                String FianlDate1=0+date.getDay()+"-"+0+String.valueOf(date.getMonth()+1)+"-"+date.getYear();


                                for (int i=0;i<Years.size();i++) {

                                    Log.d("dsfsdfsdfsds", String.valueOf(Years.size()));
                                    Log.d("dsfsdfsdfgs",Days.get(i));

                                    Log.d("dsfsdfsdfsdfsdfgFinal2",Days.get(i)+"-"+Months.get(i)+"-"+Years.get(i));
                                    FianlDate2=Days.get(i)+"-"+Months.get(i)+"-"+Years.get(i);


                                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i)), Integer.parseInt(Months.get(i))-1, Integer.parseInt(Days.get(i))), true);

                                    Log.d("fdsfsdfsdfsdfsdf1",FianlDate1);
                                    Log.d("fdsfsdfsdfsdfsdf2",FianlDate2);

                                    if (FianlDate2.contains(FianlDate1)){
                                        Log.d("gdfgdfgdfhgdfhfghfgh","yes");

                                        flag=true;
                                        colorItem=AllProducts.get(i).get("colorItem");

                                        showDataCalender(FianlDate2, colorItem, response);
//                                         break;
                                    }
//                                    else{
//                                        Toast.makeText(getActivity(), "Nothing Event nor Holiday", Toast.LENGTH_SHORT).show();
//                                    }
                                }

//                                if (flag == true){
//                                    showDataCalender(FianlDate2,colorItem,response);
//                                }
                            }
                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                for (int i1=0;i1<Years.size();i1++) {

                    Log.d("dsfsdfsdfgggsds", String.valueOf(Years.size()));
                    Log.d("dsfsdfdgfsdfgs",Days.get(i1));
                    Log.d("dsfsdfdgfsdfgs",Months.get(i1));
                    Log.d("dsfsdfdgfsdfgs",Years.get(i1));

                    Log.d("dsrggfddfsdfgFinal2",Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1));
                    // String FianlDate2=Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1);

                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);



//                    if (AllProducts.get(i1).get("colorItem").equals("blue")){
//                        calendarView.setSelectionColor(Color.BLUE);
//                    }
//                    else{
//                        calendarView.setSelectionColor(Color.RED);
//                    }

                    Log.d("dfsdfsdfsdfsdfs",AllProducts.get(i1).get("colorItem"));

//                    else if (AllProducts.get(i1).get("colorItem").equalsIgnoreCase("red")){
//                        calendarView.setSelectionColor(Color.RED);
//                    }


//                    calendarView.setDateSelected(CalendarDay.from(2018, 7, 20), true);

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

//    @Override
//    public void onResume() {
//
//        for (int i1=0;i1<Years.size();i1++) {
//
//            Log.d("dsfsdfsdfgggsds", String.valueOf(Years.size()));
//            Log.d("dsfsdfdgfsdfgs",Days.get(i1));
//            Log.d("dsfsdfdgfsdfgs",Months.get(i1));
//            Log.d("dsfsdfdgfsdfgs",Years.get(i1));
//
//            Log.d("dsrggfddfsdfgFinal2",Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1));
//            // String FianlDate2=Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1);
//
//            calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);
////                    calendarView.setDateSelected(CalendarDay.from(2018, 7, 20), true);
//
//        }
//
//        super.onResume();
//
//    }

    private void showDataCalender(String date, String isWhat, JSONArray jsonArray) {
        dialog4 = new Dialog(getActivity());
        dialog4.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog4.setContentView(R.layout.calender_alert);
        dialog4.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView heading=(TextView)dialog4.findViewById(R.id.heading);
        ListView listView=(ListView)dialog4.findViewById(R.id.listView);

        Log.d("ddgdgdgsfsdf",jsonArray.toString());
        Log.d("sdfsdfsdffgfgfgfsdfs",date);


//
//        Log.d("year_data",jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
//        Log.d("months_data",jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7)));
//        Log.d("date_data",jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)));
//
//        String year1=jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4));
//        String months1=jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7));
//        String day1=jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10));
//        String dateNew=year1+"-"+months1+"-"+day1;



//        Log.d("jhkdgfhjhsjkhd",dateNew);

        try {
            JSONArray jsonArray1=new JSONArray(jsonArray.toString());

            AllProducts2.clear();
            for (int i=0;i<jsonArray1.length();i++){
                try {
                    JSONObject jsonObject=jsonArray1.getJSONObject(i);
                    Log.d("dsfsfsdfsdfs",jsonObject.optString("start"));

                    HashMap<String,String> map=new HashMap<>();

                    Years1.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                    Months1.add(jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7)));
                    Days1.add(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)));

//                    String dateNew=Years1.get(i)+"-"+Months1.get(i)+"-"+Days1.get(i);
//                    Log.d("jhkdgfhjhsjkhd",dateNew);
                    //Log.d("dsfdsfdfgfgsfsdf",Years1.get(i)+"-"+Months1.get(i)+"-"+Days1.get(i));
                    Log.d("dsfdsfdfgfgsfsdf",Days1.get(i)+"-"+Months1.get(i)+"-"+Years1.get(i));

                    if (date.contains(Days1.get(i)+"-"+Months1.get(i)+"-"+Years1.get(i))) {
                        map.put("_id", jsonObject.optString("_id"));
                        map.put("title", jsonObject.optString("title"));
                        map.put("description", jsonObject.optString("description"));
                        map.put("start", jsonObject.optString("start"));
                        map.put("end", jsonObject.optString("end"));
                        map.put("colorItem", jsonObject.optString("colorItem"));
                        Adapter adapter = new Adapter();
                        listView.setAdapter(adapter);
                        AllProducts2.add(map);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("fdsdfsdfsdfsdf",isWhat);
        if (isWhat.equals("blue")) {
            heading.setText("Event(s) on " + date);
        }
        else if (isWhat.equals("red")){
            heading.setText("Holiday(s) on " + date);
        }

        dialog4.show();

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
            return AllProducts2.size();
        }

        @Override
        public Object getItem(int position) {
            return AllProducts2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView=inflater.inflate(R.layout.list_alet_event,parent,false);
            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
           // dateM=convertView.findViewById(R.id.dateM);
            //dayM=convertView.findViewById(R.id.dayM);
            //linearColor=convertView.findViewById(R.id.linearColor);

            title.setText(AllProducts2.get(position).get("title"));
            desc.setText(AllProducts2.get(position).get("description"));
           // dateM.setText(AllProducts2.get(position).get("start").substring(0, Math.min(AllProducts2.get(position).get("start").length(), 10)));
            //dayM.setText(AllProducts.get(position).get("start"));

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);
          //  dateM.setTypeface(tvFont);
            //  dayM.setTypeface(tvFont);


//            if (AllProducts2.get(position).get("colorItem").equalsIgnoreCase("blue")){
//                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
//            }
//            else if (AllProducts2.get(position).get("colorItem").equalsIgnoreCase("red")){
//                linearColor.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
//            }

            return convertView;
        }
    }


}
