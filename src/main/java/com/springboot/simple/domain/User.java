package com.springboot.simple.domain;

public class User {
    private String username;
    private String password;
    private String address;
    private int grade_time;
    private String grade_name;
    private String personal_note;
    private String profile_photo;
    private int add_time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public int getGrade_time() {
        return grade_time;
    }

    public void setGrade_time(int grade_time) {
        this.grade_time = grade_time;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getPersonal_note() {
        return personal_note;
    }

    public void setPersonal_note(String personal_note) {
        this.personal_note = personal_note;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", grade_time=" + grade_time +
                ", grade_name='" + grade_name + '\'' +
                ", personal_note='" + personal_note + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", add_time=" + add_time +
                '}';
    }
}
