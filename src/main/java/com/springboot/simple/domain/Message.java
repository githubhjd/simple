package com.springboot.simple.domain;

public class Message {

    private int id;
    private String post_type;
    private String address;
    private int clear_state;
    private int state;
    private String mention_person;
    private int add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClear_state() {
        return clear_state;
    }

    public void setClear_state(int clear_state) {
        this.clear_state = clear_state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMention_person() {
        return mention_person;
    }

    public void setMention_person(String mention_person) {
        this.mention_person = mention_person;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", post_type='" + post_type + '\'' +
                ", address='" + address + '\'' +
                ", clear_state=" + clear_state +
                ", state=" + state +
                ", mention_person='" + mention_person + '\'' +
                ", add_time=" + add_time +
                '}';
    }
}
