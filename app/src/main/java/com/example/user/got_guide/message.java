package com.example.user.got_guide;

import com.google.gson.annotations.SerializedName;

public class message {
    @SerializedName("message")
    String msg;
    @SerializedName("data")
    data data;

    public String getMsg() {
        return msg;
    }

    public com.example.user.got_guide.data getData() {
        return data;
    }

    public message(String msg, com.example.user.got_guide.data data) {

        this.msg = msg;
        this.data = data;
    }
}
