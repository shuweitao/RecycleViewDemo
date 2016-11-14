package com.recycleviewdemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.recycleviewdemo.adapter.ListAdapter;
import com.recycleviewdemo.adapter.StaggeredAdapter;
import com.recycleviewdemo.bean.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView的简单实用
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private List<Data> mDatas;
    private List<Data> mStraggerds;
    private int[] mListIcons = new int[]{R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4, R.mipmap.g5,
            R.mipmap.g6, R.mipmap.g7, R.mipmap.g8, R.mipmap.g9, R.mipmap.g10, R.mipmap.g11, R.mipmap.g12, R.mipmap.g13,
            R.mipmap.g14, R.mipmap.g15, R.mipmap.g16, R.mipmap.g17, R.mipmap.g18, R.mipmap.g19, R.mipmap.g20,
            R.mipmap.g21, R.mipmap.g22, R.mipmap.g23, R.mipmap.g24, R.mipmap.g25, R.mipmap.g26, R.mipmap.g27,
            R.mipmap.g28, R.mipmap.g29};


    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myRecyclerView.getAdapter().notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }


    private void initData() {
        setSupportActionBar(toolbar);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(new ListAdapter(mDatas));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorAccent));
        initListener();
    }

    public void getData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < mListIcons.length; i++) {
            Data data = new Data();
            data.setIcon(mListIcons[i]);
            data.setName("我是item" + i);
            mDatas.add(data);
        }

        mStraggerds = new ArrayList<>();
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            Data data = new Data();
            data.setIcon(mStraggeredIcons[i]);
            data.setName("我是item" + i);
            mStraggerds.add(data);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list_h:
                initListAdapterH();
                break;
            case R.id.action_list_v:
                initListAdapterV();
                break;
            case R.id.action_grid_h:
                initGridAdapterH();
                break;
            case R.id.action_grid_v:
                initGridAdapterV();
                break;
            case R.id.action_staggered_h:
                initStaggredAdapterH();
                break;
            case R.id.action_staggered_v:
                initStrggedAdapterV();
                break;
            default:

                break;
        }
        return true;
    }

    //纵向的瀑布流
    private void initStrggedAdapterV() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setAdapter(new StaggeredAdapter(mStraggerds));
    }

    //横向的瀑布流
    private void initStaggredAdapterH() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setAdapter(new StaggeredAdapter(mStraggerds));
    }

    //纵向的list
    private void initGridAdapterV() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        myRecyclerView.setAdapter(new ListAdapter(mDatas));
    }

    //横向的Grid
    private void initGridAdapterH() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        myRecyclerView.setAdapter(new ListAdapter(mDatas));
    }

    //纵向的list 默认情况
    private void initListAdapterV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(new ListAdapter(mDatas));
    }

    //横向的list
    private void initListAdapterH() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(new ListAdapter(mDatas));
    }
}
