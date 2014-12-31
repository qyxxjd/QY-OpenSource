package com.qy.main.data;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;

import com.qy.main.activity.CircleProgressActivity;
import com.qy.main.activity.CircularProgressButtonActivity;
import com.qy.main.activity.CollageViewActivity;
import com.qy.main.activity.DiscrollViewActivity;
import com.qy.main.activity.DragSortListViewActivity;
import com.qy.main.activity.FlabbyListViewActivity;
import com.qy.main.activity.GossipViewActivity;
import com.qy.main.activity.NumberProgressBarActivity;
import com.qy.main.activity.SatelliteMenuActivity;
import com.qy.main.activity.SweetAlertDialogActivity;
import com.qy.main.activity.SwipeMenuListViewActivity;
import com.qy.main.activity.TextJustifyActivity;
import com.qy.main.activity.TickPlusDrawableActivity;
import com.qy.main.activity.TitanicActivity;
import com.qy.main.activity.ToggleButtonActivity;
import com.qy.main.activity.TouchImageViewActivity;
import com.qy.main.bean.Obj;

public class Const
{
	/*
	 *         item0     = new ResideMenuItem(this, R.drawable.icon_home,"首页");
        item1     = new ResideMenuItem(this, R.drawable.icon_home,"开发框架");
        item2     = new ResideMenuItem(this, R.drawable.icon_home,"菜单类");
        item3     = new ResideMenuItem(this, R.drawable.icon_home,"列表类");
        item4     = new ResideMenuItem(this, R.drawable.icon_home,"图片类");
        item5     = new ResideMenuItem(this, R.drawable.icon_home,"对话框");
        item6     = new ResideMenuItem(this, R.drawable.icon_home,"按钮类");
        item7     = new ResideMenuItem(this, R.drawable.icon_home,"进度条");
        item8     = new ResideMenuItem(this, R.drawable.icon_home,"其它");
	 */
	
	public static final String TAG = "type";
	/** 全部 */
	public static final int TAG_TYPE0 = 0;
	/** 开发框架 */
	public static final int TAG_TYPE1 = 1;
	/** 菜单类 */
	public static final int TAG_TYPE2 = 2;
	/** 列表类 */
	public static final int TAG_TYPE3 = 3;
	/** 图片类 */
	public static final int TAG_TYPE4 = 4;
	/** 对话框 */
	public static final int TAG_TYPE5 = 5;
	/** 按钮类 */
	public static final int TAG_TYPE6 = 6;
	/** 进度条 */
	public static final int TAG_TYPE7 = 7;
	/** 其它 */
	public static final int TAG_TYPE8 = 8;
	@SuppressLint ("UseSparseArrays")
    public static HashMap<Integer, ArrayList<Obj>> map = new HashMap<Integer, ArrayList<Obj>>();
	
    public static void init(){
		ArrayList<Obj> data = new ArrayList<Obj>();
		//------------开发框架
		data.add(new Obj("ButterKnife", false,"https://github.com/JakeWharton/butterknife"));
		map.put(TAG_TYPE1, data);
		
		
		//------------菜单类
		data = new ArrayList<Obj>();
		data.add(new Obj("ResideMenu", false,"https://github.com/SpecialCyCi/AndroidResideMenu"));
		data.add(new Obj("SatelliteMenu", false,true,"https://github.com/siyamed/android-satellite-menu/",SatelliteMenuActivity.class));
		map.put(TAG_TYPE2, data);
		
		//------------列表类
		data = new ArrayList<Obj>();
		data.add(new Obj("XListView", true,"https://github.com/Maxwin-z/XListView-Android"));
		data.add(new Obj("DragSortListView", true,true,"https://github.com/bauerca/drag-sort-listview",DragSortListViewActivity.class));
		data.add(new Obj("FlabbyListView", false,true,"https://github.com/jpardogo/FlabbyListView",FlabbyListViewActivity.class));
		data.add(new Obj("SwipeMenuListView", false,true,"https://github.com/baoyongzhang/SwipeMenuListView",SwipeMenuListViewActivity.class));
		map.put(TAG_TYPE3, data);
		
		//------------图片类
		data = new ArrayList<Obj>();
		data.add(new Obj("TouchImageView", true,true,"https://github.com/MikeOrtiz/TouchImageView",TouchImageViewActivity.class));
		data.add(new Obj("CollageView", false,true,"https://github.com/thuytrinh/android-collage-views",CollageViewActivity.class));
		map.put(TAG_TYPE4, data);
		
		//------------对话框
		data = new ArrayList<Obj>();
		data.add(new Obj("SweetAlertDialog", false,true,"https://github.com/pedant/sweet-alert-dialog",SweetAlertDialogActivity.class));
		map.put(TAG_TYPE5, data);
		
		
		//------------按钮类
		data = new ArrayList<Obj>();
		data.add(new Obj("GossipView", false,true,"https://github.com/jianghejie/GossipView",GossipViewActivity.class));
		data.add(new Obj("ToggleButton", false,true,"https://github.com/zcweng/ToggleButton",ToggleButtonActivity.class));
		data.add(new Obj("CircularProgressButton", false,true,"https://github.com/dmytrodanylyk/circular-progress-button",CircularProgressButtonActivity.class));
		map.put(TAG_TYPE6, data);
		
		//------------进度条
		data = new ArrayList<Obj>();
		data.add(new Obj("NumberProgressBar", false,true,"https://github.com/daimajia/NumberProgressBar",NumberProgressBarActivity.class));
		data.add(new Obj("CircleProgress", false,true,"https://github.com/lzyzsd/CircleProgress",CircleProgressActivity.class));
		map.put(TAG_TYPE7, data);
		
		//------------其它
		data = new ArrayList<Obj>();
		data.add(new Obj("DiscrollView", false,true,"https://github.com/flavienlaurent/discrollview",DiscrollViewActivity.class));
		data.add(new Obj("Titanic", false,true,"https://github.com/RomainPiel/Titanic",TitanicActivity.class));
		data.add(new Obj("TextJustify", false,true,"https://github.com/bluejamesbond/TextJustify-Android",TextJustifyActivity.class));
		data.add(new Obj("TickPlusDrawable", false,true,"https://github.com/flavienlaurent/tickplusdrawable",TickPlusDrawableActivity.class));
		map.put(TAG_TYPE8, data);
		
		
		
	}
	
}
