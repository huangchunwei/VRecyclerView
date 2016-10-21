package com.mevv.vrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mevv.vrecyclerview.recycler.SpacesItemDecoration;
import com.mevv.vrecyclerview.recycler.VRecyclerView;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VRecyclerView mRecyclerView;
    private List<MyBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (VRecyclerView) findViewById(R.id.recyclerView);
        initData();

    }

    private void initData() {
        mList = new ArrayList<MyBean>();
        for (int i = 0; i < 35; i++) {
            if (i % 2 == 0) {
                mList.add(new MyBean(i, "测试\n\nfadsfadsdsfasdfasdfsadf\n\n\nfsdafhdjsfhajsdhfj\n\nhsfjasdhfkjdashf\n\nfdshfjashfjskafh" + i));
            } else {
                mList.add(new MyBean(i, "测试" + i));
            }
        }
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(this, 10));
        mRecyclerView.setPadding(10, 0, 10, 0);
        MyAdapter myAdapter = new MyAdapter(this, mList, R.layout.itme);
        mRecyclerView.setLoadingListener(new VRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (mRecyclerView != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.refreshComplete();
                        }
                    }, 3000);
                }
            }

            @Override
            public void onLoadMore() {
                if (mRecyclerView != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.loadMoreComplete("-----没有更多数据-----");
                        }
                    }, 3000);
                }
            }
        });
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Toast.makeText(MainActivity.this, "位置:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
