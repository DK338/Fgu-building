package com.example.user.fguschoolbuildings;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fgu1Activity extends AppCompatActivity {
    TextView tvF_01;
    ImageView img4_01;
    TextView tvF_03;
    Button btF_01;
    private SQLiteDatabase sqLiteDatabase=null;
    private static final String DATABASE_NAME="font.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgu1);
        tvF_01=(TextView)findViewById(R.id.tvF_01);
        img4_01=(ImageView)findViewById(R.id.img4_01);
        tvF_03=(TextView)findViewById(R.id.tvF_03);
        btF_01=(Button)findViewById(R.id.btF_01);

        sqLiteDatabase=this.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        tvF_03.setMovementMethod(ScrollingMovementMethod.getInstance());

        Bundle bundle=getIntent().getExtras();
        img4_01.setImageResource(bundle.getInt("圖"));
        final int number=bundle.getInt("校舍");


        Cursor cursor=sqLiteDatabase.rawQuery("select * from font",null);

        cursor.moveToFirst();
        cursor.moveToPosition(number);

        final String Building= cursor.getString(1);
        final String Text=cursor.getString(2);

        tvF_01.setText(Building);
        tvF_03.setText(Text);


        /*Cursor cursor=sqLiteDatabase.rawQuery("select * from font",null);

        cursor.moveToPosition(number);

        final String Building= cursor.getString(1);
        final String Text=cursor.getString(2);*/



        //當文字欄被點擊，跳出彈跳式視窗。
        tvF_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(Fgu1Activity.this);
                ab.setTitle("確認").setMessage("是否編輯文字").setCancelable(true).setPositiveButton("編輯",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle bundle1=new Bundle();
                        bundle1.putInt("number",number);
                        bundle1.putString("B",Building);
                        bundle1.putString("T",Text);

                        Intent intent=new Intent();
                        intent.putExtras(bundle1);
                        intent.setClass(Fgu1Activity.this,FontActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("返回",null).show();
            }
        });




        btF_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public String getFromAssets(String fileName)
    {
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line="" ;
            String result="";
            while ((line=bufferedReader.readLine())!=null)
                result+=line;
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }


    }

}
