package com.example.bai_tap_mahoa;

public class Cl_toSV_dangki {
    private String mkmahoa;
    private String tendn;

    public Cl_toSV_dangki(){

    }
    public Cl_toSV_dangki(String mkmahoa, String tendn) {
        this.mkmahoa = mkmahoa;
        this.tendn = tendn;
    }

    public String getMkmahoa() {
        return mkmahoa;
    }

    public void setMkmahoa(String mkmahoa) {
        this.mkmahoa = mkmahoa;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }
}
