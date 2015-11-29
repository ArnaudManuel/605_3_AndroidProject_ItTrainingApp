package com.cours644_1.maa_bom.ittrainingapp;

/**
 * Created by arnaud on 29.11.2015.
 */
public class SelectableItem {
    private Object content;
    private boolean isSelected;

    public SelectableItem(Object content) {
        this.content = content;
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean true_false) {
        isSelected = true_false;
    }

    public Object getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}