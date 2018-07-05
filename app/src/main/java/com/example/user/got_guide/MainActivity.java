package com.example.user.got_guide;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView Search;
    private ApiInterface apiInterface;
    List<data> Data;
    List<cities> Cities;
    List<String>  Names=new ArrayList<>();
    ArrayList<String> cityName=new ArrayList<>();
    ListView listView;
    String[] nm=new String[2028];
    RelativeLayout searchbar;
    ImageView imageView,history,getCity;
    int count=0;
    public static MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbar=(RelativeLayout)findViewById(R.id.relay1);
        imageView=(ImageView)findViewById(R.id.imageView2);
        history=(ImageView)findViewById(R.id.history);
        getCity=(ImageView)findViewById(R.id.getCity);
        database= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"GotBase").allowMainThreadQueries().build();
        if(doesDatabaseExist(this,"GotBase")) {
            Names = database.dataAccessObject().getAllName();
        }
        apiInterface=apiClient.getApiCLient().create(ApiInterface.class);
        final Call<List<data>> dataCall=apiInterface.getAllinfo();
        dataCall.enqueue(new Callback<List<data>>() {
            @Override
            public void onResponse(Call<List<data>> call, Response<List<data>> response) {
                Data = response.body();
                for (int i = 0; i < Data.size(); i++) {
                    String name = Data.get(i).getName();
                    if(name!=null)
                        nm[i]=name;
                }
                Log.i("Size", String.valueOf(Data.size()));
            }

            @Override
            public void onFailure(Call<List<data>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(4000);
        imageView.startAnimation(in);


        Search = (AutoCompleteTextView) findViewById(R.id.search);
        new CountDownTimer(4000,10){

            @Override
            public void onFinish() {
                searchbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTick(long millisUntilFinished) {


            }
        }.start();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nm);
        Search.setThreshold(2);
        Search.setAdapter(arrayAdapter);
        getCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                        "Loading. Please wait...", true);
                final Call<List<cities>> cityCall=apiInterface.getCities();
                cityCall.enqueue(new Callback<List<cities>>() {
                    @Override
                    public void onResponse(Call<List<cities>> call, Response<List<cities>> response) {
                        Cities=response.body();
                        for(int i=0;i<Cities.size();i++)
                            cityName.add(Cities.get(i).getCities());
                        Intent ci=new Intent(MainActivity.this,AllCities.class);
                        ci.putStringArrayListExtra("CityName",cityName);
                        startActivity(ci);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<cities>> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Names=database.dataAccessObject().getAllName();
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (Search.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        int flag=0;
                        for(int i=0;i<Names.size();i++){
                            if(Search.getText().toString().equals(Names.get(i)))
                            {
                                //Toast.makeText(getApplicationContext(),"From Database",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,historyData.class);
                                intent.putExtra("name",Search.getText().toString());
                                startActivity(intent);
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            //Toast.makeText(getApplicationContext(),"From API",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Details.class);
                    intent.putExtra("Name", Search.getText().toString());
                    startActivity(intent);
                }}}
                return false;
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Names=database.dataAccessObject().getAllName();
                Intent intent=new Intent(MainActivity.this,history.class);
                intent.putStringArrayListExtra("Names",(ArrayList<String>)Names);
                startActivity(intent);
            }
        });
    Search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Names=database.dataAccessObject().getAllName();
            int flag=0;
            for(int i=0;i<Names.size();i++){
                if(Search.getText().toString().equals(Names.get(i)))
                {
                    //Toast.makeText(getApplicationContext(),"From Database",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,historyData.class);
                    intent.putExtra("name",Search.getText().toString());
                    startActivity(intent);
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                //Toast.makeText(getApplicationContext(),"From API",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("Name", Search.getText().toString());
                startActivity(intent);
        }
    }
    });
    }
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

}
    /* listView=(ListView)findViewById(R.id.list);
        adapter=new ListViewAdapter(this,android.R.layout.simple_dropdown_item_1line);
        Search.setAdapter(adapter);

                Search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        String typing=Search.getText().toString().toLowerCase(Locale.getDefault());
                        adapter.myFilter(typing);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });*/