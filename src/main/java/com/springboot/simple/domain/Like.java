package com.springboot.simple.domain;

public class Like {
    private int id;
    private int reply_id;
    private String address;
    private int liked;
    private int change_time;
    private int add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getChange_time() {
        return change_time;
    }

    public void setChange_time(int change_time) {
        this.change_time = change_time;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", reply_id=" + reply_id +
                ", address='" + address + '\'' +
                ", liked=" + liked +
                ", change_time=" + change_time +
                ", add_time=" + add_time +
                '}';
    }
}
