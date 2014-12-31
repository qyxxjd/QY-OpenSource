package com.qy.main.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.qy.customview.circular_progress_button.CircularProgressButton;
import com.qy.main.R;


public class CircularProgressButtonActivity
        extends
        Activity
{
	@InjectView(R.id.circularButton1)
	CircularProgressButton circularButton1;
	@InjectView(R.id.circularButton2)
	CircularProgressButton circularButton2;
	@InjectView(R.id.circularButton3)
	CircularProgressButton circularButton3;
	@InjectView(R.id.circularButton4)
	CircularProgressButton circularButton4;
	@InjectView(R.id.circularButton5)
	CircularProgressButton circularButton5;
	@InjectView(R.id.circularButton6)
	CircularProgressButton circularButton6;
	@InjectView(R.id.circularButton7)
	CircularProgressButton circularButton7;
	@InjectView(R.id.circularButton8)
	CircularProgressButton circularButton8;
	@InjectView(R.id.circularButton9)
	CircularProgressButton circularButton9;
	@InjectView(R.id.circularButton10)
	CircularProgressButton circularButton10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cpb_sample_main);
		ButterKnife.inject(this);
		
        circularButton1.setIndeterminateProgressMode(true);
        circularButton2.setIndeterminateProgressMode(true);
        circularButton7.setIndeterminateProgressMode(true);
        circularButton8.setIndeterminateProgressMode(true);
        circularButton9.setIndeterminateProgressMode(true);
        circularButton10.setIndeterminateProgressMode(true);
        
	}
    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }

	@OnClick({R.id.circularButton1,R.id.circularButton2,R.id.circularButton3,R.id.circularButton4,R.id.circularButton5,R.id.circularButton6,R.id.circularButton7,R.id.circularButton8,R.id.circularButton9,R.id.circularButton10})
	void clickBtn1(View v){
		switch(v.getId()){
			case R.id.circularButton1:
				if (circularButton1.getProgress() == 0) {
		            circularButton1.setProgress(50);
		        } else if (circularButton1.getProgress() == 100) {
		            circularButton1.setProgress(0);
		        } else {
		            circularButton1.setProgress(100);
		        }
				break;
			case R.id.circularButton2:
				if (circularButton2.getProgress() == 0) {
		            circularButton2.setProgress(50);
		        } else if (circularButton2.getProgress() == -1) {
		            circularButton2.setProgress(0);
		        } else {
		            circularButton2.setProgress(-1);
		        }
				break;
			case R.id.circularButton3:
				if (circularButton3.getProgress() == 0) {
                    simulateSuccessProgress(circularButton3);
                } else {
                    circularButton3.setProgress(0);
                }
				break;
			case R.id.circularButton4:
				if (circularButton4.getProgress() == 0) {
                    simulateErrorProgress(circularButton4);
                } else {
                    circularButton4.setProgress(0);
                }
				break;
			case R.id.circularButton5:
				if (circularButton5.getProgress() == 0) {
                    circularButton5.setProgress(100);
                } else {
                    circularButton5.setProgress(0);
                }
				break;
			case R.id.circularButton6:
				if (circularButton6.getProgress() == 0) {
                    circularButton6.setProgress(-1);
                } else {
                    circularButton6.setProgress(0);
                }
				break;
			case R.id.circularButton7:
				if (circularButton7.getProgress() == 0) {
                    circularButton7.setProgress(50);
                } else if (circularButton7.getProgress() == 100) {
                    circularButton7.setProgress(0);
                } else {
                    circularButton7.setProgress(100);
                }
				break;
			case R.id.circularButton8:
				if (circularButton8.getProgress() == 0) {
                    circularButton8.setProgress(50);
                } else if (circularButton8.getProgress() == -1) {
                    circularButton8.setProgress(0);
                } else {
                    circularButton8.setProgress(-1);
                }
				break;
			case R.id.circularButton9:
				if (circularButton9.getProgress() == 0) {
                    circularButton9.setProgress(50);
                } else if (circularButton9.getProgress() == 100) {
                    circularButton9.setProgress(0);
                } else {
                    circularButton9.setProgress(100);
                }
				break;
			case R.id.circularButton10:
				if (circularButton10.getProgress() == 0) {
                    circularButton10.setProgress(50);
                } else if (circularButton10.getProgress() == -1) {
                    circularButton10.setProgress(0);
                } else {
                    circularButton10.setProgress(-1);
                }
				break;
		}
	}
}
