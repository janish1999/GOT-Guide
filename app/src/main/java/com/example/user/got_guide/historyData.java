package com.example.user.got_guide;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class historyData extends AppCompatActivity {
    private MyDatabase database;
    private data Data;
    private ImageView image,location;
    private TextView Name,house,dateofBirth,dateofDeath,gender,culture,spouse,slug,pagerank,titles,books;
    private ApiInterface apiInterface;
    private locationMessage data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Name=(TextView)findViewById(R.id.Name);
        house=(TextView)findViewById(R.id.House);
        dateofBirth=(TextView)findViewById(R.id.DOB);
        dateofDeath=(TextView)findViewById(R.id.DOD);
        gender=(TextView)findViewById(R.id.Gender);
        culture=(TextView)findViewById(R.id.Cuture);
        spouse=(TextView)findViewById(R.id.Spouse);
        slug=(TextView)findViewById(R.id.Slug);
        pagerank=(TextView)findViewById(R.id.PageRank);
        titles=(TextView)findViewById(R.id.Titles);
        books=(TextView)findViewById(R.id.Books);
        image=(ImageView)findViewById(R.id.TitleImage);
        location=(ImageView)findViewById(R.id.location);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        database= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"GotBase").allowMainThreadQueries().build();
        Data = database.dataAccessObject().getHistory(name);
        location.setVisibility(View.VISIBLE);
        Name.setText(Data.getName());
        house.setText(Data.getHouse());
        String Url="https://api.got.show/" + Data.getImageLink();
        Picasso.get().load(Url).placeholder(R.drawable.loading).error(R.drawable.oops).into(image);
        dateofBirth.setText("Date of Birth : " + String.valueOf(Data.getDateOfBirth()));
        dateofDeath.setText("Date of Death : " + String.valueOf(Data.getDateofDeath()));
        culture.setText("Culture : " + Data.getCulture());
        spouse.setText("Spouse : " + Data.getSpouse());
        slug.setText("Slug : " + Data.getSlug());
        pagerank.setText("Page Rank : " + Float.toString(Data.getPageRank()));
        boolean Gender = Data.isMale();
        if (Gender)
            gender.setText("Gender : Male");
        else
            gender.setText("Gender : Female");
        books.setText("Books : " + Arrays.toString(Data.getBooks()));
        titles.setText("Titles : " + Arrays.toString(Data.getTitles()));
        apiInterface=apiClient.getApiCLient().create(ApiInterface.class);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(historyData.this, "",
                        "Loading. Please wait...", true);
                final Call<locationMessage> dataCall=apiInterface.getLocation(Data.getName());
                dataCall.enqueue(new Callback<locationMessage>() {
                    @Override
                    public void onResponse(@NonNull Call<locationMessage> call, @NonNull Response<locationMessage> response) {
                        data=response.body();
                        assert data != null;
                        data[] location=data.getData();
                        String[] locate=location[0].getLocations();
                        Intent in=new Intent(historyData.this,Location.class);
                        in.putExtra("Locations",locate);
                        startActivity(in);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<locationMessage> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        t.printStackTrace();

                    }
                });
            }
        });

    }
}
