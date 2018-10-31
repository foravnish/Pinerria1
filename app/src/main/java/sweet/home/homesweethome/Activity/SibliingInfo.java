package sweet.home.homesweethome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.MyPrefrences;

public class SibliingInfo extends AppCompatActivity {

    Button next;
    EditText name1,name2,name3,name4,name5;
    Spinner age1,age2,age3,age4,age5;
    String[] str1 = { "Age","1", "2", "3", "4","5","6", "7", "8", "9","10","11", "12", "13", "14","15","16", "17", "18", "19","20" };
    String[] str2 = { "Age","1", "2", "3", "4","5","6", "7", "8", "9","10","11", "12", "13", "14","15","16", "17", "18", "19","20" };
    String[] str3 = { "Age","1", "2", "3", "4","5","6", "7", "8", "9","10","11", "12", "13", "14","15","16", "17", "18", "19","20" };
    String[] str4 = { "Age","1", "2", "3", "4","5","6", "7", "8", "9","10","11", "12", "13", "14","15","16", "17", "18", "19","20" };
    String[] str5 = { "Age","1", "2", "3", "4","5","6", "7", "8", "9","10","11", "12", "13", "14","15","16", "17", "18", "19","20" };
    LinearLayout spi1,spi2,spi3,spi4,spi5;
    public  static JSONObject child1,child2,child3,child4,child5,child6,child7,child8,child9,child10;
    String sibVal;
    JSONArray jsonArray;
    JSONArray finalCartItemsArray2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sibliing_info);
        next=findViewById(R.id.next);

        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        name3=findViewById(R.id.name3);
        name4=findViewById(R.id.name4);
        name5=findViewById(R.id.name5);

        age1=findViewById(R.id.age1);
        age2=findViewById(R.id.age2);
        age3=findViewById(R.id.age3);
        age4=findViewById(R.id.age4);
        age5=findViewById(R.id.age5);

        spi1=findViewById(R.id.spi1);
        spi2=findViewById(R.id.spi2);
        spi3=findViewById(R.id.spi3);
        spi4=findViewById(R.id.spi4);
        spi5=findViewById(R.id.spi5);

        Log.d("dsfsdgsdgdfgdfg",MyPrefrences.getNoOfChild(getApplicationContext()));




        Log.d("homeAddress2",getIntent().getStringExtra("homeAddress2"));
        Log.d("DOB2",getIntent().getStringExtra("DOB2"));

        Log.d("health1",getIntent().getStringExtra("health1"));
        Log.d("health2",getIntent().getStringExtra("health2"));
        Log.d("health3",getIntent().getStringExtra("health3"));
        Log.d("health4",getIntent().getStringExtra("health4"));

        Log.d("id",getIntent().getStringExtra("id"));
        Log.d("name",getIntent().getStringExtra("name"));
//        Log.d("DOB",getIntent().getStringExtra("DOB"));
        Log.d("gender",getIntent().getStringExtra("gender"));
        Log.d("nationality",getIntent().getStringExtra("nationality"));
        Log.d("homeAddress",getIntent().getStringExtra("homeAddress"));


        Button textBack2=findViewById(R.id.textBack2);
        textBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsonArray=new JSONArray();
                JSONObject jsonObject1=new JSONObject();
                JSONObject jsonObject2=new JSONObject();
                JSONObject jsonObject3=new JSONObject();
                JSONObject jsonObject4=new JSONObject();
                JSONObject jsonObject5=new JSONObject();

                try {
                    Log.d("sdfsdfsdfssdfsd", String.valueOf(age1.getSelectedItem().toString()));


                    jsonObject1.putOpt("age",age1.getSelectedItem().toString());
                    jsonObject1.putOpt("name",name1.getText().toString());

                    jsonObject2.putOpt("age",age2.getSelectedItem().toString());
                    jsonObject2.putOpt("name",name2.getText().toString());

                    jsonObject3.putOpt("age",age3.getSelectedItem().toString());
                    jsonObject3.putOpt("name",name3.getText().toString());

                    jsonObject4.putOpt("age",age4.getSelectedItem().toString());
                    jsonObject4.putOpt("name",name4.getText().toString());

                    jsonObject5.putOpt("age",age5.getSelectedItem().toString());
                    jsonObject5.putOpt("name",name5.getText().toString());

                    jsonArray.put(jsonObject1);
                    jsonArray.put(jsonObject2);
                    jsonArray.put(jsonObject3);
                    jsonArray.put(jsonObject4);
                    jsonArray.put(jsonObject5);


                    Log.d("sdfsdfsdfsdfsdfs",jsonArray.toString());
                  //  sibVal=jsonArray.toString().replaceAll("\\\\","");

                    finalCartItemsArray2 = jsonArray;


                } catch (JSONException e) {
                    e.printStackTrace();
                }



                JSONArray cartItemsArray = null;

                try {

                    cartItemsArray = new JSONArray();
                    JSONObject cartItemsObjedct;
                    // for (int i = 0; i< 1; i++) {
                    cartItemsObjedct = new JSONObject();
                    cartItemsObjedct.putOpt("_id", getIntent().getStringExtra("id"));
                    cartItemsObjedct.putOpt("address", getIntent().getStringExtra("homeAddress"));
                    cartItemsObjedct.putOpt("childAllergy", getIntent().getStringExtra("health1"));
                    cartItemsObjedct.putOpt("childHealth", getIntent().getStringExtra("health2"));
                    cartItemsObjedct.putOpt("childNote", getIntent().getStringExtra("health3"));
                    cartItemsObjedct.putOpt("childProblems", getIntent().getStringExtra("health4"));
                    cartItemsObjedct.putOpt("classes", getIntent().getStringExtra("classes"));
                    cartItemsObjedct.putOpt("dob", getIntent().getStringExtra("DOB"));
                    cartItemsObjedct.putOpt("gender", getIntent().getStringExtra("gender"));
                    cartItemsObjedct.putOpt("image", getIntent().getStringExtra("image"));
                    cartItemsObjedct.putOpt("name", getIntent().getStringExtra("name"));
                    cartItemsObjedct.putOpt("nationality", getIntent().getStringExtra("nationality"));
                    cartItemsObjedct.putOpt("previousSchoolAttended", getIntent().getStringExtra("DOB2"));
                    cartItemsObjedct.putOpt("previousSchoolDetail", getIntent().getStringExtra("homeAddress2"));
                    cartItemsObjedct.putOpt("siblingDetail",finalCartItemsArray2);

                    Log.d("id",getIntent().getStringExtra("id"));
                    Log.d("name",getIntent().getStringExtra("name"));
                    //Log.d("DOB",getIntent().getStringExtra("DOB"));
                    Log.d("gender",getIntent().getStringExtra("gender"));
                    Log.d("nationality",getIntent().getStringExtra("nationality"));
                    Log.d("homeAddress",getIntent().getStringExtra("homeAddress"));


                    if (ChildList.val1==1) {
                        cartItemsArray.put(cartItemsObjedct);
                        child1=cartItemsObjedct;
                        Log.d("sdfsdfsdfsdgsdg1", cartItemsArray.toString());

                    }
                    else if (ChildList.val1==2) {

                        cartItemsArray.put(child1);
                        child2=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg2", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==3) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        child3=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg3", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==4) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        child4=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg4", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==5) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        child5=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg5", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==6) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        cartItemsArray.put(child5);
                        child6=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg6", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==7) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        cartItemsArray.put(child5);
                        cartItemsArray.put(child6);
                        child7=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg7", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==8) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        cartItemsArray.put(child5);
                        cartItemsArray.put(child6);
                        cartItemsArray.put(child7);
                        child8=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg8", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==9) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        cartItemsArray.put(child5);
                        cartItemsArray.put(child6);
                        cartItemsArray.put(child7);
                        cartItemsArray.put(child8);
                        child9=cartItemsObjedct;
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg9", cartItemsArray.toString());
                    }
                    else if (ChildList.val1==10) {

                        cartItemsArray.put(child1);
                        cartItemsArray.put(child2);
                        cartItemsArray.put(child3);
                        cartItemsArray.put(child4);
                        cartItemsArray.put(child5);
                        cartItemsArray.put(child6);
                        cartItemsArray.put(child7);
                        cartItemsArray.put(child8);
                        cartItemsArray.put(child9);
                        cartItemsArray.put(cartItemsObjedct);

                        Log.d("sdfsdfsdfsdgsdg10", cartItemsArray.toString());
                    }


                    //  dataObj.put("productArr", cartItemsArray);
                } catch (JSONException e) { // TODO Auto-generated catch bloc
                    e.printStackTrace();
                }

                // params.put("productArr",cartItemsArray.toString());


                final JSONArray finalCartItemsArray = cartItemsArray;



                if (MyPrefrences.getNoOfChild(getApplicationContext()).equalsIgnoreCase(String.valueOf(ChildList.val1))){
                    Intent intent=new Intent(SibliingInfo.this,ParentFirstScr.class);
                    intent.putExtra("childData", finalCartItemsArray.toString());
                    startActivity(intent);

                }
                else{
                    ChildList.val1=ChildList.val1+1;
                    startActivity(new Intent(SibliingInfo.this,ChildList.class));
                }


//                Intent intent=new Intent(SibliingInfo.this,ChildInfo.class);
//                intent.putExtra("value", MyPrefrences.getNoOfChild(getApplicationContext()));
//                startActivity(intent);
            }
        });
        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        ArrayAdapter subcat1 = new ArrayAdapter(SibliingInfo.this,R.layout.simple_spinner_item,str1);
        subcat1.setDropDownViewResource(R.layout.simple_spinner_item);
        age1.setAdapter(subcat1);

        ArrayAdapter subcat2 = new ArrayAdapter(SibliingInfo.this,R.layout.simple_spinner_item,str2);
        subcat2.setDropDownViewResource(R.layout.simple_spinner_item);
        age2.setAdapter(subcat2);

        ArrayAdapter subcat3 = new ArrayAdapter(SibliingInfo.this,R.layout.simple_spinner_item,str3);
        subcat3.setDropDownViewResource(R.layout.simple_spinner_item);
        age3.setAdapter(subcat3);

        ArrayAdapter subcat4= new ArrayAdapter(SibliingInfo.this,R.layout.simple_spinner_item,str4);
        subcat4.setDropDownViewResource(R.layout.simple_spinner_item);
        age4.setAdapter(subcat4);

        ArrayAdapter subcat5 = new ArrayAdapter(SibliingInfo.this,R.layout.simple_spinner_item,str5);
        subcat5.setDropDownViewResource(R.layout.simple_spinner_item);
        age5.setAdapter(subcat5);




        if (ChildList.val1==1) {

            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (ChildList.val1==2) {

            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }
        else  if (ChildList.val1==3) {

            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (ChildList.val1==4) {

            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }
        else  if (ChildList.val1==5) {

            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (ChildList.val1==6) {

            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }
        else  if (ChildList.val1==7) {

            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (ChildList.val1==8) {

            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }
        else  if (ChildList.val1==9) {

            relat.setBackgroundResource(R.drawable.redius_img_in);
        }
        else  if (ChildList.val1==10) {

            relat.setBackgroundResource(R.drawable.redius_img_in_male);
        }

    }
}
