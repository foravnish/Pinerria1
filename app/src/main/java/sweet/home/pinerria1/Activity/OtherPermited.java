package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.pinerria1.R;

public class OtherPermited extends AppCompatActivity {

    Button next;
    EditText nanyyName,MobileNo1,driverName,MobileNo2,otherName,relationShip,MobileNo3;
    JSONObject cartItemsObjedct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_permited);

        Log.d("dsfdsfsdfsdfsd1",getIntent().getStringExtra("childData"));
        Log.d("dsfdsfsdfsdfsd2",getIntent().getStringExtra("fatherData"));
        Log.d("dsfdsfsdfsdfsd3",getIntent().getStringExtra("motherData"));
        Log.d("dsfdsfsdfsdfsd4",getIntent().getStringExtra("language"));
        Log.d("dsfdsfsdfsdfsd5",getIntent().getStringExtra("maritalStatus"));
        Log.d("dsfdsfsdfsdfsd6",getIntent().getStringExtra("reason"));

        next=findViewById(R.id.next);
        MobileNo3=findViewById(R.id.MobileNo3);
        relationShip=findViewById(R.id.relationShip);
        otherName=findViewById(R.id.otherName);
        MobileNo2=findViewById(R.id.MobileNo2);
        driverName=findViewById(R.id.driverName);
        MobileNo1=findViewById(R.id.MobileNo1);
        nanyyName=findViewById(R.id.nanyyName);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    cartItemsObjedct = new JSONObject();
                    cartItemsObjedct.putOpt("name",driverName.getText().toString());
                    cartItemsObjedct.putOpt("mobileNumber", MobileNo2.getText().toString());


                    Log.d("sdfsdfsdfdfdfddfsdf",cartItemsObjedct.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent intent=new Intent(OtherPermited.this,Emergency.class);
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
                intent.putExtra("motherData",getIntent().getStringExtra("motherData"));
                intent.putExtra("language",getIntent().getStringExtra("language"));
                intent.putExtra("maritalStatus",getIntent().getStringExtra("maritalStatus"));
                intent.putExtra("reason",getIntent().getStringExtra("reason"));
                intent.putExtra("driver",cartItemsObjedct.toString());

                startActivity(intent);
            }
        });
        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
