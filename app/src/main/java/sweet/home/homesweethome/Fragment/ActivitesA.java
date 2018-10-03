package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
public class ActivitesA extends Fragment {


    public ActivitesA() {
        // Required empty public constructor
    }

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Dialog dialog;
 //   ImageView imageNoListing;
    TextView txtNoData;

    String month2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assignments, container, false);

       // imageNoListing = (ImageView) view.findViewById(R.id.imageNoListing);

        txtNoData = (TextView) view.findViewById(R.id.txtNoData);


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        Log.d("ClassId",getArguments().getString("ClassId"));
        Log.d("studentId",getArguments().getString("studentId"));


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


        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);
        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new ActivityImage();

                Bundle bundle=new Bundle();
                bundle.putString("image","http://hshpreschooladmin.com/api/upload/"+AllProducts.get(i).get("image"));
                bundle.putString("title",AllProducts.get(i).get("title"));
                bundle.putString("description",AllProducts.get(i).get("description"));
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });



        getActivities();




        return view;
    }

    private void getActivities() {


        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.activities+getArguments().getString("ClassId")+"/"+getArguments().getString("studentId"), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response",response.toString());
                Util.cancelPgDialog(dialog);

                if (response.length()!=0) {

                    txtNoData.setVisibility(View.GONE);
                    expListView.setVisibility(View.VISIBLE);

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            HashMap<String, String> map = new HashMap<>();

                            map.put("_id", jsonObject.optString("_id"));
                            map.put("title", jsonObject.optString("title"));
                            map.put("description", jsonObject.optString("description"));
                            map.put("datefield", jsonObject.optString("datefield"));
                            map.put("image", jsonObject.optString("image"));
                            Adapter adapter = new Adapter();
                            expListView.setAdapter(adapter);
                            AllProducts.add(map);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else{
                    txtNoData.setVisibility(View.VISIBLE);
                    expListView.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
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
        TextView title,desc,date;
        NetworkImageView network;

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


            convertView=inflater.inflate(R.layout.list_activites,parent,false);
            title=convertView.findViewById(R.id.title);
            desc=convertView.findViewById(R.id.desc);
            date=convertView.findViewById(R.id.date);
            network=convertView.findViewById(R.id.network);

            title.setText(AllProducts.get(position).get("title"));
            desc.setText(AllProducts.get(position).get("description"));

            String year=AllProducts.get(position).get("datefield").toString().substring(0,4);
            String months=AllProducts.get(position).get("datefield").toString().substring(5,7);
            String dateVal=AllProducts.get(position).get("datefield").toString().substring(8,10);


            Log.d("fsdfsfsdfs",months);


            if (months.equals("01")){
                month2="January";
            }else if(months.equals("02")){
                month2="February";
            }else if(months.equals("03")){
                month2="March";
            }else if(months.equals("04")){
                month2="April";
            }else if(months.equals("05")){
                month2="May";
            } else if(months.equals("06")){
                month2="June";
            } else if(months.equals("07")){
                month2="July";
            } else if(months.equals("08")){
                month2="August";
            } else if(months.equals("09")){
                month2="September";
            } else if(months.equals("10")){
                month2="October";
            } else if(months.equals("11")){
                month2="November";
            } else if(months.equals("12")){
                month2="December";
            }

//            date.setText(AllProducts.get(position).get("datefield").substring(0, Math.min(AllProducts.get(position).get("datefield").length(), 10)));
            date.setText(month2+" "+dateVal);

            String imageUrl="http://hshpreschooladmin.com/api/upload/"+AllProducts.get(position).get("image").toString();


            if (AllProducts.get(position).get("image").toString().equals("")){
                network.setVisibility(View.GONE);
            }
            else {
                network.setVisibility(View.VISIBLE);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                network.setImageUrl(imageUrl, imageLoader);

            }





            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);
            //desc.setTypeface(tvFont);



            return convertView;
        }
    }

}
