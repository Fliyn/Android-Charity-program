package com.example.FliynProjesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    Button button;
    Button button2;
    DBHelper DB;
    TextView Okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button=(Button) findViewById(R.id.button7);
        button2=(Button) findViewById(R.id.button8);
        Okay=(TextView) findViewById(R.id.textView11);

        ListView listView=(ListView)findViewById(R.id.barbra);
        DB=new DBHelper(this);

        ArrayList<String> thelist =new ArrayList<>();
        Cursor data=DB.datataking();

        if(data.getCount()==0){
            Toast.makeText(MainActivity3.this, "HMMM.....", Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                thelist.add(data.getString(0));
                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        Cursor rocklee= DB.bombaparahesap("Anonim Bağışçı");

        if (rocklee.moveToFirst() ) {
            Okay.setText(rocklee.getString(rocklee.getColumnIndex("para")));
        }else {
            Okay.setText("Data Bulunamadı!?!");
        }
        rocklee.close();
    }
}