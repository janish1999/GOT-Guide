package com.example.user.got_guide;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {
    public static final String BASE_URL="https://api.got.show/api/";
    public static Retrofit retrofit=null;

    public static Retrofit getApiCLient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
