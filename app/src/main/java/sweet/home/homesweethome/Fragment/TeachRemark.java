package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
public class TeachRemark extends Fragment {


    public TeachRemark() {
        // Required empty public constructor
    }
    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_teach_remark, container, false);
//        ImageView textBack= view.findViewById(R.id.textBack);
//        textBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new Profile();
//                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
//                FragmentTransaction ft = manager.beginTransaction();
//                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
//            }
//        });


        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);

        Log.d("StudentId",getArguments().getString("sId"));


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ReamrkofStudent();

        return view;
    }

    public static Fragment NewInstance(String typeforListing) {
        Bundle args = new Bundle();
        args.putString("sId", typeforListing);

        TeachRemark fragment = new TeachRemark();
        fragment.setArguments(args);

        return fragment;
    }

    private void ReamrkofStudent() {
        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Remark+getArguments().getString("sId"), new Response.Listener<JSONArray>() {
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Remark+"5b180e59b1fbed41daa94c2c", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseRemark",response.toString());
                Util.cancelPgDialog(dialog);


                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();


//                        if (jsonObject.optString("isremarks").equalsIgnoreCase("true")) {
                            map.put("_id", jsonObject.optString("_id"));
                            map.put("remark", jsonObject.optString("remark"));
                            map.put("emojiIcon", jsonObject.optString("emojiIcon"));
                            map.put("classId", jsonObject.optString("classId"));
                            map.put("createdBy", jsonObject.optString("createdBy"));
                            map.put("__v", jsonObject.optString("__v"));
                            map.put("modifiedOn", jsonObject.optString("modifiedOn"));
                            map.put("createdOn", jsonObject.optString("createdOn"));
                            map.put("isClassLevel", jsonObject.optString("isClassLevel"));
                            map.put("isremarks", jsonObject.optString("isremarks"));
                            Adapter adapter = new Adapter();
                            expListView.setAdapter(adapter);
                            AllProducts.add(map);
//                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorMGS",error.toString());
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
        TextView title1,remarkValue1,dateBox;
        ImageView iv11;
        RelativeLayout relative;

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


            convertView=inflater.inflate(R.layout.list_remark,parent,false);
            title1=convertView.findViewById(R.id.title1);
            remarkValue1=convertView.findViewById(R.id.remarkValue1);
            iv11=convertView.findViewById(R.id.iv11);
            relative=convertView.findViewById(R.id.relative);
            dateBox=convertView.findViewById(R.id.dateBox);

            Log.d("dsfsdfgsdfgsdgdfg",AllProducts.get(position).get("isremarks"));
           // Log.d("dsfsdfgsdfgsdgdfg2",AllProducts.get(position).get("remark"));


            String year=AllProducts.get(position).get("createdOn").substring(0,4);
            String month=AllProducts.get(position).get("createdOn").substring(5,7);
            String year2= null;
            String month2= null;
            try {
                year2 = AllProducts.get(position-1).get("createdOn").substring(0,4);
                month2 = AllProducts.get(position-1).get("createdOn").substring(5,7);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (year.equals(year2)&& month.equals(month2)){

                dateBox.setVisibility(View.GONE);
                dateBox.setText("");

            }
            else {
                if (AllProducts.get(position).get("isremarks").equals("")) {

                    dateBox.setVisibility(View.GONE);
                    dateBox.setText("");

                }
                else{
                    dateBox.setVisibility(View.VISIBLE);
                    String date = month + "-" + year;
                    dateBox.setText(date);

                }

            }




            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title1.setTypeface(tvFont);

            title1.setText("Remark ");
            remarkValue1.setText(AllProducts.get(position).get("remark"));

//            if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("true")){
//                relative.setVisibility(View.VISIBLE);
//                title1.setText("Remark ");
//                remarkValue1.setText(AllProducts.get(position).get("remark"));
//            }
//            else if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("false")){
//                relative.setVisibility(View.GONE);
//            }


            //int code= Integer.parseInt(AllProducts.get(position).get("emojiIcon"));

            String imageUrl2="http://35.184.93.23:3000/assets/img/icon/"+AllProducts.get(position).get("emojiIcon");

            Picasso.with(getActivity()).load(imageUrl2).into(iv11);

            //iv1.setText(Html.fromHtml(AllProducts.get(position).get("emojiIcon")));
//            iv1.setText(Html.fromHtml("&#9786;"));

//            iv1.setText(Html.fromHtml("\ud83d\ude12"));
            return convertView;
        }
    }



}
