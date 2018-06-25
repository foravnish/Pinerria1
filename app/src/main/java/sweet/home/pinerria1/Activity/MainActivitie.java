package sweet.home.pinerria1.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import sweet.home.pinerria1.Fragment.Calender;
import sweet.home.pinerria1.Fragment.Menual;
import sweet.home.pinerria1.Fragment.Message;
import sweet.home.pinerria1.Fragment.Notification;
import sweet.home.pinerria1.Fragment.Profile;
import sweet.home.pinerria1.R;

public class MainActivitie extends AppCompatActivity {

    ImageView menual,message,alerm,calender,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        profile=findViewById(R.id.profile);
        calender=findViewById(R.id.calender);
        alerm=findViewById(R.id.alerm);
        message=findViewById(R.id.message);
        menual=findViewById(R.id.menual);

        Fragment fragment = new Profile();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, fragment).commit();


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Profile();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profilehover);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Calender();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calerdarhove);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });

        alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Notification();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notificationhover);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannual);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Message();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.messagehover);
                menual.setImageResource(R.drawable.schoolmannual);

            }
        });
        menual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Menual();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();

                profile.setImageResource(R.drawable.profile);
                calender.setImageResource(R.drawable.calendar);
                alerm.setImageResource(R.drawable.notification);
                message.setImageResource(R.drawable.message);
                menual.setImageResource(R.drawable.schoolmannualhover);

            }
        });


    }


}
