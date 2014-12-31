package com.qy.main.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.qy.customview.dragsort_listview.DragSortListView;
import com.qy.main.R;


/**
 * 可以拖动排序的ListView
 * @author 刘宾  
 * @date 2014年12月14日 下午9:41:04
 */
public class DragSortListViewActivity
        extends
        Activity
{
	private Context           context;
	@InjectView(R.id.dragsort_lv)
	DragSortListView          lv;
	private Adapter       	  adapter;
	private ArrayList<String> data;
	
	/** 是否开启快速滑动 */
	private boolean           isFastScroll = true; 
	/** 是否开启滑动删除 */
	private boolean           isDelete     = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dragsort_listview);
		context = this;
		ButterKnife.inject(this);
		
		initData();
		initView();
	}
	
	private void initView() {
		lv.setDropListener(onDrop);
		if(isDelete){
			lv.setRemoveListener(onRemove);
		}
		if (isFastScroll)
		{
			lv.setDragScrollProfile(ssProfile);
		}
		adapter = new Adapter();
		lv.setAdapter(adapter);
	}
	private void initData(){
		data = new ArrayList<String>();
		for (int i = 0; i < 50; i++)
        {
	        data.add("可拖动排序、滑动删除的ListView "+(i+1));
        }
	}
	@OnItemClick(R.id.dragsort_lv)
	void itemClick(int position){
		Toast.makeText(context, "OnItemClick position:"+position, Toast.LENGTH_SHORT).show();
	}
	
	private class Adapter
	        extends
	        BaseAdapter
	{
		
		@Override
		public int getCount() {
			return data.size();
		}
		
		@Override
		public String getItem(int position) {
			return data.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null){
				convertView = LayoutInflater.from(DragSortListViewActivity.this).inflate(R.layout.dragsort_listview_item, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txt.setText(data.get(position));
			return convertView;
		}
	}
	
	static class ViewHolder{
		@InjectView(R.id.dragsort_listview_item_txt)
		TextView txt;
		@InjectView(R.id.drag_handle)
		ImageView img;
		ViewHolder(View view) {
			ButterKnife.inject(this, view);
        }
	}
	
	private DragSortListView.DropListener      onDrop    = new DragSortListView.DropListener()
	                                                     {
		                                                     @Override
		                                                     public void drop(int from, int to) {
		                                                    	 if (from != to) {
		                                                             final String item = data.get(from);
		                                                             data.remove(from);
		                                                             data.add(to, item);
		                                                             adapter.notifyDataSetChanged();
		                                                         }
		                                                     }
	                                                     };
	
	private DragSortListView.RemoveListener onRemove = 
	    new DragSortListView.RemoveListener() {
	        @Override
	        public void remove(int which) {
	        	data.remove(which);
	        	adapter.notifyDataSetChanged();
	        }
	    };
	
	private DragSortListView.DragScrollProfile ssProfile = new DragSortListView.DragScrollProfile()
	                                                     {
		                                                     @Override
		                                                     public float getSpeed(float w, long t) {
			                                                     if (w > 0.8f)
			                                                     {
				                                                     // Traverse all views in a millisecond
				                                                     return ((float) adapter.getCount()) / 0.001f;
			                                                     }
			                                                     else
			                                                     {
				                                                     return 10.0f * w;
			                                                     }
		                                                     }
	                                                     };
}
