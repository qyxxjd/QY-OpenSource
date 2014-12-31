package com.qy.main.activity;

import android.app.Activity;
import android.os.Bundle;

import com.qy.customview.titanic.Titanic;
import com.qy.customview.titanic.TitanicTextView;
import com.qy.main.R;

public class TitanicActivity
        extends
        Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titanic_main);
        TitanicTextView tv = (TitanicTextView) findViewById(R.id.titanic_tv);
        
        // start animation
        new Titanic().start(tv);
    }
}
