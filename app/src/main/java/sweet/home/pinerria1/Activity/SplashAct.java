package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sweet.home.pinerria1.R;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(4*1000);

                    Intent intent = new Intent(SplashAct.this, Login.class);
                    startActivity(intent);
                    finish();


//                    if (MyPrefrences.getUserLogin(SplashScreen.this)==true){
//                        Intent intent =new Intent(SplashScreen.this, MainActivity.class);
//                        intent.putExtra("isflag","0");
//                        startActivity(intent);
//                        finish();
//                    }
//                    else {
//                        Intent intent = new Intent(SplashScreen.this, LoginAct.class);
//                        startActivity(intent);
//                        finish();
//                    }

                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}
