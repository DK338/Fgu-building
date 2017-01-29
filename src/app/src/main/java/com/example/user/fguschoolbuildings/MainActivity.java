package com.example.user.fguschoolbuildings;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private class ImagePage {
        public int imageID;
        public View page;

    }

    ImageButton ibt_01;
    Button bt1_01;
    Button bt1_02;

    private ViewPager viewPager;
    private View page1,page2,page3,page4,page5,page6,page7,page8,page9,page10,page11,page12,page13,page14,page15;
    private List<View> listpage;
    private PagerAdapter myPagerAdapter;



    final int [] image1={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,
            R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,
            R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15};
    private SQLiteDatabase sqLiteDatabase=null;
    private static final String DATABASE_NAME="font.db";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bt1_01=(Button)findViewById(R.id.bt1_01);
        bt1_02=(Button)findViewById(R.id.bt1_02);
        ibt_01=(ImageButton)findViewById(R.id.ibt1_01);

        sqLiteDatabase=this.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        String create_font_table="CREATE TABLE IF NOT EXISTS font"+"(data_id INTEGER PRIMARY KEY AUTOINCREMENT,building TEXT,text TEXT)";
        sqLiteDatabase.execSQL(create_font_table);

        ContentValues contentValues=new ContentValues(3);

        String jsonstring=getFromAssets("building.txt");

        try {
            JSONArray jsonArray=new JSONArray(jsonstring);
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                contentValues.put("data_id",jsonObject.getInt("data_id"));
                contentValues.put("building",jsonObject.getString("Builing"));
                contentValues.put("text",jsonObject.getString("Text"));

                sqLiteDatabase.insert("font",null,contentValues);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




        initView();









        ibt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);

                finish();
            }
        });



bt1_01.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,Main2Activity.class);
        startActivity(intent);

        finish();

    }
});








        bt1_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });




    }

    public void initView()
    {






        viewPager=(ViewPager)findViewById(R.id.viewpager);
        LayoutInflater Inflater=getLayoutInflater();
        page1=Inflater.inflate(R.layout.page1,null);
        page2=Inflater.inflate(R.layout.page2,null);
        page3=Inflater.inflate(R.layout.page3,null);
        page4=Inflater.inflate(R.layout.page4,null);
        page5=Inflater.inflate(R.layout.page5,null);
        page6=Inflater.inflate(R.layout.page6,null);
        page7=Inflater.inflate(R.layout.page7,null);
        page8=Inflater.inflate(R.layout.page8,null);
        page9=Inflater.inflate(R.layout.page9,null);
        page10=Inflater.inflate(R.layout.page10,null);
        page11=Inflater.inflate(R.layout.page11,null);
        page12=Inflater.inflate(R.layout.page12,null);
        page13=Inflater.inflate(R.layout.page13,null);
        page14=Inflater.inflate(R.layout.page14,null);
        page15=Inflater.inflate(R.layout.page15,null);






        listpage=new ArrayList<View>();
        listpage.add(page1);
        listpage.add(page2);
        listpage.add(page3);
        listpage.add(page4);
        listpage.add(page5);
        listpage.add(page6);
        listpage.add(page7);
        listpage.add(page8);
        listpage.add(page9);
        listpage.add(page10);
        listpage.add(page11);
        listpage.add(page12);
        listpage.add(page13);
        listpage.add(page14);
        listpage.add(page15);


        myPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return listpage.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object)
            {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {


                Toast.makeText(MainActivity.this,Integer.toString(container.getChildCount())+","+Integer.toString(position),Toast.LENGTH_SHORT).show();

              /* bt1_01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {

                        Bundle bundle=new Bundle();

                               // bundle.putInt("校舍",position-1);
                                bundle.putInt("圖",image1[position-1]);

                        Intent intent=new Intent();
                        intent.putExtras(bundle);
                        intent.setClass(MainActivity.this,Fgu1Activity.class);
                        startActivity(intent);
                    }
                });*/
                container.addView(listpage.get(position));


                return listpage.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object)
            {

                container.removeView(listpage.get(position));

            }




        };
        viewPager.setAdapter(myPagerAdapter);
    }

    public String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
}


