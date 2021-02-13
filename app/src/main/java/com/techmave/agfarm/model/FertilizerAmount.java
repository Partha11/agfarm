package com.techmave.agfarm.model;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class FertilizerAmount {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}