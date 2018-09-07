package sweet.home.pinerria1.Fragment;


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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.Const;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assessment_detail, container, false);



        AllProducts = new ArrayList<>();
        expListView = (ListView) view.findViewById(R.id.lvExp);

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
        expListView.addHeaderView(headerView);

        pageParagraph=headerView.findViewById(R.id.pageParagraph);

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
                            pageParagraph.setText(jsonObject.optString("pageParagraph"));


                            JSONArray jsonArray=jsonObject.getJSONArray("resultDataArray");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);

                                HashMap<String,String> map=new HashMap<>();
                                map.put("assessmentTitle",jsonObject1.optString("assessmentTitle"));
                                map.put("title",jsonObject1.optString("title"));
                                map.put("templateType",jsonObject1.optString("templateType"));

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
        TextView title,assessmentTitle,byUser,type,dateBox;

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
            title=convertView.findViewById(R.id.title);



            assessmentTitle.setText(AllProducts.get(position).get("assessmentTitle"));

            if (AllProducts.get(position).get("templateType").equals("category")) {
                title.setText(AllProducts.get(position).get("title"));
                title.setTextColor(Color.parseColor("#FF60B6E7"));
            }
            else  if (AllProducts.get(position).get("templateType").equals("subcategory")) {
                title.setText(AllProducts.get(position).get("title"));
                title.setTextColor(Color.parseColor("#FF6B696A"));
            }
            else  if (AllProducts.get(position).get("templateType").equals("assessment")) {
                title.setText(AllProducts.get(position).get("title"));
                title.setTextColor(Color.parseColor("#111111"));
            }


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);



            return convertView;
        }
    }

}