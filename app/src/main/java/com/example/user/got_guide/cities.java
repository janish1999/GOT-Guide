package com.example.user.got_guide;

import com.google.gson.annotations.SerializedName;

public class cities {
    @SerializedName("id")
    int Id;
    @SerializedName("name")
    String cities;

    public cities(int id, String cities) {
        Id = id;
        this.cities = cities;
    }

    public int getId() {
        return Id;
    }

    public String getCities() {
        return cities;
    }
}
