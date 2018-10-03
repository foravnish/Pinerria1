package sweet.home.homesweethome.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.AppController;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityImage extends Fragment {


    public ActivityImage() {
        // Required empty public constructor
    }

    TextView downalod,title,desc,back;
    NetworkImageView network;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_activity_image, container, false);

        Log.d("fsdgdgdfgdfg",getArguments().getString("image"));

        desc=view.findViewById(R.id.desc);
        title=view.findViewById(R.id.title);
        downalod=view.findViewById(R.id.downalod);
        network=view.findViewById(R.id.network);
        back=view.findViewById(R.id.back);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        network.setImageUrl(getArguments().getString("image"), imageLoader);

        title.setText(getArguments().getString("title"));
        desc.setText(getArguments().getString("description"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getActivity().finish();
            }
        });

        downalod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
