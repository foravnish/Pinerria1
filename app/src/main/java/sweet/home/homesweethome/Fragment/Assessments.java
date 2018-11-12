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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class Assessments extends Fragment {


    public Assessments() {
        // Required empty public constructor
    }

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Dialog dialog;
    TextView txtNoData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assessments, container, false);

        AllProducts = new ArrayList<>();
        expListView = (GridView) view.findViewById(R.id.lvExp);
        txtNoData = (TextView) view.findViewById(R.id.txtNoData);

        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        assessmentData();


        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getActivity(), ""+AllProducts.get(i).get("_id"), Toast.LENGTH_SHORT).show();

                Fragment fragment = new AssessmentDetail();
                Bundle bundle=new Bundle();
                bundle.putString("assessmentId",AllProducts.get(i).get("assessmentId"));
                bundle.putString("selectedSemester",AllProducts.get(i).get("selectedSemester"));
                bundle.putString("sId",getArguments().getString("sId"));
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

    public static Fragment NewInstance(String typeforListing) {
        Bundle args = new Bundle();
        args.putString("sId", typeforListing);

        Assessments fragment = new Assessments();
        fragment.setArguments(args);

        return fragment;
    }

    private void assessmentData() {
        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Assessment+getArguments().getString("sId"), new Response.Listener<JSONArray>() {
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Remark+"5b180e59b1fbed41daa94c2c", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseRemark",response.toString());
                Util.cancelPgDialog(dialog);


                if (response.length()!=0) {

                    txtNoData.setVisibility(View.GONE);
                    expListView.setVisibility(View.VISIBLE);


                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);


                            HashMap<String, String> map = new HashMap<>();

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
                            map.put("assessmentId", jsonObject.optString("assessmentId"));
                            map.put("selectedSemester", jsonObject.optString("selectedSemester"));
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
        TextView title,remarkValue,dateBox,semester;
        ImageView iv1;
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


            convertView=inflater.inflate(R.layout.list_assessment,parent,false);
            title=convertView.findViewById(R.id.title);
            remarkValue=convertView.findViewById(R.id.remarkValue);
            iv1=convertView.findViewById(R.id.iv1);
            relative=convertView.findViewById(R.id.relative);
            dateBox=convertView.findViewById(R.id.dateBox);
            semester=convertView.findViewById(R.id.semester);


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);

            title.setText("Assessment");
            remarkValue.setText(AllProducts.get(position).get("remark"));

//            if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("true")){
//                relative.setVisibility(View.GONE);
//
//            }
//            else if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("false")){
//                relative.setVisibility(View.VISIBLE);
//                title.setText("Assessment");
//                remarkValue.setText(AllProducts.get(position).get("remark"));
//            }

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
                dateBox.setVisibility(View.VISIBLE);
                String date=month+"-"+year;
                dateBox.setText(date);

            }

            if (AllProducts.get(position).get("selectedSemester").equals("semTwo")){
                semester.setText("Semester Two");
            }
            else if (AllProducts.get(position).get("selectedSemester").equals("semOne")){
                semester.setText("Semester One");
            }


            //int code= Integer.parseInt(AllProducts.get(position).get("emojiIcon"));

            String imageUrl2="http://hshpreschooladmin.com/assets/img/icon/"+AllProducts.get(position).get("emojiIcon");


            Picasso.with(getActivity()).load(imageUrl2).into(iv1);

            //iv1.setText(Html.fromHtml(AllProducts.get(position).get("emojiIcon")));
//            iv1.setText(Html.fromHtml("&#9786;"));

//            iv1.setText(Html.fromHtml("\ud83d\ude12"));
            return convertView;
        }
    }




}
