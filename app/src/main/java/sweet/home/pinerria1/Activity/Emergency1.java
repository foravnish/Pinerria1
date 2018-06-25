package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.pinerria1.R;

public class Emergency1 extends AppCompatActivity {

    Button next;
    EditText motherMob,fatherMob,person1,personMob,personRelation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency1);

        next=findViewById(R.id.next);
        personRelation=findViewById(R.id.personRelation);
        personMob=findViewById(R.id.personMob);
        person1=findViewById(R.id.person1);
        fatherMob=findViewById(R.id.fatherMob);
        motherMob=findViewById(R.id.motherMob);

        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        intent.putExtra("childData",getIntent().getStringExtra("childData"));
//        intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
//        intent.putExtra("motherData",getIntent().getStringExtra("motherData"));
//        intent.putExtra("language",getIntent().getStringExtra("language"));
//        intent.putExtra("maritalStatus",getIntent().getStringExtra("maritalStatus"));
//        intent.putExtra("reason",getIntent().getStringExtra("reason"));
//        intent.putExtra("childData",getIntent().getStringExtra("childData"));
//        intent.putExtra("driver",getIntent().getStringExtra("driver"));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                Intent intent =new Intent(Emergency1.this,Emergency2.class);


                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
                intent.putExtra("motherData",getIntent().getStringExtra("motherData"));
                intent.putExtra("language",getIntent().getStringExtra("language"));
                intent.putExtra("maritalStatus",getIntent().getStringExtra("maritalStatus"));
                intent.putExtra("reason",getIntent().getStringExtra("reason"));
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("driver",getIntent().getStringExtra("driver"));

                intent.putExtra("motherMob",motherMob.getText().toString());
                intent.putExtra("fatherMob",fatherMob.getText().toString());
                intent.putExtra("person1",person1.getText().toString());
                intent.putExtra("personMob",personMob.getText().toString());
                intent.putExtra("personRelation",personRelation.getText().toString());
                startActivity(intent);

            }
        });

    }
}
