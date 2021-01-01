package com.example.bonus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpinnerAdapter  extends BaseAdapter {
    Context context;
    Object flags[];
    Object[] countryNames;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext, Object[] flags, Object[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_samples, null);
        ImageView icon = (ImageView) view.findViewById(R.id.exampleImageView);
        TextView names = (TextView) view.findViewById(R.id.exampleTextView);
        icon.setImageResource(Integer.parseInt(flags[i].toString()));
        names.setText(countryNames[i].toString());
        return view;
    }
}