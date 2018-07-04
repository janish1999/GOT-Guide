package com.example.user.got_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView listView;
        ArrayAdapter<String> arrayAdapter;
        List<String> list=new ArrayList<String>();
        final Intent intent=getIntent();
        list=intent.getStringArrayListExtra("Names");
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        arrayAdapter.notifyDataSetChanged();
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
        final List<String> finalList = list;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name= finalList.get(position);
                Intent intent1=new Intent(history.this,historyData.class);
                intent1.putExtra("name",name);
                startActivity(intent1);
            }
        });
    }
}
