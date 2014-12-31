package com.qy.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.qy.customview.flabby_listview.FlabbyAdapter;
import com.qy.main.R;

public class FlabbyListViewActivity
        extends
        ListActivity
{
    private static final int NUM_LIST_ITEM = 100;
    private FlabbyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flabby_lv_main);
        List<String> items = getListItems();
        mAdapter = new FlabbyAdapter(this,items);
        setListAdapter(mAdapter);
        getListView().setSelection(items.size()/2);
    }

    private ArrayList<String> getListItems() {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<NUM_LIST_ITEM;i++){
            list.add("Item"+i);
        }
        return list;
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, "onListItemClick position:"+position, Toast.LENGTH_SHORT).show();
    }
}
