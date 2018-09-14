package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.homesweethome.R;

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

                if (validate()) {
                    try {


                        cartItemsObjedct = new JSONObject();
                        cartItemsObjedct.putOpt("name", driverName.getText().toString());
                        cartItemsObjedct.putOpt("mobileNumber", MobileNo2.getText().toString());


                        Log.d("sdfsdfsdfdfdfddfsdf", cartItemsObjedct.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(OtherPermited.this, Emergency.class);
                    intent.putExtra("childData", getIntent().getStringExtra("childData"));
                    intent.putExtra("fatherData", getIntent().getStringExtra("fatherData"));
                    intent.putExtra("motherData", getIntent().getStringExtra("motherData"));
                    intent.putExtra("language", getIntent().getStringExtra("language"));
                    intent.putExtra("maritalStatus", getIntent().getStringExtra("maritalStatus"));
                    intent.putExtra("reason", getIntent().getStringExtra("reason"));
                    intent.putExtra("driver", cartItemsObjedct.toString());

                    startActivity(intent);
                }
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

    private boolean validate(){

        if (TextUtils.isEmpty(driverName.getText().toString()))
        {
            driverName.setError("Oops! Driver Name blank");
            driverName.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(MobileNo2.getText().toString()))
        {
            MobileNo2.setError("Oops! Driver Mobile No blank");
            MobileNo2.requestFocus();
            return false;
        }
//        else if (TextUtils.isEmpty(health3.getText().toString()))
//        {
//            health3.setError("Oops! Empty Field 3 blank");
//            health3.requestFocus();
//            return false;
//        }
//        else if (TextUtils.isEmpty(health4.getText().toString()))
//        {
//            health4.setError("Oops! Empty Field 4 blank");
//            health4.requestFocus();
//            return false;
//        }

        return true;

    }


}
