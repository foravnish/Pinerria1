package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.widget.ImageView;

import sweet.home.homesweethome.R;

public class ParentFirstScr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_first_scr);
        ImageView proceed=findViewById(R.id.proceed);


        Log.d("fdsdfsdfsdfsdg",getIntent().getStringExtra("childData"));

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ParentFirstScr.this,FatherInfo.class);
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                startActivity(intent);


            }
        });

    }
}
