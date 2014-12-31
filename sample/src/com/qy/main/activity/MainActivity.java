package com.qy.main.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.qy.customview.residemenu.ResideMenu;
import com.qy.customview.residemenu.ResideMenuItem;
import com.qy.customview.universal_image_loader.ImageLoaderUtil;
import com.qy.main.R;
import com.qy.main.data.Const;
import com.qy.main.fragment.AboutFragment;
import com.qy.main.fragment.HomeFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem item0;
    private ResideMenuItem item1;
    private ResideMenuItem item2;
    private ResideMenuItem item3;
    private ResideMenuItem item4;
    private ResideMenuItem item5;
    private ResideMenuItem item6;
    private ResideMenuItem item7;
    private ResideMenuItem item8;
    
    private ResideMenuItem rightItem1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        Const.init();
        ImageLoaderUtil.initImageLoader_normal(this);
        setUpMenu();
        System.out.println(""+Environment.getExternalStorageDirectory().getPath());
        if( savedInstanceState == null )
            changeFragment(getHomeFragment(Const.TAG_TYPE0));
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_bg);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        item0     = new ResideMenuItem(this, R.drawable.home_img,"首页");
        item1     = new ResideMenuItem(this, R.drawable.framework_img,"开发框架");
        item2     = new ResideMenuItem(this, R.drawable.menu_img,"菜单类");
        item3     = new ResideMenuItem(this, R.drawable.list_img,"列表类");
        item4     = new ResideMenuItem(this, R.drawable.image_img,"图片类");
        item5     = new ResideMenuItem(this, R.drawable.dialog_img,"对话框");
        item6     = new ResideMenuItem(this, R.drawable.button_img,"按钮类");
        item7     = new ResideMenuItem(this, R.drawable.progress_img,"进度条");
        item8     = new ResideMenuItem(this, R.drawable.other_img,"其它");
        rightItem1     = new ResideMenuItem(this, R.drawable.about_img,"关于");

        item0.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        item7.setOnClickListener(this);
        item8.setOnClickListener(this);
        rightItem1.setOnClickListener(this);

        resideMenu.addMenuItem(item0, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item3, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item4, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item5, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item6, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item7, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item8, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(rightItem1, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    //禁止手势操作某个菜单方向
    //resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    
    
    /*
     *  在某些场景下，手势滑动开启/关闭菜单可能与您的某些控件产生冲突，例如viewpager，这时您可以把viewpager添加到ignored view.

        // add gesture operation's ignored views
        FrameLayout ignored_view = (FrameLayout) findViewById(R.id.ignored_view);
        resideMenu.addIgnoredView(ignored_view);
        
		这样子在ignored_view操作的区域就不允许用手势滑动操作菜单.
     */
    
    /**
     * 如果您需要使用手势滑动开启/关闭菜单，请复写activity的dispatchTouchEvent()
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
    	if (view == item0){
            changeFragment(getHomeFragment(Const.TAG_TYPE0));
        }else if (view == item1){
            changeFragment(getHomeFragment(Const.TAG_TYPE1));
        }else if (view == item2){
            changeFragment(getHomeFragment(Const.TAG_TYPE2));
        }else if (view == item3){
            changeFragment(getHomeFragment(Const.TAG_TYPE3));
        }
        else if (view == item4){
            changeFragment(getHomeFragment(Const.TAG_TYPE4));
        }
        else if (view == item5){
        	changeFragment(getHomeFragment(Const.TAG_TYPE5));
        }
        else if (view == item6){
        	changeFragment(getHomeFragment(Const.TAG_TYPE6));
        }
        else if (view == item7){
        	changeFragment(getHomeFragment(Const.TAG_TYPE7));
        }
        else if (view == item8){
        	changeFragment(getHomeFragment(Const.TAG_TYPE8));
        }
        else if (view == rightItem1){
        	changeFragment(new AboutFragment());
        }
        
        resideMenu.closeMenu();
    }
    private HomeFragment getHomeFragment(int tag){
    	final HomeFragment fragment = new HomeFragment();
    	final Bundle bundle = new Bundle();
    	bundle.putInt(Const.TAG, tag);
    	fragment.setArguments(bundle);
    	return fragment;
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
