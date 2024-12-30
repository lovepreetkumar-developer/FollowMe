package com.techeytech.followme.beans.response_beans;

public class FeedBean {


    private String name;
    private String description;
    private int index;
    private int likes;

    public FeedBean() {

    }

    public FeedBean(String name, String description, int index, int likes) {
        this.name = name;
        this.description = description;
        this.index = index;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
