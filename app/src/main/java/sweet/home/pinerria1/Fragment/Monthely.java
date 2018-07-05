package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
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
    List<HashMap<String,String>> AllProducts ;
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
            public void onResponse(JSONArray response) {
                Log.d("ResponseCala",response.toString());
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
                                    String FianlDate2=Days.get(i)+"-"+Months.get(i)+"-"+Years.get(i);


                                    calendarView.setDateSelected(CalendarDay.from(Integer.parseInt(Years.get(i)), Integer.parseInt(Months.get(i))-1, Integer.parseInt(Days.get(i))), true);


                                    Log.d("fdsfsdfsdfsdfsdf1",FianlDate1);
                                    Log.d("fdsfsdfsdfsdfsdf2",FianlDate2);

                                    if (FianlDate2.contains(FianlDate1)){
                                        Log.d("gdfgdfgdfhgdfhfghfgh","yes");

                                        showDataCalender(FianlDate2,AllProducts.get(i).get("colorItem"));
                                        break;
                                    }
                                }
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


    private void showDataCalender(String date,String isWhat) {
        dialog4 = new Dialog(getActivity());
        dialog4.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog4.setContentView(R.layout.calender_alert);
        dialog4.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView heading=(TextView)dialog4.findViewById(R.id.heading);

        Log.d("fdsdfsdfsdfsdf",isWhat);
        if (isWhat.equals("blue")) {
            heading.setText("Event(s) on " + date);
        }
        else if (isWhat.equals("red")){
            heading.setText("Holiday(s) on " + date);
        }

        dialog4.show();

    }


}
