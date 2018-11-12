package sweet.home.homesweethome.Fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menual extends Fragment {


    public Menual() {
        // Required empty public constructor
    }
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menual, container, false);
        MainActivitie.mTopToolbar.setVisibility(View.GONE);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
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
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        return view;
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Welcome to families");
        listDataHeader.add("Admission Policy");
        listDataHeader.add("Curriculum");
        listDataHeader.add("Campus");
        listDataHeader.add("Achivements");


        // Adding child data
        List<String> top1 = new ArrayList<String>();
        top1.add("Philosophy");
        top1.add("Vision");
        top1.add("Mission");
        top1.add("Licencing");
        top1.add("Confidentiality");


        List<String> top2 = new ArrayList<String>();
        top2.add("Philosophy");
        top2.add("Vision");
        top2.add("Mission");
        top2.add("Licencing");
        top2.add("Confidentiality");



        List<String> top3 = new ArrayList<String>();
        top3.add("Philosophy");
        top3.add("Vision");
        top3.add("Mission");
        top3.add("Licencing");
        top3.add("Confidentiality");


        List<String> top4 = new ArrayList<String>();
        top4.add("Philosophy");
        top4.add("Vision");
        top4.add("Mission");
        top4.add("Licencing");
        top4.add("Confidentiality");

        List<String> top5 = new ArrayList<String>();
        top5.add("Philosophy");
        top5.add("Vision");
        top5.add("Mission");
        top5.add("Licencing");
        top5.add("Confidentiality");


        listDataChild.put(listDataHeader.get(0), top1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), top2);
        listDataChild.put(listDataHeader.get(2), top3);
        listDataChild.put(listDataHeader.get(3), top4);
        listDataChild.put(listDataHeader.get(4), top5);

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item2, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            txtListChild.setText(childText);
            return convertView;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group2, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            //lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
