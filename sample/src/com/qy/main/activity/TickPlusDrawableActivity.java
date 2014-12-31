package com.qy.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.qy.customview.tickplus_drawable.TickPlusDrawable;
import com.qy.main.R;

public class TickPlusDrawableActivity
        extends
        Activity
{
	@SuppressLint ("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickplusdrawable_main);
        
        View view = findViewById(R.id.view);

        final TickPlusDrawable tickPlusDrawable = new TickPlusDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), getResources().getColor(android.R.color.holo_blue_dark), Color.WHITE);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(tickPlusDrawable);
        } else {
            view.setBackground(tickPlusDrawable);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickPlusDrawable.toggle();
            }
        });
    }
}
