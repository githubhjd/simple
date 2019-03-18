package com.springboot.simple.domain;

public class Post {
    private String post_type;
    private String title;
    private String main_spec;
    private String post_bounty;
    private int add_time;
    private int status;
    private int click_num;
    private int clear_num;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public int getClear_num() {
        return clear_num;
    }

    public void setClear_num(int clear_num) {
        this.clear_num = clear_num;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_type='" + post_type + '\'' +
                ", title='" + title + '\'' +
                ", main_spec='" + main_spec + '\'' +
                ", post_bounty='" + post_bounty + '\'' +
                ", add_time=" + add_time +
                ", status=" + status +
                ", click_num=" + click_num +
                ", clear_num=" + clear_num +
                '}';
    }
}
