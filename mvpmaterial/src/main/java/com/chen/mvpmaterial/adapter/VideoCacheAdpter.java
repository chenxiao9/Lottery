package com.chen.mvpmaterial.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chen.mvpmaterial.R;
import com.chen.mvpmaterial.engine.DownloaderWrapper;
import com.chen.mvpmaterial.local.table.VideoInfo;
import com.chen.mvpmaterial.rxbus.RxBus;
import com.chen.mvpmaterial.utils.DefIconFactory;
import com.chen.mvpmaterial.utils.ImageLoader;
import com.chen.mvpmaterial.utils.StringUtils;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.dl7.downloaderlib.model.DownloadStatus;
import com.dl7.recycler.adapter.BaseViewHolder;



/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class VideoCacheAdpter extends BaseVideoDLAdapter {

    public VideoCacheAdpter(Context context, RxBus rxBus) {
        super(context, rxBus);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_video_cache;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final VideoInfo item) {
        setTag(holder,item.getVideoUrl());
        ImageView ivThumb = holder.getView(R.id.iv_thumb);
        ImageLoader.loadFitCenter(mContext, item.getCover(), ivThumb, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.getTitle());
        _switchViews(holder, item);

        holder.getView(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _handleClick(holder, item);
            }
        });
        // 根据是否为编辑模式来进行处理
        final CheckBox cbDelete = holder.getView(R.id.cb_delete);
        if (mIsEditMode) {
            cbDelete.setVisibility(View.VISIBLE);
            cbDelete.setChecked(mSparseItemChecked.get(holder.getAdapterPosition()));
        } else {
            cbDelete.setVisibility(View.GONE);
            cbDelete.setChecked(false);
        }
        cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 注意这里在切换为编辑状态时，获取holder.getAdapterPosition()=-1 的情况，要在_handleCheckedChanged()进行判断处理
                _handleCheckedChanged(holder.getAdapterPosition(), isChecked);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsEditMode) {
                    cbDelete.setChecked(!cbDelete.isChecked());
                } else {
//                    VideoPlayerActivity.launch(mContext, item);
                }
            }
        });
    }

    /**
     * 更新视图
     * @param holder
     * @param item
     */
    private void _switchViews(BaseViewHolder holder, VideoInfo item) {
        switch (item.getDownloadStatus()){
            case DownloadStatus.DOWNLOADING:
                NumberProgressBar pbDownload = holder.getView(R.id.pb_download);
                if (!holder.isVisible(R.id.pb_download) || !holder.isSelected(R.id.btn_download)) {
                    holder.setVisible(R.id.pb_download, true)
                            .setSelected(R.id.btn_download, true)
                            .setText(R.id.tv_total_size, StringUtils.convertStorageNoB(item.getTotalSize()))
                            .setTextColor(R.id.tv_speed, ContextCompat.getColor(mContext, R.color.download_normal));
                    pbDownload.setMax((int) item.getTotalSize());
                }
                holder.setText(R.id.tv_load_size, StringUtils.convertStorageNoB(item.getLoadedSize()) + "/")
                        .setText(R.id.tv_speed, StringUtils.convertStorageNoB(item.getDownloadSpeed()) + "/s");
                pbDownload.setProgress((int) item.getLoadedSize());
                break;
            case DownloadStatus.STOP:
                if (holder.isVisible(R.id.pb_download) || holder.isSelected(R.id.btn_download))
                    holder.setVisible(R.id.pb_download,false)
                            .setSelected(R.id.btn_download, false)
                            .setText(R.id.tv_speed, "下载暂停")
                            .setTextColor(R.id.tv_speed, ContextCompat.getColor(mContext, R.color.download_stop));
                break;
            case DownloadStatus.COMPLETE:
                mRxBus.post(item);
            case DownloadStatus.CANCEL:
                // 移除
                removeItem(item);
                break;
            case DownloadStatus.ERROR:
                holder.setText(R.id.tv_speed, "异常出错，请重新下载")
                        .setVisible(R.id.pb_download, false)
                        .setSelected(R.id.btn_download, false)
                        .setTextColor(R.id.tv_speed, ContextCompat.getColor(mContext, R.color.download_error));
                break;
        }
    }

    /**
     * 点击处理
     * @param holder
     * @param item
     */
    private void _handleClick(BaseViewHolder holder, VideoInfo item) {
        switch (item.getDownloadStatus()) {
            case com.dl7.downloaderlib.model.DownloadStatus.NORMAL:
            case com.dl7.downloaderlib.model.DownloadStatus.ERROR:
            case com.dl7.downloaderlib.model.DownloadStatus.STOP:
                holder.setText(R.id.tv_speed, "处理中...")
                        .setTextColor(R.id.tv_speed, ContextCompat.getColor(mContext, R.color.download_normal));
                DownloaderWrapper.start(item);
                break;

            case DownloadStatus.DOWNLOADING:
                holder.setText(R.id.tv_speed, "即将暂停")
                        .setTextColor(R.id.tv_speed, ContextCompat.getColor(mContext, R.color.download_normal));
                DownloaderWrapper.stop(item);
                break;

            default:
                break;
        }
    }
}
