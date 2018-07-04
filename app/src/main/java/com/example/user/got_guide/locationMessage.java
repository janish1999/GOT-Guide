package com.example.user.got_guide;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class locationMessage {
    @SerializedName("message")
    String msg;
    @SerializedName("data")
    data[] data;

    public String getMsg() {
        return msg;
    }

    public data[] getData() {
        return data;
    }

    public locationMessage(String msg, data[] data) {
        this.msg = msg;
        this.data = data;
    }
}