package com.qy.main.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qy.customview.swipemenu_listview.SwipeMenu;
import com.qy.customview.swipemenu_listview.SwipeMenuCreator;
import com.qy.customview.swipemenu_listview.SwipeMenuItem;
import com.qy.customview.swipemenu_listview.SwipeMenuListView;
import com.qy.customview.swipemenu_listview.SwipeMenuListView.OnMenuItemClickListener;
import com.qy.main.R;

public class SwipeMenuListViewActivity
        extends
        Activity
{
	private List<ApplicationInfo> mAppList;
	private AppAdapter mAdapter;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipemenulistview_main);
		context = this;
		mAppList = getPackageManager().getInstalledApplications(0);

		SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);
		mAdapter = new AppAdapter();
		listView.setAdapter(mAdapter);

		// step 1. create a MenuCreator
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// Create different menus depending on the view type
				switch (menu.getViewType()) {
				case 0:
					createMenu1(menu);
					break;
				case 1:
					createMenu2(menu);
					break;
				case 2:
					createMenu3(menu);
					break;
				}
			}

			private void createMenu1(SwipeMenu menu) {
				SwipeMenuItem item1 = new SwipeMenuItem(
						getApplicationContext());
				item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
						0x5E)));
				item1.setWidth(dp2px(90));
				item1.setIcon(R.drawable.ic_action_favorite);
				menu.addMenuItem(item1);
				SwipeMenuItem item2 = new SwipeMenuItem(
						getApplicationContext());
				item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				item2.setWidth(dp2px(90));
				item2.setIcon(R.drawable.ic_action_good);
				menu.addMenuItem(item2);
			}
			
			private void createMenu2(SwipeMenu menu) {
				SwipeMenuItem item1 = new SwipeMenuItem(
						getApplicationContext());
				item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
						0x3F)));
				item1.setWidth(dp2px(90));
				item1.setIcon(R.drawable.ic_action_important);
				menu.addMenuItem(item1);
				SwipeMenuItem item2 = new SwipeMenuItem(
						getApplicationContext());
				item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				item2.setWidth(dp2px(90));
				item2.setIcon(R.drawable.ic_action_discard);
				menu.addMenuItem(item2);
			}
			
			private void createMenu3(SwipeMenu menu) {
				SwipeMenuItem item1 = new SwipeMenuItem(
						getApplicationContext());
				item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
						0xF5)));
				item1.setWidth(dp2px(90));
				item1.setIcon(R.drawable.ic_action_about);
				menu.addMenuItem(item1);
				SwipeMenuItem item2 = new SwipeMenuItem(
						getApplicationContext());
				item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				item2.setWidth(dp2px(90));
				item2.setIcon(R.drawable.ic_action_share);
				menu.addMenuItem(item2);
			}
		};
		// set creator
		listView.setMenuCreator(creator);

		// step 2. listener item click event
		listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//				ApplicationInfo item = mAppList.get(position);
				
				//如果是多种不同的菜单，需要对SwipeMenu的ViewType进行处理
				switch(menu.getViewType()){
					case 0:
						Toast.makeText(context, index==0?"这是最喜欢的":"32个赞，哈哈！", Toast.LENGTH_SHORT).show();
						break;
					case 1:
						Toast.makeText(context, index==0?"这是五角星":"亲！你这是要删除吗？", Toast.LENGTH_SHORT).show();
						break;
					case 2:
						Toast.makeText(context, index==0?"这是关于":"赶快分享到朋友圈吧！", Toast.LENGTH_SHORT).show();
						break;
				}
				
				//如果是只有一种菜单，这样处理即可
//				switch (index) {
//				case 0:
//					break;
//				case 1:
//					mAppList.remove(position);
//					mAdapter.notifyDataSetChanged();
//					break;
//				}
				return true;
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				ApplicationInfo item = mAppList.get(position);
				Toast.makeText(context, "onItemClick --> "+item.loadLabel(getPackageManager()), Toast.LENGTH_SHORT).show();
            }
		});

	}

	class AppAdapter extends BaseAdapter {
		ViewHolder holder;
		@Override
		public int getCount() {
			return mAppList.size();
		}

		@Override
		public ApplicationInfo getItem(int position) {
			return mAppList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public int getViewTypeCount() {
			// menu type count
			return 3;
		}
		
		@Override
		public int getItemViewType(int position) {
			// current menu type
			return position % 3;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.swipemenulistview_item, null);
				new ViewHolder(convertView);
			}
			holder = (ViewHolder) convertView.getTag();
			ApplicationInfo item = getItem(position);
			holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
			holder.tv_name.setText(item.loadLabel(getPackageManager()));
			
			return convertView;
		}

		class ViewHolder {
			ImageView iv_icon;
			TextView tv_name;

			public ViewHolder(View view) {
				iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
				tv_name = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(this);
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
}
