package com.example.bonus;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

public class CustomSpinner extends androidx.appcompat.widget.AppCompatSpinner {

    public int counter=0;
    public CustomSpinner(Context context)
    { super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs)
    { super(context, attrs); }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle)
    { super(context, attrs, defStyle); }

    @Override
    public void setSelection(int position, boolean animate) {
        counter++;
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position, animate);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());

        }

    }

    @Override
    public void setSelection(int position) {
        counter++;
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());


        }

    }


    }

