package com.tomclaw.minion.demo.benchmark;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tomclaw.minion.demo.R;

import me.zhanghai.android.materialprogressbar.IndeterminateHorizontalProgressDrawable;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by solkin on 03.08.17.
 */
class BenchmarkItemViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private TextView title;
    private MaterialProgressBar progress;
    private TextView result;

    public BenchmarkItemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.title = (TextView) itemView.findViewById(R.id.title);
        this.progress = (MaterialProgressBar) itemView.findViewById(R.id.progress);
        this.result = (TextView) itemView.findViewById(R.id.result);
    }

    public void bind(final BenchmarkItem item) {
        title.setText(item.getTitle());
        int value = item.getProgress();
        if (value == 0) {
            progress.setIndeterminate(true);
            Drawable drawable = new IndeterminateHorizontalProgressDrawable(itemView.getContext());
            progress.setIndeterminateDrawable(drawable);
        } else {
            progress.setIndeterminate(false);
            progress.setProgress(value);
        }
        result.setText(item.getResult());
    }
}
