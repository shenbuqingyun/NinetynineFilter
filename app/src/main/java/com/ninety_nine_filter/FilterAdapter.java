package com.ninety_nine_filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 作者    chin_style
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

        private List<FilterBean> mFilterList;

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView filterImage;
            TextView filterName;

            public ViewHolder(View itemView) {
                super(itemView);
                filterImage = (ImageView) itemView.findViewById(R.id.filter_image);
                filterName = (TextView) itemView.findViewById(R.id.filter_name);
            }
        }

        public FilterAdapter(List<FilterBean> songList) {
            mFilterList = songList;
        }

        @Override
        public FilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.filter_item, parent, false);
            ViewHolder holder = new ViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(final FilterAdapter.ViewHolder holder, final int position) {
            FilterBean filterBean = mFilterList.get(position);
            holder.filterImage.setImageResource(filterBean.getImageId());
            holder.filterName.setText(String.valueOf(filterBean.getId()));
            holder.filterImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 添加动画
                    mListener.itemClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFilterList.size();
        }

    public interface OnFilterClickListener {
        void itemClick(int id);
    }

    private OnFilterClickListener mListener;

    public void setOnFilterClickListener(OnFilterClickListener listener) {
        mListener = listener;
    }
}
