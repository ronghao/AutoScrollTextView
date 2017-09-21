package com.haohahu.autoscrolltextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;
import java.util.ArrayList;
import java.util.List;

/**
 * 上下滚动
 *
 * @author haohao on 2017/9/21 下午 02:28
 * @version v1.0
 */
public class AutoScrollTextView extends MarqueeSwitcher implements ViewSwitcher.ViewFactory {

    private static final int FLAG_START_AUTO_SCROLL = 1001;
    private static final int FLAG_STOP_AUTO_SCROLL = 1002;

    private OnItemClickListener itemClickListener;
    /**
     * 当前显示Item的ID
     */
    private int currentId = -1;
    private ArrayList<String> textList;

    private ArrayList<MarqueeTextView> textViewList;

    private Handler handler;

    private boolean isStop = false;

    public AutoScrollTextView(Context context) {
        this(context, null);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textList = new ArrayList<>();
        textViewList = new ArrayList<>();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_START_AUTO_SCROLL:
                        if (textList.size() > 0) {
                            currentId++;
                            setText(textList.get(currentId % textList.size()));
                        }
                        break;
                    case FLAG_STOP_AUTO_SCROLL:
                        isStop = true;
                        handler.removeMessages(FLAG_START_AUTO_SCROLL);
                        break;
                }
            }
        };

        setFactory(this);

        setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_up_in));
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_up_out));
    }

    /**
     * 设置数据源
     */
    public void setTextList(List<String> titles) {
        textList.clear();
        textList.addAll(titles);
        currentId = -1;
    }

    /**
     * 开始轮播
     */
    public void startAutoScroll() {
        handler.sendEmptyMessage(FLAG_START_AUTO_SCROLL);
    }

    /**
     * 停止轮播
     */
    public void stopAutoScroll() {
        handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
        stopText();
    }

    /**
     * 设置点击事件监听
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public View makeView() {
        // FIXME: 2017/9/21 添加这层RelativeLayout是解决动画默认回到句首的问题
        RelativeLayout layout = new RelativeLayout(getContext());
        MarqueeTextView textView = new MarqueeTextView(getContext());
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
                    itemClickListener.onItemClick(currentId % textList.size());
                }
            }
        });
        textView.setMarqueeListener(new IMarqueeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                if (isStop) {
                    isStop = false;
                    return;
                }
                handler.sendMessageDelayed(Message.obtain(handler, FLAG_START_AUTO_SCROLL), 1000);
            }
        });
        layout.addView(textView);
        return layout;
    }

    /**
     * 轮播文本点击监听器
     */
    public interface OnItemClickListener {

        /**
         * 点击回调
         *
         * @param position 当前点击ID
         */
        public void onItemClick(int position);
    }
}
