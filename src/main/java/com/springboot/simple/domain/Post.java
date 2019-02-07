package com.springboot.simple.domain;

public class Post {
    private String post_type;
    private String title;
    private String main_spec;
    private String post_bounty;
    private int add_time;

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMain_spec() {
        return main_spec;
    }

    public void setMain_spec(String main_spec) {
        this.main_spec = main_spec;
    }

    public String getPost_bounty() {
        return post_bounty;
    }

    public void setPost_bounty(String post_bounty) {
        this.post_bounty = post_bounty;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }
}
