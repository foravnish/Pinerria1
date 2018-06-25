package sweet.home.pinerria1.Fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.RadioButton;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

import sweet.home.pinerria1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monthely extends Fragment {


    public Monthely() {
        // Required empty public constructor
    }

    CalendarView calender;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_monthely, container, false);

        RadioButton rb  = (RadioButton)view. findViewById(R.id.radiobutton1);
        RadioButton rb2  = (RadioButton)view. findViewById(R.id.radiobutton2);
//

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

        return view;

    }

}
