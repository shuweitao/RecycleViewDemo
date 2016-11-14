package com.recycleviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycleviewdemo.R;
import com.recycleviewdemo.bean.Data;

import java.util.List;

/**
 * @author swt
 * @time 2016/11/10 17:43
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private List<Data> mDatas;

    public ListAdapter(List<Data> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回一个ViewHolder,决定根视图
        View view = View.inflate(parent.getContext(), R.layout.item_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //数据和视图绑定
        Data data = mDatas.get(position);
        holder.setDataAndRefreshUI(data);
    }

    @Override
    public int getItemCount() {//返回的条数
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_icon;
        private final TextView tv_name;

        /**
         * @param view 接收一个根视图
         */
        public MyViewHolder(View view) {
            super(view);
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }

        public void setDataAndRefreshUI(Data data) {
            iv_icon.setImageResource(data.getIcon());
            tv_name.setText(data.getName());
        }
    }
}
