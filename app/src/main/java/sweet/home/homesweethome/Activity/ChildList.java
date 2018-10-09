package sweet.home.homesweethome.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;

public class ChildList extends AppCompatActivity {

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    Button btnGetQuote;
    Dialog dialog;
    TextView textViewUser;
    CircleImageView authorImageView;
    ImageView classImage;
    public static  int val1=1;
    String stId;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);

        AllProducts = new ArrayList<>();
       // expListView = (GridView) findViewById(R.id.lvExp);
        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        textViewUser = (TextView) findViewById(R.id.textViewUser);
        authorImageView = (CircleImageView) findViewById(R.id.authorImageView);
        classImage = (ImageView) findViewById(R.id.classImage);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        btnGetQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ChildList.this, ChildInfo.class);
                intent.putExtra("value", MyPrefrences.getNoOfChild(getApplicationContext()));
                intent.putExtra("name", textViewUser.getText().toString());
                intent.putExtra("id", stId);
                startActivity(intent);

            }
        });

        Log.d("sdfsdgd",MyPrefrences.getChildList(getApplicationContext()));
        try {


            JSONArray jsonArray=new JSONArray(MyPrefrences.getChildList(getApplicationContext()));

            if (val1==1) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                textViewUser.setText(jsonObject.optString("name"));

                JSONObject jsonObject1=jsonObject.getJSONObject("classes");

                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in);
            }
            else  if (val1==2) {
                JSONObject jsonObject = jsonArray.getJSONObject(1);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));

                relativeLayout.setBackgroundResource(R.drawable.redius_img_in_male);
            }
            else  if (val1==3) {
                JSONObject jsonObject = jsonArray.getJSONObject(2);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in);
            }
            else  if (val1==4) {
                JSONObject jsonObject = jsonArray.getJSONObject(3);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl =Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in_male);
            }
            else  if (val1==5) {
                JSONObject jsonObject = jsonArray.getJSONObject(4);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in);
            }
            else  if (val1==6) {
                JSONObject jsonObject = jsonArray.getJSONObject(5);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in_male);
            }
            else  if (val1==7) {
                JSONObject jsonObject = jsonArray.getJSONObject(6);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl =Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in);
            }
            else  if (val1==8) {
                JSONObject jsonObject = jsonArray.getJSONObject(7);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));

                relativeLayout.setBackgroundResource(R.drawable.redius_img_in_male);
            }
            else  if (val1==9) {
                JSONObject jsonObject = jsonArray.getJSONObject(8);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl = Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));
                relativeLayout.setBackgroundResource(R.drawable.redius_img_in);
            }
            else  if (val1==10) {
                JSONObject jsonObject = jsonArray.getJSONObject(9);
                textViewUser.setText(jsonObject.optString("name"));
                JSONObject jsonObject1=jsonObject.getJSONObject("classes");
                stId=jsonObject.optString("_id");
                String imageUrl =Api.ImageURL+"upload/" + jsonObject.optString("image");
                String imageUrlCLASS = Api.ImageURLCLASS+ jsonObject1.optString("image");

                Picasso.with(getApplicationContext()).load(imageUrl).into(authorImageView);
                Picasso.with(getApplicationContext()).load(imageUrlCLASS).into(classImage);
                Log.d("dfsdfsdfgsdgdgdf", jsonObject.optString("name"));

                relativeLayout.setBackgroundResource(R.drawable.redius_img_in_male);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //// TODO Header APi

//        JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.parent,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                       // System.out.print(response);
//                        Log.d("gdfgdfghdfhdhgf",response.toString());
//
//                        try {
//                            JSONArray jsonArray=response.getJSONArray("child");
//                            for (int i=0;i<jsonArray.length();i++) {
//                                JSONObject jsonObject=jsonArray.getJSONObject(i);
//
//
//                                HashMap<String,String> map=new HashMap<>();
//
//                                map.put("_id", jsonObject.optString("_id"));
//                                map.put("name", jsonObject.optString("name"));
//
//                                MyPrefrences.setNoOfChild(getApplicationContext(), String.valueOf(jsonArray.length()));
//
//
//                               // textViewUser.setText();
////                                Adapter adapter=new Adapter();
////                                expListView.setAdapter(adapter);
////                                AllProducts.add(map);
//
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
////                        listUser.add("Asma Ali");
////                        listUser.add("Asif Ali");
////                        listUser.add("Afsana");
//
//
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//              //  System.out.println(volleyError.toString());
//                Log.d("gdfgdfghdfhdfddddfdhgf",volleyError.toString());
//            }
//        })
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> header = new HashMap<>();
//                String authToken = MyPrefrences.getToken(getApplicationContext());
//                String bearer = "Bearer ".concat(authToken);
//                header.put("Authorization", bearer);
//
//                return header;
//            }
//        };
//        //System.out.print("called twice");
////                    SingletonRequestQueue.getInstance(getActivity()).getRequestQueue().add(parentMeRequest);
//        AppController.getInstance().addToRequestQueue(parentMeRequest);


    }



//    class Adapter extends BaseAdapter {
//
//        LayoutInflater inflater;
//        TextView textview;
//
//        Adapter() {
//            inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        }
//
//        @Override
//        public int getCount() {
//            return AllProducts.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return AllProducts.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            convertView=inflater.inflate(R.layout.list_s_list,parent,false);
//            textview=convertView.findViewById(R.id.textview);
//            textview.setText(AllProducts.get(position).get("name"));
//
//
////            Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "muli_semibold.ttf");
////            textview.setTypeface(face);
//
//            return convertView;
//        }
//    }


}
