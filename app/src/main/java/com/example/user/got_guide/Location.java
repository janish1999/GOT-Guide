package com.example.user.got_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class Location extends AppCompatActivity {

    TextView locate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locate=(TextView)findViewById(R.id.locate);
        Intent intent=getIntent();
        String[] data=intent.getStringArrayExtra("Locations");
        locate.setText(Arrays.toString(data));
    }
}
