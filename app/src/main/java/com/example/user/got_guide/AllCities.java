package com.example.user.got_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AllCities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cities);
        ListView listView;
        ArrayAdapter<String> arrayAdapter;
        List<String> list=new ArrayList<String>();
        final Intent intent=getIntent();
        list=intent.getStringArrayListExtra("CityName");
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        arrayAdapter.notifyDataSetChanged();
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
    }
}
