package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sweet.home.pinerria1.R;

public class SeelctKids extends AppCompatActivity {
    Spinner switchUser;
    String[] str = { "1", "2", "3", "4","5" };
    Button proceed;
    String val="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seelct_kids);
        switchUser=findViewById(R.id.switchUser);
        proceed=findViewById(R.id.proceed);


        ArrayAdapter subcat = new ArrayAdapter(SeelctKids.this,android.R.layout.simple_spinner_item,str);
        subcat.setDropDownViewResource(R.layout.simple_spinner_item);
        switchUser.setAdapter(subcat);


        switchUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                val=switchUser.getSelectedItem().toString();
                Log.d("dsfsfsdfsdfgs", String.valueOf(val));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SeelctKids.this,SibliingInfo.class);
                intent.putExtra("value",val);
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
