package com.techeytech.followme.beans;

public class InterestBean {

    private String name;
    private int index;
    private boolean selected;

    public InterestBean(String name, int index, boolean selected) {
        this.name = name;
        this.index = index;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
