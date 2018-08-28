package sweet.home.pinerria1.Fragment;


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

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.Api;
import sweet.home.pinerria1.Utils.AppController;
import sweet.home.pinerria1.Utils.MyPrefrences;
import sweet.home.pinerria1.Utils.Util;

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

        Log.d("StudentId",getArguments().getString("sId"));


        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ReamrkofStudent();

        return view;
    }

    private void ReamrkofStudent() {
        Util.showPgDialog(dialog);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Remark+getArguments().getString("sId"), new Response.Listener<JSONArray>() {
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Api.Remark+"5b1414bb9f3e5271f50c1aa6", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("ResponseRemark",response.toString());
                Util.cancelPgDialog(dialog);


                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);


                        HashMap<String,String> map=new HashMap<>();

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
                        Adapter adapter=new Adapter();
                        expListView.setAdapter(adapter);
                        AllProducts.add(map);



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
        TextView title,remarkValue;
        ImageView iv1;

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
            title=convertView.findViewById(R.id.title);
            remarkValue=convertView.findViewById(R.id.remarkValue);
            iv1=convertView.findViewById(R.id.iv1);


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);

            if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("yes")){
                title.setText("Remark");
            }
            else if (AllProducts.get(position).get("isremarks").equalsIgnoreCase("no")){
                title.setText("Assessment");
            }

            remarkValue.setText(AllProducts.get(position).get("remark"));
            //int code= Integer.parseInt(AllProducts.get(position).get("emojiIcon"));

            String imageUrl2="http://35.184.93.23:3000/assets/img/icon/"+AllProducts.get(position).get("emojiIcon");

            Picasso.with(getActivity()).load(imageUrl2).into(iv1);

            //iv1.setText(Html.fromHtml(AllProducts.get(position).get("emojiIcon")));
//            iv1.setText(Html.fromHtml("&#9786;"));

//            iv1.setText(Html.fromHtml("\ud83d\ude12"));
            return convertView;
        }
    }



}
