package sweet.home.homesweethome.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Food2 extends Fragment {


    public Food2() {
        // Required empty public constructor
    }

    GridView expListView;
    List<HashMap<String,String>> AllProducts ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_food3, container, false);
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);

        Log.d("fsfgsdgsfgdfhg",getArguments().getString("type"));

//        try {
//            JSONArray jsonArray=new JSONArray(getArguments().getString("json"));
//            for (int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject=jsonArray.getJSONObject(i);
//                HashMap<String,String> map=new HashMap<>();
//                map.put("_id", jsonObject.optString("_id"));
//                map.put("name", jsonObject.optString("name"));
//                Adapter adapter=new Adapter();
//                expListView.setAdapter(adapter);
//                AllProducts.add(map);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        try {
            JSONObject jsonObject = new JSONObject(getArguments().getString("json"));

            JSONObject jsonObject1 = jsonObject.optJSONObject("Snacks");
            JSONArray jsonArray = jsonObject1.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                HashMap<String, String> map = new HashMap<>();
                map.put("_id", jsonObject3.optString("_id"));
                map.put("name", jsonObject3.optString("name"));
                Adapter adapter = new Adapter();
                expListView.setAdapter(adapter);
                AllProducts.add(map);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String,String > map2=new HashMap<>();
        map2.put("name","∧");
        AllProducts.add(map2);

        ImageView textBack= view.findViewById(R.id.textBack);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Food();
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new Food();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });



        return view;
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


            convertView=inflater.inflate(R.layout.list_food_break,parent,false);

            title=convertView.findViewById(R.id.title);
            title.setText(AllProducts.get(position).get("name"));

            if (position%3==0){
                title.setBackgroundColor(Color.parseColor("#fdb6c2"));
            }
            else if (position%3==1){
                title.setBackgroundColor(Color.parseColor("#fdc2cc"));
            }
            else if (position%3==2){
                title.setBackgroundColor(Color.parseColor("#fdcdd6"));
            }

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);


            return convertView;
        }
    }



}
