package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.pinerria1.R;

public class MotherInfo extends AppCompatActivity {

    Button next;
    EditText motherName,mobile,education,ocption,pow,workNo,emailId,homeAddress;
    JSONObject cartItemsObjedct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_info);
        next=findViewById(R.id.next);
        homeAddress=findViewById(R.id.homeAddress);
        emailId=findViewById(R.id.emailId);
        workNo=findViewById(R.id.workNo);
        pow=findViewById(R.id.pow);
        ocption=findViewById(R.id.ocption);
        education=findViewById(R.id.education);
        mobile=findViewById(R.id.mobile);
        motherName=findViewById(R.id.motherName);

        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d("sdfsdfsdgfsdg",getIntent().getStringExtra("fatherData"));


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    // for (int i = 0; i< 1; i++) {
                    cartItemsObjedct = new JSONObject();
                    cartItemsObjedct.putOpt("address",homeAddress.getText().toString());
                    cartItemsObjedct.putOpt("education", education.getText().toString());
                    cartItemsObjedct.putOpt("emailId", emailId.getText().toString());
                    cartItemsObjedct.putOpt("mobileNumber", mobile.getText().toString());
                    cartItemsObjedct.putOpt("name", motherName.getText().toString());
                    cartItemsObjedct.putOpt("occupation", ocption.getText().toString());
                    cartItemsObjedct.putOpt("officeNumber", workNo.getText().toString());
                    cartItemsObjedct.putOpt("workPlace", pow.getText().toString());

                    Log.d("sdfsdfsdfdfdfddfsdf",cartItemsObjedct.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent intent=new Intent(MotherInfo.this,OtherInfo.class);
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
                intent.putExtra("motherData",cartItemsObjedct.toString());

                startActivity(intent);

            }
        });


    }
}
