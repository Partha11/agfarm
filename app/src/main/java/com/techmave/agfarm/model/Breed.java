package com.techmave.agfarm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Breed {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}