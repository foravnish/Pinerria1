package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import sweet.home.homesweethome.R;

public class Successfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully);

        ImageView proceed=findViewById(R.id.proceed);


        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(3*1000);

                    Intent intent = new Intent(Successfully.this, MainActivitie.class);
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


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Successfully.this,MainActivitie.class));
            }
        });
    }
}
