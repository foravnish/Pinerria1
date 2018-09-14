package sweet.home.pinerria1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import sweet.home.pinerria1.R;

public class Emergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        ImageView proceed=findViewById(R.id.proceed);

//        intent.putExtra("childData",getIntent().getStringExtra("childData"));
//        intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
//        intent.putExtra("motherData",getIntent().getStringExtra("motherData"));
//        intent.putExtra("language",getIntent().getStringExtra("language"));
//        intent.putExtra("maritalStatus",getIntent().getStringExtra("maritalStatus"));
//        intent.putExtra("reason",getIntent().getStringExtra("reason"));
//        intent.putExtra("driver",cartItemsObjedct.toString());


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Emergency.this,Emergency1.class);
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("fatherData",getIntent().getStringExtra("fatherData"));
                intent.putExtra("motherData",getIntent().getStringExtra("motherData"));
                intent.putExtra("language",getIntent().getStringExtra("language"));
                intent.putExtra("maritalStatus",getIntent().getStringExtra("maritalStatus"));
                intent.putExtra("reason",getIntent().getStringExtra("reason"));
                intent.putExtra("childData",getIntent().getStringExtra("childData"));
                intent.putExtra("driver",getIntent().getStringExtra("driver"));
                startActivity(intent);
            }
        });
    }
}
