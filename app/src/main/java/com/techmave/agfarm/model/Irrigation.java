package com.techmave.agfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Irrigation {

    @SerializedName("after")
    @Expose
    private Integer after;
    @SerializedName("details")
    @Expose
    private String details;

    public Integer getAfter() {
        return after;
    }

    public void setAfter(Integer after) {
        this.after = after;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}