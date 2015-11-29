package com.cours644_1.maa_bom.ittrainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnaud on 29.11.2015.
 */
public class BinaryArrayAdapter extends ArrayAdapter<SelectableItem> {
    public BinaryArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    private String stateOne;
    private String stateTwo;

    public List<SelectableItem> getSelected() {
        List<SelectableItem> respons = new ArrayList<SelectableItem>();
        SelectableItem current;
        int size = getCount();
        for (int cpt = 0; cpt < size; ++cpt) {
            current = getItem(cpt);
            if (current.isSelected())
                respons.add(current);
        }
        return respons;
    }

    public BinaryArrayAdapter(Context context, SelectableItem[] items, String firstState, String secondState) {
        super(context, 0, items);
        this.stateOne = firstState;
        this.stateTwo = secondState;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SelectableItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.binary_element_in_list, parent, false);
        }

        TextView state = (TextView) convertView.findViewById(R.id.binary_element_in_list_status);
        setState(state, item.isSelected());
        ((TextView) convertView.findViewById(R.id.binary_element_in_list_element)).setText(item.toString());
        convertView.setOnClickListener(new reverser(item));

        return convertView;
    }

    private void setState(TextView textView, boolean selected) {
        if (selected)
            textView.setText(stateOne);
        else
            textView.setText(stateTwo);
    }

    private class reverser implements View.OnClickListener {
        private SelectableItem item;

        private reverser(SelectableItem item) {
            this.item = item;
        }


        @Override
        public void onClick(View v) {
            item.setSelected(!item.isSelected());
            TextView state = (TextView) v.findViewById(R.id.binary_element_in_list_status);
            setState(state, item.isSelected());
        }
    }


}