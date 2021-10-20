package com.example.gpproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class help extends LinearLayout {

    public help(Context context) {
        this(context, null, 0);
    }


    public help(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public help(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.help, this, true);

    }


}
