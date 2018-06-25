package sweet.home.pinerria1.Fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sweet.home.pinerria1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekPlan extends Fragment {


    public WeekPlan() {
        // Required empty public constructor
    }

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    List<HashMap<String,String>> AllProducts ;
    GridView expListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_week_plan, container, false);

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

        HashMap<String,String> map=new HashMap<>();
        for (int i=0;i<20;i++) {
            map.put("name", "Name");
            Adapter adapter=new Adapter();
            expListView.setAdapter(adapter);
            AllProducts.add(map);
        }


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


            convertView=inflater.inflate(R.layout.list_week_plan,parent,false);
            title=convertView.findViewById(R.id.title);


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "comicz.ttf");
            title.setTypeface(tvFont);



            return convertView;
        }
    }

//    public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {
//
//        ArrayList<String> alName;
//        ArrayList<Integer> alImage;
//        List<HashMap<String,String>> allProducts;
//        Context context;
//
//        public HLVAdapter(Context context, List<HashMap<String, String>> allProducts) {
//            super();
//            this.context = context;
//            this.allProducts = allProducts;
//            this.alImage = alImage;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.list_week_plan_week, viewGroup, false);
//            ViewHolder viewHolder = new ViewHolder(v);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder viewHolder, int i) {
//            //viewHolder.tvSpecies.setText(AllProducts.get(i).get("name"));
//            //viewHolder.imgThumbnail.setImageResource(alImage.get(i));
//
//            Log.d("fvsdgsdgdfg",allProducts.get(i).get("name"));
//            viewHolder.bnt_Week.setText("Week "+allProducts.get(i).get("name"));
//
////            viewHolder.setClickListener(new ItemClickListener() {
////                @Override
////                public void onClick(View view, int position, boolean isLongClick) {
////                    if (isLongClick) {
////                        Toast.makeText(context, "#" + position + " - " + alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
////                        context.startActivity(new Intent(context, MainActivity.class));
////                    } else {
////                        Toast.makeText(context, "#" + position + " - " + alName.get(position), Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return AllProducts.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//
//            public Button bnt_Week;
//            public TextView tvSpecies;
//            //private ItemClickListener clickListener;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                bnt_Week = (Button) itemView.findViewById(R.id.bnt_Week);
//            }
//
//            @Override
//            public void onClick(View view) {
//
//            }
//
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//
////            public void setClickListener(ItemClickListener itemClickListener) {
////                this.clickListener = itemClickListener;
////            }
//
////            @Override
////            public void onClick(View view) {
////                clickListener.onClick(view, getPosition(), false);
////            }
//
////            @Override
////            public boolean onLongClick(View view) {
////                clickListener.onClick(view, getPosition(), true);
////                return true;
////            }
//        }
//
//    }

}
