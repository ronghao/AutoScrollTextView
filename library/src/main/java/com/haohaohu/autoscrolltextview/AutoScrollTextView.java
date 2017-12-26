package com.haohaohu.autoscrolltextview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 上下滚动
 *
 * @author haohao on 2017/9/21 下午 02:28
 * @version v1.0
 */
public class AutoScrollTextView extends BaseScrollTextView {

    public AutoScrollTextView(Context context) {
        super(context);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public MarqueeTextView makeTextView() {
        return new MarqueeTextView(getContext());
    }
}
