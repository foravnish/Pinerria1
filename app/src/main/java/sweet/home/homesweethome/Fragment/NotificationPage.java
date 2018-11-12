package sweet.home.homesweethome.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.ProductsAdapter;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;
import sweet.home.homesweethome.Utils.AppController;
import sweet.home.homesweethome.Utils.MyPrefrences;
import sweet.home.homesweethome.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationPage extends Fragment {


    public NotificationPage() {
        // Required empty public constructor
    }
    private int pageIndex = 1;
    private RelativeLayout progressBar;
    private RecyclerView products_rclv;
    private ArrayList<HashMap<String, String>> products_arrayList;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading = false;
    ProductsAdapter productsAdapter;
    RelativeLayout relat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification_page, container, false);
        progressBar = (RelativeLayout) view.findViewById(R.id.rltv_progressBar);
        products_rclv = (RecyclerView) view.findViewById(R.id.rclv_products);
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        relat = (RelativeLayout) view.findViewById(R.id.relat);

        products_arrayList = new ArrayList<>();
//        getProductData(Api.Notification+"?catId=" + getArguments().getString("cat_id").toString()+"&page_no=0");
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
        getProductData(Api.Notification+"?pagenum=1&perpage=8");


        MainActivitie.profile.setImageResource(R.drawable.profile);
        MainActivitie.calender.setImageResource(R.drawable.calendar);
        MainActivitie.alerm.setImageResource(R.drawable.notificationhover);
        MainActivitie.message.setImageResource(R.drawable.message);
        MainActivitie.menual.setImageResource(R.drawable.schoolmannual);


        products_rclv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        isLoading = true;

                        pageIndex++;

//                        String ProductsUrl = ConstantData.service_URL + "search?page="+pageIndex+"&pageSize=15";
//                        String ProductsUrl = Api.subCategoriesList+"?catId=" + getArguments().getString("cat_id").toString()+"&page_no="+pageIndex;
                        String ProductsUrl = Api.Notification+"?pagenum="+pageIndex+"&perpage=8";

                        getProductData(ProductsUrl);
                    }
                }


                products_rclv.addOnItemTouchListener(
                        new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                // TODO Handle item click
                                Log.d("fsdfsdfsdgfsd", "dfgdfg"+position);


//                                Fragment fragment=new CatagoryViewFragment();
//                                FragmentManager manager=getFragmentManager();
//                                FragmentTransaction ft=manager.beginTransaction();
//                                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
//                                Bundle bundle=new Bundle();
//                                bundle.putString("product_id",products_arrayList.get(position).get("id").toString());
//                                //bundle.putString("product_image",DataList.get(position).getDesc().toString());
//                                fragment.setArguments(bundle);

                            }
                        })
                );



            }
        });

        if (MyPrefrences.getColorGender(getActivity()).equals("male")){
            Log.d("dfggfgdg","Male");
            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (MyPrefrences.getColorGender(getActivity()).equals("female")){
            Log.d("dfggfgdg","Female");
            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }

        return  view;
    }

    private void getProductData(String ProductsUrl) {
        progressBar.setVisibility(View.VISIBLE);

        final int pageNumber = pageIndex;

//
        JsonArrayRequest mJsonObjectRequest=new JsonArrayRequest(ProductsUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() >= 1) {
                    //  mainActPresenter.setAllData(response,pageIndex);

                    Log.d("sdfsdfsdfsdfs", String.valueOf(response));
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject products_jsonObject = response.getJSONObject(i);

//                                Products_Pojo products_pojo = new Products_Pojo();
                            HashMap<String, String> map = new HashMap<>();

                            if (products_jsonObject.has("_id")) {
                                if (!(products_jsonObject.isNull("_id"))) {

                                    map.put("_id", products_jsonObject.optString("_id"));
                                    map.put("recordId", products_jsonObject.optString("recordId"));
                                    map.put("type", products_jsonObject.optString("type"));
                                    map.put("title", products_jsonObject.optString("title"));
                                    map.put("language", products_jsonObject.optString("language"));
                                    map.put("description", products_jsonObject.optString("description"));
                                    map.put("createdByRole", products_jsonObject.optString("createdByRole"));
                                    map.put("createdOn", products_jsonObject.optString("createdOn"));
                                }
                            }
                            products_arrayList.add(map);
                        }
//                            mactView.updateData(products_arrayList);

                        if (pageIndex == 1) {
                            productsAdapter = new ProductsAdapter(getActivity(), products_arrayList,products_rclv);

                            products_rclv.setAdapter(productsAdapter);

//                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                            // products_rclv.setLayoutManager(mLayoutManager);

                            linearLayoutManager = new LinearLayoutManager(getActivity());
                            products_rclv.setLayoutManager(linearLayoutManager);
                        } else {
                            isLoading = false;
                            if (productsAdapter != null) {
                                // notifyDataSetChanged is used to Notify The Adapter afer having Changes in RecyclerView
                                productsAdapter.notifyDataSetChanged();
                            }
                        }

//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    if (pageNumber != 0) {
                        pageIndex = pageNumber - 1;
                    }
                }

                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errorcala",error.toString());
               // Util.cancelPgDialog(dialog);
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

        mJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        MySingleton.getInstance(MainActivity.this).addToRequestQueue(mJsonObjectRequest);
        AppController.getInstance().addToRequestQueue(mJsonObjectRequest);

    }


    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            }
            return false;
        }

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
//            View childView = view.findChildViewUnder(e.getX(), e.getY());
//            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
//                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
//            }
//            return false;
//        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
