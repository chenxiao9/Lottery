package com.chen.zhbj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chen.zhbj.R;
import com.chen.zhbj.domain.ZhihuDailyNews;
import com.chen.zhbj.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by lizhaotailang on 2016/3/18.
 * 知乎日报消息适配器
 * latest posts adapter
 */
public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<ZhihuDailyNews.StoriesBean> list = new ArrayList<>();
    private OnRecyclerViewOnClickListener mListener;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    @Inject
    public ZhihuDailyNewsAdapter(Context context, List<ZhihuDailyNews.StoriesBean> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==TYPE_ITEM){
            view = inflater.inflate(R.layout.zhihu_item_layout,parent,false);
            return new LatestItemViewHolder(view,mListener);
        }else {
            view = inflater.inflate(R.layout.layout_foot_item,parent,false);
            return new FootViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LatestItemViewHolder){
            LatestItemViewHolder theHolder=(LatestItemViewHolder)holder;
            ZhihuDailyNews.StoriesBean item = list.get(position);

            if (item.getImages().get(0) == null){
                theHolder.itemImg.setImageResource(R.drawable.placeholder);
            } else {
                Glide.with(context)
                        .load(item.getImages().get(0))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(theHolder.itemImg);
            }
            theHolder.tvLatestNewsTitle.setText(item.getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public void setList(List<ZhihuDailyNews.StoriesBean> list) {
        this.list = list;
    }

    public class LatestItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImg;
        private TextView tvLatestNewsTitle;
        private OnRecyclerViewOnClickListener listener;
        public LatestItemViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);

            itemImg = (ImageView) itemView.findViewById(R.id.zhihu_item_iv);
            tvLatestNewsTitle = (TextView) itemView.findViewById(R.id.zhihu_item_tv_title);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
            tvFoot=(TextView)itemView.findViewById(R.id.tv_foot);
        }
        TextView tvFoot;
    }
}
