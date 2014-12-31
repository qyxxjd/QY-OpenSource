package com.qy.main.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.qy.customview.residemenu.ResideMenu;
import com.qy.customview.xlistview.XListView;
import com.qy.customview.xlistview.XListView.IXListViewListener;
import com.qy.main.R;
import com.qy.main.activity.IntroActivity;
import com.qy.main.activity.MainActivity;
import com.qy.main.bean.Obj;
import com.qy.main.data.Const;
import com.qy.main.utils.AssetsUtil;

public class HomeFragment extends Fragment implements IXListViewListener {

    private View parentView;
    private ResideMenu resideMenu;
    
	private ArrayList<Obj> data;
	private MyAdapter adapter;
	private Handler mHandler;
	@InjectView(R.id.home_lv)
	XListView lv;
	private int type;

	private Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
        
        context = getActivity();
        ButterKnife.inject(this,parentView);
		
		mHandler = new Handler();
		data = new ArrayList<Obj>();
		type = getArguments().getInt(Const.TAG);
		initData();
		initView();
		setUpViews();
        return parentView;
    }

    private void setUpViews() {
    	MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        //在某些场景下，手势滑动开启/关闭菜单可能与您的某些控件产生冲突
//        resideMenu.addIgnoredView(lv);
    }

    
	private void initView() {
		adapter = new MyAdapter();
		lv.setPullLoadEnable(type==Const.TAG_TYPE0);
		lv.setAdapter(adapter);
		lv.setXListViewListener(this);
    }
	@OnItemClick(R.id.home_lv)
	void itemClick(int position){
		final Obj obj = data.get(position-1);
        if(null != obj){
        	Intent intent = new Intent(context,IntroActivity.class);
        	intent.putExtra("obj", obj);
        	startActivity(intent);
        }
	}
	
	private class MyAdapter extends BaseAdapter{
		@Override
        public int getCount() {
	        return data.size();
        }
		@Override
        public Object getItem(int arg0) {
	        return data.get(arg0);
        }
		@Override
        public long getItemId(int arg0) {
	        return arg0;
        }

		@Override
        public View getView(int arg0, View view, ViewGroup arg2) {
	        ViewHolder holder;
	        if(null == view){
	        	view = LayoutInflater.from(context).inflate(R.layout.main_item, null);
	        	holder = new ViewHolder(view);
	        	view.setTag(holder);
	        }else{
	        	holder = (ViewHolder) view.getTag();
	        }
	        final Obj obj = (Obj) getItem(arg0);
	        if(obj != null){
	        	holder.title.setText(obj.title);
	        	holder.intro.setText(AssetsUtil.getAssetString(obj.title+".txt", context));
	        	holder.img.setVisibility(obj.isUse?View.VISIBLE:View.GONE);
	        }
	        return view;
        }
		
	}
	static class ViewHolder{
		@InjectView(R.id.main_item_title)
		TextView title;
		@InjectView(R.id.main_item_intro)
		TextView intro;
		@InjectView(R.id.main_item_img)
		ImageView img;
		ViewHolder(View view) {
			ButterKnife.inject(this, view);
        }
	}
	private void onLoad() {
		lv.stopRefresh();
		lv.stopLoadMore();
		lv.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				data.clear();
				initData();
				initView();
				onLoad();
			}
		}, 500);
	}
	private void initData() {
		data.clear();
		if(type == Const.TAG_TYPE0){
			for (int key : Const.map.keySet())
            {
	            data.addAll(Const.map.get(key));
            }
		}else if(null != Const.map.get(type)){
			data.addAll(Const.map.get(type));
		}
    }

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//TODO 加载分页数据
				adapter.notifyDataSetChanged();
				onLoad();
			}
		}, 500);
	}
}
