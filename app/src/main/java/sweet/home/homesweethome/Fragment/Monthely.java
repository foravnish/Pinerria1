package sweet.home.homesweethome.Fragment;


import android.app.Activity;
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
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.style.BackgroundColorSpan;
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
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.Event;
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

    List<String> YearsColor=new ArrayList<>();
    List<String> MonthsColor=new ArrayList<>();
    List<String> DaysColor=new ArrayList<>();

    List<String> ColorData=new ArrayList<>();

    List<String> Years1=new ArrayList<>();
    List<String> Months1=new ArrayList<>();
    List<String> Days1=new ArrayList<>();


    List<HashMap<String,String>> AllProducts ;
    List<HashMap<String,String>> AllProducts2 ;

    Boolean flag=false;
    String FianlDate2;
    String colorItem;

    HashSet<CalendarDay> setDays = new HashSet<>();
    HashSet<CalendarDay> setDays2 = new HashSet<>();
    String FianlDate1;
    int i1;
    Date date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_monthely, container, false);

        RadioButton rb  = (RadioButton)view. findViewById(R.id.radiobutton1);
        RadioButton rb2  = (RadioButton)view. findViewById(R.id.radiobutton2);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
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

        Util.showPgDialog(dialog);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Calender, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d("ResponseCalaMonthly",response.toString());
                Util.cancelPgDialog(dialog);

                for (int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(i);

                        ColorData.add(jsonObject.optString("colorItem"));

                        HashMap<String,String> map=new HashMap<>();

                        //   calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#00ff00"),setDays,"true"));

                        map.put("_id", jsonObject.optString("_id"));
                        map.put("colorItem", jsonObject.optString("colorItem"));

                        AllProducts.add(map);


//                       // SimpleDateFormatter formatter = new SimpleDateFormatter(); //TODO: update this line with the correct formatter
//                        List<Event> events = new ArrayList<>();
//                       // for (int j = 0; j < response.length(); j++) {
////                            try {
//                              //  JSONObject obj = response.getJSONObject(j);
//                                //String str2 = obj.optString("eventdate");
//                               // String str1 = obj.optString("#2413AD");
//                              //  Date date = formatter.parse(str2);
//                                int color = Color.parseColor("#228BC34A"); //TODO: update this line with the correct code to parse your color
//                                Event event = new Event(color);
//                                events.add(event);
////                            }
////                            catch (JSONException e) {
////                                e.printStackTrace();
////                            }
//                      //  }
//
//                        for (Event event1 : events) {
//                            EventDecorator eventDecorator = new EventDecorator(calendarView,  event1.getColor());
//                            calendarView.addDecorator(eventDecorator);
//                        }

                        Log.d("dfsdfsdfgfgfgfgfsdf", String.valueOf(calendarView.getCurrentDate()));
                        // calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4))), Integer.parseInt(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10))), Integer.parseInt(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)))), true);
                        //calendarView.setDateSelected(CalendarDay.from(2018, 3, 20), true);

//                        try {
//                            String source = "2013-02-19T11:20:16.393Z";
//                            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//                            Date formatted = null;
//                            formatted = formatter.parse(source);
//                            String formattedString = formatted.toString();
//                            Log.d("sdgdfgdfgdfgdf",formattedString);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }


                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                        try {
                            date = format.parse(jsonObject.optString("start").replaceAll("Z$", "+0000"));
                            System.out.println(date);
                            Log.d("sdfgdsfgdsgsdfgdf", String.valueOf(date));

                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }



                        Log.d("year_data",jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                        Log.d("months_data",String.valueOf(date.getMonth()));
                        Log.d("date_data", String.valueOf(date.getDate()));

                        ///Date set for calender
                        if (jsonObject.optString("colorItem").equals("blue")) {
                            Years.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                            Months.add(String.valueOf(date.getMonth()+1));
                            Days.add(String.valueOf(date.getDate()));
                        }
                        else if (jsonObject.optString("colorItem").equals("red")){
                            YearsColor.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                            MonthsColor.add(String.valueOf(date.getMonth()+1));
                            DaysColor.add(String.valueOf(date.getDate()));
                        }

                        // calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Years.get(i)+"-"+Months.get(i)+"-"+Days));

                        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                            @Override
                            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                                //Log.d("fsdfsdfsdfsdfs", String.valueOf(date));
                                Log.d("fsdfsdfsdfsdfs1", String.valueOf(date.getDay()));
                                Log.d("fsdfsdfsdfsdfs2", String.valueOf(date.getMonth()+1));
                                Log.d("fsdfsdfsdfsdfs3", String.valueOf(date.getYear()));

                                Log.d("fddsfsdfsdfgsFinal",0+date.getDay()+"-"+0+String.valueOf(date.getMonth()+1)+"-"+date.getYear());
//                                if (date.getDay()<=9 && date.getMonth()+1>=10 ){
//                                    FianlDate1="0"+date.getDay()+"-"+String.valueOf(date.getMonth()+1)+"-"+date.getYear();
//                                    Log.d("gdfgdfgdfgdgdg","1");
//                                }
//                                else if (date.getDay()<=9 && date.getMonth()+1<=9 ){
//                                    FianlDate1=0+date.getDay()+"-"+0+String.valueOf(date.getMonth()+1)+"-"+date.getYear();
//                                    Log.d("gdfgdfgdfgdgdg","2");
//                                }
//                                else if (date.getDay()>=10 && date.getMonth()+1<=9 ){
//                                    FianlDate1=date.getDay()+"-"+0+String.valueOf(date.getMonth()+1)+"-"+date.getYear();
//                                    Log.d("gdfgdfgdfgdgdg","13");
//                                }
//                                else if (date.getDay()>=10 && date.getMonth()+1>=10 ){
//                                    FianlDate1=date.getDay()+"-"+String.valueOf(date.getMonth()+1)+"-"+date.getYear();
//                                    Log.d("gdfgdfgdfgdgdg","4");
//                                }
                                String FianlDate1=date.getDay()+"-"+String.valueOf(date.getMonth()+1)+"-"+date.getYear();

                                Log.d("Sdvsfvdgvdfgbdfb",FianlDate1);

                                for (int i=0;i<Years.size();i++) {

                                    Log.d("dsfsdfsdfsds", String.valueOf(Years.size()));
                                    Log.d("dsfsdfsdfgs",Days.get(i));

                                    Log.d("dsfsdfsdfsdfsdfgFinal2",Days.get(i)+"-"+Months.get(i)+"-"+Years.get(i));
                                    FianlDate2=Days.get(i)+"-"+Months.get(i)+"-"+Years.get(i);


                                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i)), Integer.parseInt(Months.get(i))-1, Integer.parseInt(Days.get(i))), true);

                                    Log.d("fdsfsdfsdfsdfsdf1",FianlDate1);
                                    Log.d("fdsfsdfsdfsdfsdf2",FianlDate2);


//                                    calendarView.addDecorator(new DayViewDecorator() {
//                                        @Override
//                                        public boolean shouldDecorate(CalendarDay day) {
////                            CalendarDay eventDay = new CalendarDay(2018, 11, 04);
////                            CalendarDay eventDay = new CalendarDay(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1)+1), Integer.parseInt(Days.get(i1)));
////                            CalendarDay date= CalendarDay.today();
//                                            CalendarDay date= CalendarDay.from(Integer.parseInt(Years.get(i)), Integer.parseInt(Months.get(i))-1, Integer.parseInt(Days.get(i)));
//                                            return date != null && day.equals(date);
//                                        }
//                                        @Override
//                                        public void decorate(DayViewFacade view) {
//                                            view.addSpan(new ForegroundColorSpan(Color.BLUE));
//                                        }
//                                    });


                                    if (FianlDate2.contains(FianlDate1)){
                                        Log.d("gdfgdfgdfhgdfhfghfgh","yes");

                                        flag=true;
                                        colorItem=AllProducts.get(i).get("colorItem");

//                                        showDataCalender(FianlDate2, colorItem, response);
//                                         break;
                                    }
//                                    else{
//                                        Toast.makeText(getActivity(), "Nothing Event nor Holiday", Toast.LENGTH_SHORT).show();
//                                    }
                                }


                                if (flag == true){

                                    Log.d("Sdfgsdfgdfgdfg","true");
                                    showDataCalender(FianlDate1.toString(),colorItem,response);

                                }
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                for (i1=0;i1<Years.size();i1++) {

                    Log.d("dsfsdfsdfgggsds", String.valueOf(Years.size()));
                    Log.d("dsfsdfdgfsdfgs",Days.get(i1));
                    Log.d("dsfsdfdgfsdfgs",Months.get(i1));
                    Log.d("dsfsdfdgfsdfgs",Years.get(i1));

                    Log.d("dsrggfddfsdfgFinal2",Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1));



//                    calendarView.addDecorator(new DayViewDecorator() {
//                        @Override
//                        public boolean shouldDecorate(CalendarDay day) {
//                            return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//                                    day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ;
//                        }
//
//                        @Override
//                        public void decorate(DayViewFacade view) {
//                            view.addSpan(new ForegroundColorSpan(Color.RED));
//                        }
//                    });


                    calendarView.addDecorator(new DayViewDecorator() {
                        @Override
                        public boolean shouldDecorate(CalendarDay day) {
                            return true; //decorat
                        }
                        @Override
                        public void decorate(DayViewFacade view) {
                            view.setDaysDisabled(true); //disable all days
                        }
                    });



                    // String FianlDate2=Days.get(i1)+"-"+Months.get(i1)+"-"+Years.get(i1);

//                    if (jsonObject.optString("colorItem").equals("red")){
//                        calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#00ff00"),setDays));
//                    }
//                    else if(jsonObject.optString("colorItem").equals("blue")){
//                        calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#948ad6"),setDays));
//                    }

                    // set Default Date Selected
//                    if (colorItem.equals("blue")){
//
//                    }


//                    if (ColorData.get(i1).equals("blue")) {
//                        calendarView.setSelectionColor(Color.GREEN);
//                        calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);
//                    }
//
//                    else if (ColorData.get(i1).equals("red")){
//                        calendarView.setSelectionColor(Color.RED);
////                        calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(YearsColor.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);
//                        calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);
//
//                    }
                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);

                    setDays.add(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))));
                    //setDays2.add(CalendarDay.from(Integer.parseInt(YearsColor.get(i1)), Integer.parseInt(MonthsColor.get(i1))-1, Integer.parseInt(DaysColor.get(i1))));

                    calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#fb0000"),setDays));


                    Log.d("fsdfsdfsadfsfsdf",ColorData.get(i1));

//                    calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#fb0000"),setDays));

                    if (ColorData.get(i1).equals("red")){
//                        Log.d("dsfgdgdfgdfgdf","true");
//
//
//                        calendarView.addDecorator(new DayViewDecorator() {
//                        @Override
//                        public boolean shouldDecorate(CalendarDay day) {
//                            return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//                                    day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ;
//                        }
//
//                        @Override
//                        public void decorate(DayViewFacade view) {
//                            view.addSpan(new BackgroundColorSpan(Color.RED));
//                        }
//                    });


                    }
                    else{
                        Log.d("dsfgdgdfgdfgdf","false");

//                        calendarView.addDecorator(new DayViewDecorator() {
//                            @Override
//                            public boolean shouldDecorate(CalendarDay day) {
//                                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//                                        day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ;
//                            }
//
//                            @Override
//                            public void decorate(DayViewFacade view) {
//                                view.addSpan(new BackgroundColorSpan(Color.GREEN));
//                            }
//                        });

//                        calendarView.addDecorators(new CurrentDayDecorator2(getActivity(),Color.parseColor("#00ff00"),setDays));
                    }

//                    if (ColorData.get(i1).toString().equals("blue")){
//                       // calendarView.setSelectionColor(Color.BLUE);
//
//                        Log.d("sdfsdghghghgfsdfsdf","true");
//
//                       // calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#00ff00"),setDays,"true"));
//
////                        calendarView.addDecorators(new EventDecorator(Color.parseColor("#00ff00"),));
//                    }
//                    else  if (ColorData.get(i1).toString().equals("red")){
//                        calendarView.setSelectionColor(Color.BLUE);
//                        Log.d("sdfsdghghghgfsdfsdf","false");
//                        calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#948ad6"),setDays,"false"));
//                    }

                    Log.d("dfsdfsdfsdfsdfs",AllProducts.get(i1).get("colorItem"));

//                    else if (AllProducts.get(i1).get("colorItem").equalsIgnoreCase("red")){
//                        calendarView.setSelectionColor(Color.RED);
//                    }


//                    calendarView.setDateSelected(CalendarDay.from(2018, 7, 20), true);

                }



                for (i1=0;i1<YearsColor.size();i1++) {

                    Log.d("dsfsdfsdfgggsds", String.valueOf(YearsColor.size()));
                    Log.d("dsfsdfdgfsdfgs",DaysColor.get(i1));
                    Log.d("dsfsdfdgfsdfgs",MonthsColor.get(i1));
                    Log.d("dsfsdfdgfsdfgs",YearsColor.get(i1));

                    Log.d("dsrggfddfsdfgFinal2",DaysColor.get(i1)+"-"+MonthsColor.get(i1)+"-"+YearsColor.get(i1));


//                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))), true);
                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(YearsColor.get(i1)), Integer.parseInt(MonthsColor.get(i1))-1, Integer.parseInt(DaysColor.get(i1))), true);

//                    setDays.add(CalendarDay.from(Integer.parseInt(Years.get(i1)), Integer.parseInt(Months.get(i1))-1, Integer.parseInt(Days.get(i1))));
                    setDays2.add(CalendarDay.from(Integer.parseInt(YearsColor.get(i1)), Integer.parseInt(MonthsColor.get(i1))-1, Integer.parseInt(DaysColor.get(i1))));

//                    calendarView.addDecorators(new CurrentDayDecorator(getActivity(),Color.parseColor("#fb0000"),setDays));
                    calendarView.addDecorators(new CurrentDayDecorator2(getActivity(),Color.parseColor("#fb0000"),setDays2));

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


        try {
            JSONArray jsonArray1=new JSONArray(jsonArray.toString());

            AllProducts2.clear();
            for (int i=0;i<jsonArray1.length();i++){
                try {
                    JSONObject jsonObject=jsonArray1.getJSONObject(i);
                    Log.d("dsfsfsdfsdfs",jsonObject.optString("start"));

                    HashMap<String,String> map=new HashMap<>();


                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    try {
                       Date date2 = format.parse(jsonObject.optString("start").replaceAll("Z$", "+0000"));
                        System.out.println(date);
                        Log.d("sdfgdsfgdsgsdfgdf", String.valueOf(date));
                        Years1.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                        Months1.add(String.valueOf(date2.getMonth()+1));
                        Days1.add(String.valueOf(date2.getDate()));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }




//                    Years1.add(jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
//                    Months1.add(jsonObject.optString("start").substring(5, Math.min(jsonObject.optString("start").length(), 7)));
//                    Days1.add(jsonObject.optString("start").substring(8, Math.min(jsonObject.optString("start").length(), 10)));

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
                        dialog4.show();
                    }
//                    else{
//                        Toast.makeText(getActivity(), "No events and no Holidays here.", Toast.LENGTH_SHORT).show();
//                        break;
//                    }



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


    public class CurrentDayDecorator implements DayViewDecorator {

        private Drawable drawable;
        String DateValue;
        private final int color;
        String mode;
        private HashSet<CalendarDay> mCalendarDayCollection;
        //        CalendarDay currentDay = CalendarDay.from(new Date());
        CalendarDay currentDay = CalendarDay.from(new Date()) ;

        public CurrentDayDecorator(Activity context,int color, HashSet<CalendarDay> calendarDayCollection) {
            this.DateValue=DateValue;
            this.color = color;
            this.mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            Log.d("dfgdfgdfgssdsdsdsdf","CalendarDay{"+DateValue+"}");
            Log.d("dfgdfgddfdfdffgdf", String.valueOf(currentDay));
            Log.d("fgfhgfhfghfghgf", String.valueOf(day));
            Log.d("fdfgfhsdfgdfdfdfdhfghgf", String.valueOf(mCalendarDayCollection));

            return mCalendarDayCollection.contains(day);

        }

        @Override
        public void decorate(DayViewFacade view) {

            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.first_day_month));

        }
    }

    public class CurrentDayDecorator2 implements DayViewDecorator {

        private Drawable drawable;
        String DateValue;
        private final int color;
        String mode;
        private HashSet<CalendarDay> mCalendarDayCollection;
        //        CalendarDay currentDay = CalendarDay.from(new Date());
        CalendarDay currentDay = CalendarDay.from(new Date()) ;

        public CurrentDayDecorator2(Activity context,int color, HashSet<CalendarDay> calendarDayCollection) {
            this.DateValue=DateValue;
            this.color = color;

            this.mCalendarDayCollection = calendarDayCollection;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            Log.d("dfgdfgdfgssdsdsdsdf","CalendarDay{"+DateValue+"}");
            Log.d("dfgdfgddfdfdffgdf", String.valueOf(currentDay));
            Log.d("fgfhgfhfghfghgf", String.valueOf(day));
            Log.d("fdfgfhsdfgdfdfdfdhfghgf", String.valueOf(mCalendarDayCollection));

            return mCalendarDayCollection.contains(day);

        }

        @Override
        public void decorate(DayViewFacade view) {

            // Log.d("fsdfsfsfsfsfrwe",""+view);

            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.first_day_month2));

        }
    }


}
