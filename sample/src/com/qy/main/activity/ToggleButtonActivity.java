package com.qy.main.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.qy.customview.toggle_button.ToggleButton;
import com.qy.customview.toggle_button.ToggleButton.OnToggleChanged;
import com.qy.customview.viewbadger.BadgeView;
import com.qy.main.R;

public class ToggleButtonActivity
        extends
        Activity
{
	@InjectView(R.id.toggle_btn1)
	ToggleButton btn1;
	@InjectView(R.id.toggle_btn2)
	ToggleButton btn2;
	@InjectView(R.id.toggle_btn3)
	ToggleButton btn3;
	@InjectView(R.id.toggle_btn4)
	ToggleButton btn4;
	private static final int red = Color.parseColor("#ff3300");
	BadgeView badge1,badge2,badge3,badge4;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.togglebutton_main);
        ButterKnife.inject(this);
        
        badge1 = new BadgeView(this, btn1);
        badge1.setBadgeBackgroundColor(red);
        badge1.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badge1.setTextColor(getResources().getColor(android.R.color.white));
        badge1.setText("11");
        
        badge2 = new BadgeView(this, btn2);
        badge2.setBackgroundResource(R.drawable.badge_ifaux);
        badge2.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badge2.setTextColor(getResources().getColor(android.R.color.white));
        badge2.setText("22");
        
        badge3 = new BadgeView(this, btn3);
        badge3.setBadgeBackgroundColor(red);
        badge3.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badge3.setTextColor(getResources().getColor(android.R.color.white));
        badge3.setText("33");
        
        badge4 = new BadgeView(this, btn4);
        badge4.setBackgroundResource(R.drawable.badge_ifaux);
        badge4.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badge4.setTextColor(getResources().getColor(android.R.color.white));
        badge4.setText("44");
        
        //切换开关
//      btn1.toggle();
//      btn1.setToggleOn();
//      btn1.setToggleOff();
        
        //开关切换事件
        btn1.setOnToggleChanged(new OnToggleChanged(){
                @Override
                public void onToggle(boolean on) {
                	if(on){
                		badge1.show();
                	}else{
                		badge1.hide();
                	}
                }
        });
        btn2.setOnToggleChanged(new OnToggleChanged(){
        	@Override
        	public void onToggle(boolean on) {
		    	badge2.toggle();
        	}
        });
        btn3.setOnToggleChanged(new OnToggleChanged(){
        	@Override
        	public void onToggle(boolean on) {
        		badge3.toggle(true);
        	}
        });
        btn4.setOnToggleChanged(new OnToggleChanged(){
        	@Override
        	public void onToggle(boolean on) {
        		TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
        		anim.setInterpolator(new BounceInterpolator());
        		anim.setDuration(1000);
        		badge4.toggle(anim, null);
        	}
        });


    }
}
