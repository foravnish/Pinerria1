package sweet.home.pinerria1.Fragment;


import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.RadioButton;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
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
    Dialog dialog;

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

//        calender=(CalendarView) view.findViewById(R.id.calender) ;
//
//        //calender.getDate(); // get selected date in milliseconds
//        calender.setSelectedDateVerticalBar(getResources().getDrawable(R.drawable.food)); // set the drawable for the vertical bar
//
//        Calendar cal = Calendar.getInstance();
//
//        cal.add(Calendar.DATE, -1);

        MaterialCalendarView calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        //calendarView.setSelectionMode(SELECTION_MODE_MULTIPLE); // Removes onClick functionality

        Calendar cal = Calendar.getInstance();


//        cal.add(Calendar.DATE, -1);
//        calendarView.setDateSelected(cal.getTime(), true);
//        calendarView.setDateSelected(CalendarDay.today(), true);
        calendarView.setDateSelected(CalendarDay.from(2018, 3, 19), true);
        calendarView.setDateSelected(CalendarDay.from(2018, 3, 20), true);


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
                        JSONObject jsonObject=response.getJSONObject(i);

                        Log.d("year_data",jsonObject.optString("start").substring(0, Math.min(jsonObject.optString("start").length(), 4)));
                        Log.d("months_data",jsonObject.optString("start").substring(4, Math.min(jsonObject.optString("start").length(), 7)));
                        Log.d("date_data",jsonObject.optString("start").substring(7, Math.min(jsonObject.optString("start").length(), 10)));

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



}
