package sweet.home.pinerria1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.MyPrefrences;

public class PreviousSchool extends AppCompatActivity {

    Button next;

    TextView DOB2;
    DatePickerDialog datePickerDialog;
    EditText childName,nationality,homeAddress2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_school);
        next=findViewById(R.id.next);
        DOB2=findViewById(R.id.DOB2);
        homeAddress2=findViewById(R.id.homeAddress2);

//        intent.putExtra("health1",health1.getText().toString());
//        intent.putExtra("health2",health2.getText().toString());
//        intent.putExtra("health3",health3.getText().toString());
//        intent.putExtra("health4",health4.getText().toString());
//
//        intent.putExtra("id",getIntent().getStringExtra("id"));
//        intent.putExtra("name",getIntent().getStringExtra("name"));
//        intent.putExtra("DOB",getIntent().getStringExtra("DOB"));
//        intent.putExtra("gender",getIntent().getStringExtra("gender"));
//        intent.putExtra("nationality",getIntent().getStringExtra("nationality"));
//        intent.putExtra("homeAddress",getIntent().getStringExtra("homeAddress"));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PreviousSchool.this,SibliingInfo.class);
                intent.putExtra("homeAddress2",homeAddress2.getText().toString());
                intent.putExtra("DOB2",DOB2.getText().toString());

                intent.putExtra("health1",getIntent().getStringExtra("health1"));
                intent.putExtra("health2",getIntent().getStringExtra("health2"));
                intent.putExtra("health3",getIntent().getStringExtra("health3"));
                intent.putExtra("health4",getIntent().getStringExtra("health4"));

                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("DOB",getIntent().getStringExtra("DOB"));
                intent.putExtra("gender",getIntent().getStringExtra("gender"));
                intent.putExtra("nationality",getIntent().getStringExtra("nationality"));
                intent.putExtra("homeAddress",getIntent().getStringExtra("homeAddress"));


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


        DOB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();

                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(PreviousSchool.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                DOB2.setText(i2 + "/" + (i1 + 1)+ "/" + i);
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

    }
}
