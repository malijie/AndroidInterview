package com.android.interview.bean;

/**
 * Created by malijie on 2017/10/19.
 */

public class TestInfo {

    private boolean checked;
    private int index;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "checked=" + checked +
                ", index=" + index +
                '}';
    }
}
