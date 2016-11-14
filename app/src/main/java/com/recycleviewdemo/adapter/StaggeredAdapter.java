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
 * @time 2016/11/14 10:47
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.StaggerdViewHolder> {
    private List<Data> mDatas;

    public StaggeredAdapter(List<Data> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public StaggerdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_stragger, null);
        return new StaggerdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StaggerdViewHolder holder, int position) {
        //数据和视图绑定
        Data data = mDatas.get(position);
        holder.setDataAndRefreshUI(data);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    class StaggerdViewHolder extends RecyclerView.ViewHolder {
        private final ImageView item_straggered_iv;
        private final TextView item_straggered_tv;

        public StaggerdViewHolder(View view) {
            super(view);
            item_straggered_iv = (ImageView) view.findViewById(R.id.item_straggered_iv);
            item_straggered_tv = (TextView) view.findViewById(R.id.item_straggered_tv);
        }

        public void setDataAndRefreshUI(Data data) {
            item_straggered_iv.setImageResource(data.getIcon());
            item_straggered_tv.setText(data.getName());
        }
    }
}
