package sweet.home.homesweethome.Fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.okhttp.internal.Util;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sweet.home.homesweethome.Activity.FatherInfo;
import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.AppController;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityImage extends Fragment {


    public ActivityImage() {
        // Required empty public constructor
    }

    TextView downalod,title,desc,back;
//    NetworkImageView network;
    ImageView network;
    Dialog dialog;
    private AsyncTask mMyTask;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

    Bitmap bitmap ;
    String mSavedInfo;

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

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

//        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//        network.setImageUrl(getArguments().getString("image"), imageLoader);

        Picasso.with(getActivity()).load(getArguments().getString("image")).into(network);

        title.setText(getArguments().getString("title"));
        desc.setText(getArguments().getString("description"));



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new ActivitesA();

                Bundle bundle=new Bundle();
                bundle.putString("ClassId",getArguments().getString("ClassId"));
                bundle.putString("studentId",getArguments().getString("studentId"));

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });



        downalod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sweet.home.homesweethome.Utils.Util.showPgDialog(dialog);
                new DownloadFromURL().execute(getArguments().getString("image"));
            }
        });




        return view;
    }



    class DownloadFromURL extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            showDialog(progressType);


        }

        @Override
        protected String doInBackground(String... fileUrl) {
            int count;
            sweet.home.homesweethome.Utils.Util.cancelPgDialog(dialog);

            try {
                URL url = new URL(fileUrl[0]);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                // show progress bar 0-100%
                int fileLength = urlConnection.getContentLength();
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());

                OutputStream outputStream = new FileOutputStream("/sdcard/HSH_Image"+ currentDateandTime+".jpg");

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));
                    outputStream.write(data, 0, count);
                }
                // flushing output
                outputStream.flush();
                // closing streams
                outputStream.close();
                inputStream.close();


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        // progress bar Updating

        protected void onProgressUpdate(String... progress) {
            // progress percentage
//            progressDialog.setProgress(Integer.parseInt(progress[0]));
//            sweet.home.homesweethome.Utils.Util.cancelPgDialog(dialog);
//            Toast.makeText(getActivity(), "Image saved successfully.", Toast.LENGTH_SHORT).show();
            //sweet.home.homesweethome.Utils.Util.errorDialog2(getActivity(),"Image saved successfully.");
        }

        @Override
        protected void onPostExecute(String file_url) {
          //  dismissDialog(progressType);
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
            sweet.home.homesweethome.Utils.Util.errorDialog2(getActivity(),"Image saved successfully.");
            Log.d("dfdgdfgfdgdfgd",imagePath);
            //imageView.setImageDrawable(Drawable.createFromPath(imagePath));
        }
    }

    //progress dialog
//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case progressType: // we set this to 0
//                progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage("File is Downloading. Please wait...");
//                progressDialog.setIndeterminate(false);
//                progressDialog.setMax(100);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setCancelable(true);
//                progressDialog.show();
//                return progressDialog;
//            default:
//                return null;
//        }

}


