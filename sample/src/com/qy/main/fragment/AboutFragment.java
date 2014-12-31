package com.qy.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.qy.main.R;
import com.qy.main.utils.AssetsUtil;


public class AboutFragment
        extends
        Fragment
{
	
	private Activity context;
	private View     parentView;
	@InjectView (R.id.about_txt)
	TextView         tv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.about, container, false);
		
		context = getActivity();
		ButterKnife.inject(this, parentView);
		
		tv.setText(AssetsUtil.getAssetString("about.txt", context));
		return parentView;
	}
	
}
