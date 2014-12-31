package com.qy.main.activity;

import android.app.Activity;
import android.os.Bundle;

import com.qy.customview.collage_views.MultiTouchListener;
import com.qy.main.R;

public class CollageViewActivity
        extends
        Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collageviews_main);
        findViewById(R.id.collageviews_img).setOnTouchListener(new MultiTouchListener());
    }
}
