package com.example.user.fguschoolbuildings;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    ImageButton ibt2_01;
    Button bt2_01;
    ListView lv2_01;
    private int _imageID = 0;
    final int [] image1={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,
            R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,
            R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15};
    private SQLiteDatabase sqLiteDatabase=null;
    private static final String DATABASE_NAME="font.db";

    public Main2Activity(SQLiteDatabase SQL, int imageID)
    {
        if (SQL == null)
        {

        }
        sqLiteDatabase = SQL;
        _imageID = imageID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ibt2_01=(ImageButton)findViewById(R.id.ibt2_01);
        bt2_01=(Button)findViewById(R.id.bt2_01);
        lv2_01=(ListView)findViewById(R.id.lv2_01);

        sqLiteDatabase=this.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        final String build[]=getResources().getStringArray(R.array.校舍);

       lv2_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
           {
                    Bundle bundle=new Bundle();
                    bundle.putInt("校舍",i);
                    bundle.putInt("圖",image1[i]);

               Intent intent=new Intent();
               intent.putExtras(bundle);
               intent.setClass(Main2Activity.this,Fgu1Activity.class);
               startActivity(intent);
           }
       });

        ibt2_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt2_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





    }
}
