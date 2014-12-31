package com.qy.main.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.qy.customview.circleprogress.ArcProgress;
import com.qy.customview.circleprogress.CircleProgress;
import com.qy.customview.circleprogress.DonutProgress;
import com.qy.main.R;


public class CircleProgressActivity
        extends
        Activity
{
	private Timer          timer;
	@InjectView(R.id.donut_progress)
	DonutProgress  donutProgress;
	@InjectView(R.id.donut_progress2)
	DonutProgress  donutProgress2;
	@InjectView(R.id.donut_progress3)
	DonutProgress  donutProgress3;
	@InjectView(R.id.donut_progress4)
	DonutProgress  donutProgress4;
	@InjectView(R.id.circle_progress)
	CircleProgress circleProgress;
	@InjectView(R.id.circle_progress2)
	CircleProgress circleProgress2;
	@InjectView(R.id.arc_progress)
	ArcProgress    arcProgress;
	@InjectView(R.id.arc_progress2)
	ArcProgress    arcProgress2;
	@InjectView(R.id.arc_progress3)
	ArcProgress    arcProgress3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circleprogress_my);
		ButterKnife.inject(this);
		
		timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run() {
				runOnUiThread(new Runnable()
				{
					@Override
					public void run() {
						donutProgress.setProgress(donutProgress.getProgress() + 1);
						donutProgress2.setProgress(donutProgress2.getProgress() + 1);
						donutProgress3.setProgress(donutProgress3.getProgress() + 1);
						donutProgress4.setProgress(donutProgress4.getProgress() + 1);
						circleProgress.setProgress(circleProgress.getProgress() + 1);
						circleProgress2.setProgress(circleProgress2.getProgress() + 1);
						arcProgress.setProgress(arcProgress.getProgress() + 1);
						arcProgress2.setProgress(arcProgress2.getProgress() + 1);
						arcProgress3.setProgress(arcProgress3.getProgress() + 1);
					}
				});
			}
		}, 1000, 100);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}
}
