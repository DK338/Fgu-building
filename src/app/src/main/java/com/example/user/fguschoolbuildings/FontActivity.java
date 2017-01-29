package com.example.user.fguschoolbuildings;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FontActivity extends AppCompatActivity {
    Button bt4_01;
    Button bt4_02;
    EditText ed4_01;
    TextView tv4_01;

    final int [] image1={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,
            R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,
            R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15};

    private SQLiteDatabase sqLiteDatabase=null;
    private static final String DATABASE_NAME="font.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        bt4_01=(Button)findViewById(R.id.bt4_01);
        bt4_02=(Button)findViewById(R.id.bt4_02);
        ed4_01=(EditText)findViewById(R.id.ed4_01);
        tv4_01=(TextView)findViewById(R.id.tv4_01);

        sqLiteDatabase=this.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);


        ed4_01.setMovementMethod(ScrollingMovementMethod.getInstance());

        final Bundle bundle=getIntent().getExtras();
        final int number=bundle.getInt("number");




        final Bundle bundle1=new Bundle();
        bundle1.putInt("校舍",number);
        bundle1.putInt("圖",image1[number]);
        tv4_01.setText(bundle.getString("B"));
        ed4_01.setText(bundle.getString("T"));

        bt4_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues=new ContentValues();

                contentValues.put("building",bundle.getString("B"));
                contentValues.put("text", String.valueOf(ed4_01.getText()));


                sqLiteDatabase.update("font",contentValues,"data_id="+number,null);


                Intent intent=new Intent();
                intent.putExtras(bundle1);

                intent.setClass(FontActivity.this,Fgu1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        bt4_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtras(bundle1);
                intent.setClass(FontActivity.this,Fgu1Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }




}
