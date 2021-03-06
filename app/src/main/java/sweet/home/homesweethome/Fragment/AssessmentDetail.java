package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class AssessmentDetail extends Fragment {


    public AssessmentDetail() {
        // Required empty public constructor
    }

    Dialog dialog;
    TextView pageParagraph;

    List<HashMap<String,String>> AllProducts ;
    ListView expListView;

    JSONObject jsonObjectuserSelection;
    private View stickyViewSpacer;
    private LinearLayout stickyView;
    private View heroImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assessment_detail, container, false);



        AllProducts = new ArrayList<>();
        expListView = (ListView) view.findViewById(R.id.listView);
        stickyView = (LinearLayout) view.findViewById(R.id.stickyView);
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        heroImageView = view.findViewById(R.id.heroImageView);
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        Log.d("AssessmentID",getArguments().getString("assessmentId"));

        ImageView textBack= view.findViewById(R.id.textBack);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RemarkAssessment();
                Bundle bundle=new Bundle();
                bundle.putString("sId",getArguments().getString("sId"));
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        View headerView = ((LayoutInflater)getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, null, false);
        stickyViewSpacer  = headerView.findViewById(R.id.stickyViewPlaceholder);
        expListView.addHeaderView(headerView);

        pageParagraph=headerView.findViewById(R.id.pageParagraph);


        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                /* Check if the first item is already reached to top.*/
                if (expListView.getFirstVisiblePosition() == 0) {
                    View firstChild = expListView.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }

                    int heroTopY = stickyViewSpacer.getTop();
                    stickyView.setY(Math.max(0, heroTopY + topY));

                    /* Set the image to scroll half of the amount that of ListView */
                    heroImageView.setY(topY * 0.5f);
                }
            }
        });

        assessmentDetail();



        return  view;
    }




    private void assessmentDetail() {
        Util.showPgDialog(dialog);


        JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.AssessmentById+getArguments().getString("assessmentId"),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.print(response);
                        Util.cancelPgDialog(dialog);
                        Log.d("AassDetlResponse",response.toString());

                        try {
                            JSONObject jsonObject=response.getJSONObject("templateData");
                            JSONObject object=jsonObject.getJSONObject("pageParagraph");
                            jsonObjectuserSelection   =response.getJSONObject("userSelection");

                            pageParagraph.setText(object.optString("en")+"\n"+object.optString("ar")+"\nD: Developing    M:Meeting     E:Exceeding\n");

                            JSONArray jsonArray=jsonObject.getJSONArray("resultDataArray");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);

                                HashMap<String,String> map=new HashMap<>();
                                map.put("assessmentTitle",jsonObject1.optString("assessmentTitle"));
                                map.put("title",jsonObject1.optString("title"));
                                map.put("templateType",jsonObject1.optString("templateType"));
                                map.put("assessmentId",jsonObject1.optString("assessmentId"));

                                Adapter adapter=new Adapter();
                                expListView.setAdapter(adapter);
                                AllProducts.add(map);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.toString());
                Util.cancelPgDialog(dialog);
                Log.d("AassDetlErrorResponse",volleyError.toString());
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
        //System.out.print("called twice");
//                    SingletonRequestQueue.getInstance(getActivity()).getRequestQueue().add(parentMeRequest);
        AppController.getInstance().addToRequestQueue(parentMeRequest);




    }


    class Adapter extends BaseAdapter {

        LayoutInflater inflater;
        TextView assess,assessmentTitle,subCategory,category,dateBox;
        LinearLayout assessLayout;
        CheckBox check1,check2,check3;
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


            convertView=inflater.inflate(R.layout.list_assessment_detail,parent,false);

            assessmentTitle=convertView.findViewById(R.id.assessmentTitle);
            assess=convertView.findViewById(R.id.assess);
            category=convertView.findViewById(R.id.category);
            subCategory=convertView.findViewById(R.id.subCategory);
            assessLayout=convertView.findViewById(R.id.assessLayout);
            check1=convertView.findViewById(R.id.check1);
            check2=convertView.findViewById(R.id.check2);
            check3=convertView.findViewById(R.id.check3);

            assessmentTitle.setText(AllProducts.get(position).get("assessmentTitle"));

            if (AllProducts.get(position).get("templateType").equals("category")) {
                category.setText(AllProducts.get(position).get("title"));
                category.setTextColor(Color.parseColor("#FF60B6E7"));
                category.setVisibility(View.VISIBLE);
                subCategory.setVisibility(View.GONE);
                assess.setVisibility(View.GONE);
                assessLayout.setVisibility(View.GONE);
            }
            else  if (AllProducts.get(position).get("templateType").equals("subcategory")) {
                subCategory.setText(AllProducts.get(position).get("title"));
                subCategory.setTextColor(Color.parseColor("#FF6B696A"));
                category.setVisibility(View.GONE);
                subCategory.setVisibility(View.VISIBLE);
                assess.setVisibility(View.GONE);
                assessLayout.setVisibility(View.GONE);
            }
            else  if (AllProducts.get(position).get("templateType").equals("assessment")) {
                assess.setText(AllProducts.get(position).get("title"));
                assess.setTextColor(Color.parseColor("#111111"));
                category.setVisibility(View.GONE);
                subCategory.setVisibility(View.GONE);
                assess.setVisibility(View.VISIBLE);
                assessLayout.setVisibility(View.VISIBLE);
            }


//            if (AllProducts.get(position).get("assessmentId").contains(String.valueOf(jsonObjectuserSelection))){
//                Log.d("dsfgdgdfgdfgdfgd","True");
//
//            }
            Log.d("fgdfgdfgdfgd", String.valueOf(jsonObjectuserSelection));





            Log.d("gdfgfdsgdfsgdsf",jsonObjectuserSelection.optString(AllProducts.get(position).get("assessmentId")));

            if (!jsonObjectuserSelection.optString(AllProducts.get(position).get("assessmentId")).equals("")){

                if (getArguments().getString("selectedSemester").equals("semTwo")){
                    try {
                        JSONObject jsonObject=new JSONObject(jsonObjectuserSelection.optString(AllProducts.get(position).get("assessmentId")));
                        if (jsonObject.optString("semTwo").equals("D")){
                            check1.setChecked(true);
                            check2.setChecked(false);
                            check3.setChecked(false);
                        }
                        else  if (jsonObject.optString("semTwo").equals("M")){
                            check1.setChecked(false);
                            check2.setChecked(true);
                            check3.setChecked(false);
                        }
                        else  if (jsonObject.optString("semTwo").equals("E")){
                            check1.setChecked(false);
                            check2.setChecked(false);
                            check3.setChecked(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else if (getArguments().getString("selectedSemester").equals("semOne")){
                    try {
                        JSONObject jsonObject=new JSONObject(jsonObjectuserSelection.optString(AllProducts.get(position).get("assessmentId")));
                        if (jsonObject.optString("semOne").equals("D")){
                            check1.setChecked(true);
                            check2.setChecked(false);
                            check3.setChecked(false);
                        }
                        else  if (jsonObject.optString("semOne").equals("M")){
                            check1.setChecked(false);
                            check2.setChecked(true);
                            check3.setChecked(false);
                        }
                        else  if (jsonObject.optString("semOne").equals("E")){
                            check1.setChecked(false);
                            check2.setChecked(false);
                            check3.setChecked(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
            else{
                check1.setChecked(false);
            }



            Log.d("dfdvdgdgd", AllProducts.get(position).get("assessmentId"));

            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            assess.setTypeface(tvFont);


            return convertView;
        }
    }
}
