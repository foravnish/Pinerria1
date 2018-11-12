package sweet.home.homesweethome;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> implements AdapterView.OnClickListener
{
    private ArrayList<HashMap<String,String>> products_arrayList;
    private LayoutInflater layoutInflater;
    Context context;
    RecyclerView recyclerView;

    public ProductsAdapter(Context context, ArrayList<HashMap<String,String>> products_arrayList, RecyclerView recyclerView)
    {
        /*
         * RecyclerViewAdapter Constructor to Initialize Data which we get from RecyclerViewFragment
         **/

        layoutInflater = LayoutInflater.from(context);
        this.products_arrayList = products_arrayList;
        this.context=context;
        this.recyclerView=recyclerView;
        recyclerView.setOnClickListener(this);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        /*
         * LayoutInflater is used to Inflate the view
         * from fragment_listview_adapter
         * for showing data in RecyclerView
         **/

        View view = layoutInflater.inflate(R.layout.list_notifiction, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.MyViewHolder holder, int position)
    {


        Log.e("dfgdgdgdf", String.valueOf(products_arrayList));


        holder.title.setText(products_arrayList.get(position).get("title"));
        holder.desc.setText(products_arrayList.get(position).get("description"));
        holder.byUser.setText(products_arrayList.get(position).get("createdByRole"));
        holder.type.setText(products_arrayList.get(position).get("type"));

        String year1=products_arrayList.get(position).get("createdOn").substring(0,4);
        String month1=products_arrayList.get(position).get("createdOn").substring(5,7);
        String date1=products_arrayList.get(position).get("createdOn").substring(8,10);

        holder.dateSet.setText(date1+"-"+month1+"-"+year1);



        String year=products_arrayList.get(position).get("createdOn").substring(0,4);
        String month=products_arrayList.get(position).get("createdOn").substring(5,7);

        String year2= null;
        String month2= null;
        try {
            year2 = products_arrayList.get(position-1).get("createdOn").substring(0,4);
            month2 = products_arrayList.get(position-1).get("createdOn").substring(5,7);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
            holder.linear2.setBackgroundResource(R.drawable.strock_noti_gray);
        }



        if (year.equals(year2)&& month.equals(month2)){

            holder.dateBox.setVisibility(View.GONE);
            holder.dateBox.setText("");

        }
        else {
            holder.dateBox.setVisibility(View.VISIBLE);



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
            holder.dateBox.setText(date);


        }

        if (products_arrayList.get(position).get("type").equalsIgnoreCase("News")){
            holder.type.setTextColor(Color.parseColor("#FFC0CB"));
            holder.title.setTextColor(Color.parseColor("#FFC0CB"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Calendar")){
            holder.type.setTextColor(Color.parseColor("#0000FF"));
            holder.title.setTextColor(Color.parseColor("#0000FF"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Activity")){
            holder.type.setTextColor(Color.parseColor("#f44194"));
            holder.title.setTextColor(Color.parseColor("#f44194"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Announcement")){
            holder.type.setTextColor(Color.parseColor("#800080"));
            holder.title.setTextColor(Color.parseColor("#800080"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Assessment")){
            holder.type.setTextColor(Color.parseColor("#f441f1"));
            holder.title.setTextColor(Color.parseColor("#f441f1"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Attendance")){
            holder. type.setTextColor(Color.parseColor("#4286f4"));
            holder.title.setTextColor(Color.parseColor("#4286f4"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("CheckIn")){
            holder.type.setTextColor(Color.parseColor("#008000"));
            holder.title.setTextColor(Color.parseColor("#008000"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("CheckOut")){
            holder.type.setTextColor(Color.parseColor("#FF0000"));
            holder. title.setTextColor(Color.parseColor("#FF0000"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Feedback")){
            holder.type.setTextColor(Color.parseColor("#f1f441"));
            holder.title.setTextColor(Color.parseColor("#f1f441"));
        }
        else if (products_arrayList.get(position).get("type").equalsIgnoreCase("Remark")){
            holder.type.setTextColor(Color.parseColor("#f49741"));
            holder.title.setTextColor(Color.parseColor("#f49741"));
        }

        final Typeface tvFont = Typeface.createFromAsset(context.getAssets(), "comicz.ttf");
        final Typeface tvFont2 = Typeface.createFromAsset(context.getAssets(), "listfont.ttf");

        holder.title.setTypeface(tvFont2);
        holder. type.setTypeface(tvFont);
        holder. desc.setTypeface(tvFont2);
        holder. byUser.setTypeface(tvFont2);
        holder. dateBox.setTypeface(tvFont);





    }

    @Override
    public int getItemCount()
    {
        /*
         * getItemCount is used to get the size of respective worldpopulation_pojoArrayList ArrayList
         **/

        return products_arrayList.size();
    }

    @Override
    public void onClick(View view) {
        Log.d("fsdfsdfsdgfsd", "dfgdfg");
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView name,price,newprice,off;
//        Button button1;
//        NetworkImageView imageView;

        LayoutInflater inflater;
        TextView title, desc, byUser, type, dateBox, dateSet;
        LinearLayout linear2;
        /**
         * MyViewHolder is used to Initializing the view.
         **/

        MyViewHolder(View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            byUser = itemView.findViewById(R.id.byUser);
            type = itemView.findViewById(R.id.type);
            dateBox = itemView.findViewById(R.id.dateBox);
            dateSet = itemView.findViewById(R.id.dateSet);
            linear2=itemView.findViewById(R.id.linear2);

            itemView.setTag(itemView);


        }
    }
}
