package com.example.user.got_guide;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
   @GET("characters/{name}")
   Call<message> getData(@Path("name") String name);
   @GET("characters/locations/{name}")
   Call<locationMessage> getLocation(@Path("name") String name);
   @GET("cities")
   Call<List<cities>> getCities();
   @GET("characters/?")
   Call<List<data>> getAllinfo();
}