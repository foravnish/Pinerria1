package sweet.home.pinerria1.Activity;

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

import sweet.home.pinerria1.R;

public class OtherInfo extends AppCompatActivity {

    Button next;
    EditText language2;
    Spinner switchStatus,language;
    String[] str1 = { "Married", "Unmarried"};
    String[] str2 = { "Arabic", "English","Both"};
    String statusM,statusL;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        next=findViewById(R.id.next);
        language=findViewById(R.id.language);
        language2=findViewById(R.id.language2);
        switchStatus=findViewById(R.id.switchStatus);


        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayAdapter subcat1 = new ArrayAdapter(OtherInfo.this,android.R.layout.simple_spinner_item,str1);
        subcat1.setDropDownViewResource(R.layout.simple_spinner_item);
        switchStatus.setAdapter(subcat1);

        ArrayAdapter subcat2 = new ArrayAdapter(OtherInfo.this,android.R.layout.simple_spinner_item,str2);
        subcat2.setDropDownViewResource(R.layout.simple_spinner_item);
        language.setAdapter(subcat2);

        Log.d("dfdsgdgdfgd1",getIntent().getStringExtra("childData"));
        Log.d("dfdsgdgdfgd2",getIntent().getStringExtra("fatherData"));
        Log.d("dfdsgdgdfgd3",getIntent().getStringExtra("motherData"));


        switchStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusM=switchStatus.getSelectedItem().toString();
                if (i==0){

                    language2.setVisibility(View.GONE);
                    flag=false;
                }
                else if (i==1){
                    flag=true;
                    language2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusL=language.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    Intent intent = new Intent(OtherInfo.this, OtherPermited.class);
                    intent.putExtra("childData", getIntent().getStringExtra("childData"));
                    intent.putExtra("fatherData", getIntent().getStringExtra("fatherData"));
                    intent.putExtra("motherData", getIntent().getStringExtra("motherData"));

                    intent.putExtra("language", statusM);
                    intent.putExtra("maritalStatus", statusL);
                    intent.putExtra("reason", language2.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validate(){

//        if (TextUtils.isEmpty(language.getText().toString()))
//        {
//            language.setError("Oops! Language Spoken blank");
//            language.requestFocus();
//            return false;
//        }

        if (flag==true) {
         if (TextUtils.isEmpty(language2.getText().toString())) {
                language2.setError("Oops! Empty Field 2");
                language2.requestFocus();
                return false;
            }
        }



        return true;

    }

}
