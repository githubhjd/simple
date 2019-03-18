package com.springboot.simple.domain;

public class Col {
    private String col_post_type;
    private String address;
    private int col_time;

    public String getCol_post_type() {
        return col_post_type;
    }

    public void setCol_post_type(String col_post_type) {
        this.col_post_type = col_post_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCol_time() {
        return col_time;
    }

    public void setCol_time(int col_time) {
        this.col_time = col_time;
    }

    @Override
    public String toString() {
        return "Col{" +
                "col_post_type='" + col_post_type + '\'' +
                ", address='" + address + '\'' +
                ", col_time=" + col_time +
                '}';
    }
}
