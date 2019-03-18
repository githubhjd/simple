package com.springboot.simple.domain;

public class Reply {
    private int reply_id;
    private String post_type;
    private String reply_main_spec;
    private int state;
    private int add_time;
    private int like_num;
    private String reply_address;

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getReply_main_spec() {
        return reply_main_spec;
    }

    public void setReply_main_spec(String reply_main_spec) {
        this.reply_main_spec = reply_main_spec;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public String getReply_address() {
        return reply_address;
    }

    public void setReply_address(String reply_address) {
        this.reply_address = reply_address;
    }


    @Override
    public String toString() {
        return "Reply{" +
                "reply_id=" + reply_id +
                ", post_type='" + post_type + '\'' +
                ", reply_main_spec='" + reply_main_spec + '\'' +
                ", state=" + state +
                ", add_time=" + add_time +
                ", like_num=" + like_num +
                ", reply_address='" + reply_address + '\'' +
                '}';
    }
}
