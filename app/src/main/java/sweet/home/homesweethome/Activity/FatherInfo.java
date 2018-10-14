package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Const;
import sweet.home.homesweethome.Utils.Util;

public class FatherInfo extends AppCompatActivity {

    Button next;
    EditText fathername,mobile,education,ocption,pow,workNo,emailId,homeAddress;
    Spinner educationSpin;
    JSONObject cartItemsObjedct;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String[] str1 = {"Education *", "Graduate", "Post Graduate","Under Graduate"};
    String stringEducation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_info);
        next=findViewById(R.id.next);
        homeAddress=findViewById(R.id.homeAddress);
        emailId=findViewById(R.id.emailId);
        workNo=findViewById(R.id.workNo);
        pow=findViewById(R.id.pow);
        ocption=findViewById(R.id.ocption);
       // education=findViewById(R.id.education);
        educationSpin=findViewById(R.id.educationSpin);
        mobile=findViewById(R.id.mobile);
        fathername=findViewById(R.id.fathername);
        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Log.d("fdsdfsdfsdfsdg",getIntent().getStringExtra("childData"));

        ArrayAdapter subcat1 = new ArrayAdapter(FatherInfo.this,R.layout.simple_spinner_item,str1);
        subcat1.setDropDownViewResource(R.layout.simple_spinner_item);
        educationSpin.setAdapter(subcat1);

        educationSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stringEducation=educationSpin.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validate()) {
                    try {

                        // for (int i = 0; i< 1; i++) {
                        cartItemsObjedct = new JSONObject();
                        cartItemsObjedct.putOpt("address",homeAddress.getText().toString());
                        cartItemsObjedct.putOpt("education", stringEducation.toString());
                        cartItemsObjedct.putOpt("emailId", emailId.getText().toString());
                        cartItemsObjedct.putOpt("mobileNumber", mobile.getText().toString());
                        cartItemsObjedct.putOpt("name", fathername.getText().toString());
                        cartItemsObjedct.putOpt("occupation", ocption.getText().toString());
                        cartItemsObjedct.putOpt("officeNumber", workNo.getText().toString());
                        cartItemsObjedct.putOpt("workPlace", pow.getText().toString());

                        Log.d("sdfsdfsdfsdf",cartItemsObjedct.toString());

                        Const.setFatherMob(mobile.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Intent intent=new Intent(FatherInfo.this,MotherInfo.class);
                    intent.putExtra("fatherData",cartItemsObjedct.toString());
                    intent.putExtra("childData",getIntent().getStringExtra("childData"));
                    startActivity(intent);

                }

            }
        });

    }


    private boolean validate(){


        if (TextUtils.isEmpty(fathername.getText().toString()))
        {
            fathername.setError("Oops! Father Name field blank");
            fathername.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(mobile.getText().toString()))
        {
            mobile.setError("Oops! Mobile No field blank");
            mobile.requestFocus();
            return false;
        }
        else if (mobile.getText().length()<7)
        {
            Util.errorDialog2(FatherInfo.this,"Mobile number should be 7-15 characters");
            mobile.requestFocus();
            return false;
        }
        else if (stringEducation.equalsIgnoreCase("Education *"))
        {
//            education.setError("Oops! Education field blank");
//            education.requestFocus();
            Util.errorDialog2(FatherInfo.this,"Education field blank");
            return false;
        }
        else if (TextUtils.isEmpty(ocption.getText().toString()))
        {
            ocption.setError("Oops! Occupation field blank");
            ocption.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(pow.getText().toString()))
        {
            pow.setError("Oops! Place field blank");
            pow.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(workNo.getText().toString()))
        {
            workNo.setError("Oops! Work No. field blank");
            workNo.requestFocus();
            return false;
        }
        else if (workNo.getText().length()<7)
        {
            Util.errorDialog2(FatherInfo.this,"Mobile number should be 7-15 characters");
            workNo.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(emailId.getText().toString()))
        {
            emailId.setError("Oops! Email Id field blank");
            emailId.requestFocus();
            return false;
        }
        else if (!emailId.getText().toString().trim().matches(emailPattern))
        {
            emailId.setError("Oops! invalid email id");
            emailId.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(homeAddress.getText().toString()))
        {
            homeAddress.setError("Oops! Home Address field blank");
            homeAddress.requestFocus();
            return false;
        }



        return true;

    }

}
