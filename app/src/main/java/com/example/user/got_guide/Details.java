package com.example.user.got_guide;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {

    private ApiInterface apiInterface;
    private message Data;
    private locationMessage data;
    private ImageView image,location;
    private TextView Name,house,dateofBirth,dateofDeath,gender,culture,spouse,slug,pagerank,titles,books;
    public static MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");
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
        final RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Main);
        final LinearLayout layout=(LinearLayout)findViewById(R.id.noName);
        apiInterface=apiClient.getApiCLient().create(ApiInterface.class);
        database= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"GotBase").allowMainThreadQueries().build();
        final Call<message> dataCall=apiInterface.getData(name);//
        dataCall.enqueue(new Callback<message>() {
            @Override
            public void onResponse(@NonNull Call<message> call, @NonNull Response<message> response) {
                Data =response.body();
                try{
                    layout.setVisibility(View.INVISIBLE);
                    location.setVisibility(View.VISIBLE);
                    Name.setText(Data.getData().getName());
                    house.setText(Data.getData().getHouse());
                    String Url="https://api.got.show/" + Data.getData().getImageLink();
                    Picasso.get().load(Url).placeholder(R.drawable.loading).error(R.drawable.oops).into(image);
                    dateofBirth.setText("Date of Birth : " + String.valueOf(Data.getData().getDateOfBirth()));
                    dateofDeath.setText("Date of Death : " + String.valueOf(Data.getData().getDateofDeath()));
                    culture.setText("Culture : " + Data.getData().getCulture());
                    spouse.setText("Spouse : " + Data.getData().getSpouse());
                    slug.setText("Slug : " + Data.getData().getSlug());
                    pagerank.setText("Page Rank : " + Float.toString(Data.getData().getPageRank()));
                    boolean Gender = Data.getData().isMale();
                    if (Gender)
                        gender.setText("Gender : Male");
                    else
                        gender.setText("Gender : Female");
                    books.setText("Books : " + Arrays.toString(Data.getData().getBooks()));
                    titles.setText("Titles : " + Arrays.toString(Data.getData().getTitles()));
                    DataManager manager=new DataManager();
                    manager.setName(Data.getData().getName());
                    manager.setHouse(Data.getData().getHouse());
                    manager.setImageLink(Data.getData().getImageLink());
                    manager.setDateOfBirth(Data.getData().getDateOfBirth());
                    manager.setDateofDeath(Data.getData().getDateofDeath());
                    manager.setCulture(Data.getData().getCulture());
                    manager.setSpouse(Data.getData().getSpouse());
                    manager.setSlug(Data.getData().getSlug());
                    manager.setPageRank(Data.getData().getPageRank());
                    manager.setMale(Data.getData().isMale());
                    manager.setBooks(Data.getData().getBooks());
                    manager.setTitles(Data.getData().getTitles());
                    database.dataAccessObject().insertData(manager);
                }catch (NullPointerException e){
                    layout.setVisibility(View.VISIBLE);
                }}


            @Override
            public void onFailure(Call<message> call, Throwable t) {
                t.printStackTrace();
                layout.setVisibility(View.VISIBLE);
            }
        });
    location.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ProgressDialog dialog = ProgressDialog.show(Details.this, "",
                    "Loading. Please wait...", true);
            final Call<locationMessage> dataCall=apiInterface.getLocation(Data.getData().getName());
            dataCall.enqueue(new Callback<locationMessage>() {
                @Override
                public void onResponse(@NonNull Call<locationMessage> call,@NonNull Response<locationMessage> response) {
                    data=response.body();
                    assert data != null;
                    data[] location=data.getData();
                    Intent in=new Intent(Details.this,Location.class);
                    String[] locate=location[0].getLocations();
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
