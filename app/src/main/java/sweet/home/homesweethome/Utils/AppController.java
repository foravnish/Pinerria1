package sweet.home.homesweethome.Utils;

/**
 * Created by developer on 28-Sep-15.
 */


import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        try {
            super.onCreate();
            mInstance = this;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        try {
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void addToRequestQueue(Request<T> req) {
        try {
            req.setTag(TAG);
            getRequestQueue().add(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelPendingRequests(Object tag) {
        try {
            if (mRequestQueue != null) {
                mRequestQueue.cancelAll(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
