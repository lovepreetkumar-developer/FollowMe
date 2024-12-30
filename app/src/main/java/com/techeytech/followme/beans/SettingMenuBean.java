package com.techeytech.followme.beans;

public class SettingMenuBean {

    private String name;
    private int icon;
    private int index;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public SettingMenuBean(String name, int icon, int index, boolean selected) {
        this.name = name;
        this.icon = icon;
        this.index = index;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
