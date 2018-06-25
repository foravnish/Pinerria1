package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import sweet.home.pinerria1.R;

public class HealthInfo extends AppCompatActivity {

    Button next,next2,next3,next4,next5;
    EditText health1,health2,health3,health4;
    EditText health12,health22,health32,health42;
    EditText health13,health23,health33,health43;
    EditText health14,health24,health34,health44;
    EditText health15,health25,health35,health45;
    LinearLayout linear1,linear2,linear3,linear4,linear5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        next=findViewById(R.id.next);

        health1=findViewById(R.id.health1);
        health2=findViewById(R.id.health2);
        health3=findViewById(R.id.health3);
        health4=findViewById(R.id.health4);


//
        linear1=findViewById(R.id.linear1);



        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HealthInfo.this, PreviousSchool.class);

                intent.putExtra("health1",health1.getText().toString());
                intent.putExtra("health2",health2.getText().toString());
                intent.putExtra("health3",health3.getText().toString());
                intent.putExtra("health4",health4.getText().toString());

                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("DOB",getIntent().getStringExtra("DOB"));
                intent.putExtra("gender",getIntent().getStringExtra("gender"));
                intent.putExtra("nationality",getIntent().getStringExtra("nationality"));
                intent.putExtra("homeAddress",getIntent().getStringExtra("homeAddress"));

                startActivity(intent);




            }
        });


//



    }
}
