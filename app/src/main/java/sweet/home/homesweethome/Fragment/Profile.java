package sweet.home.homesweethome.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import sweet.home.homesweethome.Activity.Emergency2;
import sweet.home.homesweethome.Activity.Login;
import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.Activity.SplashAct;
import sweet.home.homesweethome.Activity.Successfully;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }

    TextView teacherMenu,activitmenu,weekMenu,foddMenu,textViewUser;
    Dialog dialog4;
    //    ImageView switchUser;
    Spinner switchUser,optionmMenu;
    List<String> listUser = new ArrayList<String>();
    List<String> listUserGender = new ArrayList<String>();
    List<String> listUser2 = new ArrayList<String>();
    List<String> listUser3 = new ArrayList<String>();
    List<String> listUserID = new ArrayList<String>();
    List<String> listUserClassID = new ArrayList<String>();
    Dialog dialog;
    //    CircleImageView authorImageView;
    ImageView authorImageView;
    NetworkImageView classImage;
    //String [] opt  = {"Profile","Change Password","Logout"};
    ArrayList<String> opt=new ArrayList<>();
    public  static String sId, parentId;
    public  static String ClassId,childName;
    RelativeLayout relativeLayout;
    LinearLayout linearLayoutColor,linearLayoutColor2;
    ArrayAdapter subcat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        MainActivitie.mTopToolbar.setVisibility(View.VISIBLE);
        foddMenu=view.findViewById(R.id.foddMenu);
        weekMenu=view.findViewById(R.id.weekMenu);
        activitmenu=view.findViewById(R.id.activitmenu);
        teacherMenu=view.findViewById(R.id.teacherMenu);
        switchUser=view.findViewById(R.id.switchUser);
        textViewUser=view.findViewById(R.id.textViewUser);
        authorImageView=view.findViewById(R.id.authorImageView);
        classImage=view.findViewById(R.id.classImage);
        optionmMenu=view.findViewById(R.id.optionmMenu);

        linearLayoutColor=view.findViewById(R.id.linearLayoutColor);
        linearLayoutColor2=view.findViewById(R.id.linearLayoutColor2);
        relativeLayout=view.findViewById(R.id.relativeLayout);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        opt.clear();
        opt.add("Profile");
        opt.add("Change Password");
        opt.add("Logout");

        MainActivitie.profile.setImageResource(R.drawable.profilehover);
        MainActivitie.calender.setImageResource(R.drawable.calendar);
        MainActivitie.alerm.setImageResource(R.drawable.notification);
        MainActivitie.message.setImageResource(R.drawable.message);
        MainActivitie.menual.setImageResource(R.drawable.schoolmannual);

        ArrayAdapter option = new ArrayAdapter(getActivity(),R.layout.simple_spinner_user,opt);
        option.setDropDownViewResource(R.layout.simple_spinner_item);
        optionmMenu.setAdapter(option);

        optionmMenu.setSelection(0);

        optionmMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0){
                }
                else if (i==1){
                    Fragment fragment = new ChangePassword();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                }
                else if (i==2){
                    confirmationPopup();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        foddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPrefrences.setPositionFood(getActivity(),"");
                MyPrefrences.setPosition(getActivity(), String.valueOf(switchUser.getSelectedItemPosition()));
                Fragment fragment = new Food();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        weekMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPrefrences.setPosition(getActivity(), String.valueOf(switchUser.getSelectedItemPosition()));
                Fragment fragment = new WeekPlan();
                Bundle bundle=new Bundle();
                bundle.putString("ClassId",ClassId);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        activitmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("sdgdfgdfgdf", String.valueOf(switchUser.getSelectedItemPosition()));
                MyPrefrences.setPosition(getActivity(), String.valueOf(switchUser.getSelectedItemPosition()));
                Fragment fragment = new ActivitesA();
                Bundle bundle=new Bundle();
                bundle.putString("ClassId",ClassId);
                bundle.putString("studentId",sId);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });
        teacherMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPrefrences.setPosition(getActivity(), String.valueOf(switchUser.getSelectedItemPosition()));
                Fragment fragment = new RemarkAssessment();
                Bundle bundle=new Bundle();
                bundle.putString("sId",sId);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

//        switchUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Switch User working soon...", Toast.LENGTH_SHORT).show();
//            }
//        });


/// https://stackoverflow.com/questions/38784202/how-to-make-a-spinner-show-previously-selected-item-after-refresh


        partntdDetails();


//        if(!MyPrefrences.getPosition(getActivity()).equalsIgnoreCase(""))
//        {
//            int spinnerPosition = subcat.getPosition(MyPrefrences.getPosition(getActivity()));
//            switchUser.setSelection(spinnerPosition);
//            Log.d("gfdfgsdfgsdgfsd",MyPrefrences.getPosition(getActivity()));
//        }


        Log.d("dfgsdfsdfgdsg",MyPrefrences.getPosition(getActivity()));

//        if (listUser.isEmpty()){
//            Log.d("SDgdfgsdfgsdfgsdfsdf","true");
//        }
//        else{
//            Log.d("SDgdfgsdfgsdfgsdfsdf","false");
//
//            if (MyPrefrences.getPosition(getActivity()).equals("0")){
//                textViewUser.setText(listUser.get(0));
//            }
//            else if (MyPrefrences.getPosition(getActivity()).equals("1")){
//                textViewUser.setText(listUser.get(1));
//            }
//            else if (MyPrefrences.getPosition(getActivity()).equals("2")){
//                textViewUser.setText(listUser.get(2));
//            }
//        }


        Log.d("SDfdsfgvdsfsdfsgsg", String.valueOf(listUser));





       // switchUser.setSelection(3);

        switchUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("fgdfgdfgdfgsdgdf","true");
                MyPrefrences.setPosition(getActivity(),"");


                return false;
            }
        });




        switchUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (!MyPrefrences.getPosition(getActivity()).equals("")) {
                    switchUser.setSelection(Integer.parseInt(MyPrefrences.getPosition(getActivity())));
                }


                try {
                    Log.d("dfgdfgdfgdfgd",switchUser.getSelectedItem().toString());
                    textViewUser.setText(switchUser.getSelectedItem().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                    getActivity().finish();
                }

//                textViewUser.setText(listUser.get(i).toString());
                Log.d("fsdafsdafsdfs",listUserID.get(i));
                sId=listUserID.get(i).toString();
                ClassId=listUserClassID.get(i).toString();
                childName=switchUser.getSelectedItem().toString();

                String imageUrl="http://hshpreschooladmin.com/api/upload/"+listUser2.get(i);
                Picasso.with(getActivity()).load(imageUrl).into(authorImageView);

                Log.d("fsdfsdfsdfsd",listUser3.get(i));
                String imageUrl2="http://hshpreschooladmin.com/assets/img/class/"+listUser3.get(i);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                classImage.setImageUrl(imageUrl2, imageLoader);

//                if (listUserGender.get(i).equals("Male")){
//                    Log.d("SDfdsfsdfsdfdsgdsgdf","Male");
//                    relativeLayout.setBackgroundColor(Color.parseColor("#FFD1E3EC"));
//                    linearLayoutColor.setBackgroundColor(Color.parseColor("#FFD1E3EC"));
//                   // linearLayoutColor2.setBackgroundResource(R.drawable.redius_img_in);
//                    MyPrefrences.setColorGender(getActivity(),"male");
//                }
//                else  if (listUserGender.get(i).equals("Female")){
//                    Log.d("SDfdsfsdfsdfdsgdsgdf","Female");
//
//                    relativeLayout.setBackgroundColor(Color.parseColor("#eecacf"));
//                    linearLayoutColor.setBackgroundColor(Color.parseColor("#eecacf"));
//                    //linearLayoutColor2.setBackgroundResource(R.drawable.redius_img_in_male);
//                    MyPrefrences.setColorGender(getActivity(),"female");
//                }


                if (listUserGender.get(i).equals("Female")){
                    Log.d("SDfdsfsdfsdfdsgdsgdf","Female");

                    relativeLayout.setBackgroundColor(Color.parseColor("#eecacf"));
                    linearLayoutColor.setBackgroundColor(Color.parseColor("#eecacf"));
                    //linearLayoutColor2.setBackgroundResource(R.drawable.redius_img_in_male);
                    MyPrefrences.setColorGender(getActivity(),"female");
                    MainActivitie.mTopToolbar.setBackgroundColor(Color.parseColor("#eecacf"));
                }
                else{
                    Log.d("SDfdsfsdfsdfdsgdsgdf","Male");
                    relativeLayout.setBackgroundColor(Color.parseColor("#FFD1E3EC"));
                    linearLayoutColor.setBackgroundColor(Color.parseColor("#FFD1E3EC"));
                    // linearLayoutColor2.setBackgroundResource(R.drawable.redius_img_in);
                    MyPrefrences.setColorGender(getActivity(),"male");
                    MainActivitie.mTopToolbar.setBackgroundColor(Color.parseColor("#FFD1E3EC"));
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //switchUser.setSelection(3);
            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {


                        Button Yes_action,No_action;
                        TextView heading;
                        dialog4 = new Dialog(getActivity());
                        dialog4.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog4.setContentView(R.layout.update_state1);

                        Yes_action=(Button)dialog4.findViewById(R.id.Yes_action);
                        No_action=(Button)dialog4.findViewById(R.id.No_action);
                        heading=(TextView)dialog4.findViewById(R.id.heading);


                        heading.setText("Are you sure you want to exit?");
                        Yes_action.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyPrefrences.setPosition(getActivity(),"");
                                getActivity().finishAffinity();
                            }
                        });

                        No_action.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog4.dismiss();

                            }
                        });
                        dialog4.show();
//

                        //Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });

        return view;

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        partntdDetails();
//    }

    @Override
    public void onResume() {
        super.onResume();
        optionmMenu.setSelection(0);
    }

    private void confirmationPopup() {
        Button Yes_action,No_action;
        TextView heading;
        dialog4 = new Dialog(getActivity());
        dialog4.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog4.setContentView(R.layout.update_state1);

        Yes_action=(Button)dialog4.findViewById(R.id.Yes_action);
        No_action=(Button)dialog4.findViewById(R.id.No_action);
        heading=(TextView)dialog4.findViewById(R.id.heading);


        heading.setText("Are you sure you want to Logout");
        Yes_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog4.show();

                MyPrefrences.resetPrefrences(getActivity());
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finishAffinity();

                logoutApi();
            }
        });

        No_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4.dismiss();
                optionmMenu.setSelection(0);
            }
        });
        dialog4.show();

    }

    private void logoutApi() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, Api.Logout,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Util.cancelPgDialog(dialog);
                        Log.d("dfsdfsdfggdfgdfgdf", response.toString());


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Util.cancelPgDialog(dialog);
                VolleyLog.d("dsfsdfsdfsdf", "Error: " + error.getMessage());
//                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
//
                String authToken = MyPrefrences.getToken(getActivity());
                String bearer = "Bearer ".concat(authToken);
                headers.put("Authorization", bearer);
//

                return headers;
            }
        };
        queue.add(jsonObjReq);
    }


    private void partntdDetails() {

        Util.showPgDialog(dialog);
        //// TODO Header APi
        JsonObjectRequest parentMeRequest = new JsonObjectRequest(Api.parent,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.print(response);
                        Util.cancelPgDialog(dialog);
                        Log.d("gdfgdfghdfhdhgf",response.toString());

                        Log.d("ParenTId",response.optString("_id"));

                        parentId=response.optString("_id");
                        listUser.clear();
                        listUserGender.clear();
                        try {
                            JSONArray jsonArray=response.getJSONArray("child");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                JSONObject jsonObject1=jsonObject.getJSONObject("classes");

                                listUser.add(jsonObject.optString("name"));
                                listUserGender.add(jsonObject.optString("gender"));
                                listUser2.add(jsonObject.optString("image"));
                                listUser3.add(jsonObject1.optString("image"));
                                listUserID.add(jsonObject.optString("_id"));
                                listUserClassID.add(jsonObject1.optString("_id"));


                                subcat = new ArrayAdapter(getActivity(),R.layout.simple_spinner_dropdown_user,listUser);
                                subcat.setDropDownViewResource(R.layout.simple_spinner_item_user);
                                switchUser.setAdapter(subcat);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.toString());
                Util.cancelPgDialog(dialog);
                Log.d("gdfgdfghdfhdhgf",volleyError.toString());
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


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // TODO Add your menu entries here
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.action_settings:
//
//                // Not implemented here
//                return false;
//
//            default:
//                break;
//        }
//
//        return false;
//    }





}
