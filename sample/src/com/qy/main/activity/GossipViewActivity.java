package com.qy.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.qy.customview.gossipview.GossipItem;
import com.qy.customview.gossipview.GossipView;
import com.qy.main.R;

public class GossipViewActivity
        extends
        Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gossipview_main);
        GossipView gossipView = (GossipView)findViewById(R.id.gossipview);
		String [] strs = {"安卓","微软","苹果","谷歌","百度","腾讯"} ;
				 
		final List<GossipItem> items =new ArrayList<GossipItem>();
		for(int i = 0; i < strs.length; i++) { 
			GossipItem item = new GossipItem(strs[i],3);
			items.add(item);
		}
		gossipView.setItems(items);
		gossipView.setNumber(6);
		gossipView.setOnPieceClickListener( new GossipView.OnPieceClickListener(){
			@Override
			public void onPieceClick(int index) {
			  if(index != -1 &&  index != -2) {
				  Toast.makeText(GossipViewActivity.this, "你选择了" + items.get(index).getTitle(), Toast.LENGTH_SHORT).show();
			  }
		    }
		});
    }
}
