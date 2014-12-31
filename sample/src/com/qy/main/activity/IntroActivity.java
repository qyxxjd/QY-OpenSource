package com.qy.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.qy.main.R;
import com.qy.main.bean.Obj;
import com.qy.main.utils.AssetsUtil;

public class IntroActivity extends Activity{

	@InjectView(R.id.intro_title)
	TextView title;
	@InjectView(R.id.intro_content)
	TextView intro;
	@InjectView(R.id.intro_url)
	TextView url;
	@InjectView(R.id.intro_btn)
	Button	 btn;
	
	Obj obj;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        ButterKnife.inject(this);
        
        if(getIntent().hasExtra("obj")){
        	obj = (Obj) getIntent().getSerializableExtra("obj");
        }
        if(null==obj){
        	finish();
        	return;
        }
        
        title.setText(obj.title);
        intro.setText(AssetsUtil.getAssetString(obj.title+".txt", this));
        if(!TextUtils.isEmpty(obj.url)){
        	url.setVisibility(View.VISIBLE);
        	url.setText("项目主页："+obj.url);
        }
        btn.setVisibility(obj.isEnable ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.intro_btn)
    public void click(){
    	startActivity(new Intent(this,obj._class));
    }
}
