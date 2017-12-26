package com.haohaohu.autoscrolltextviewsample;

import android.content.Context;
import android.util.AttributeSet;
import com.haohaohu.autoscrolltextview.BaseScrollTextView;
import com.haohaohu.autoscrolltextview.MarqueeTextView;

/**
 * 上下滚动
 *
 * @author haohao on 2017/9/21 下午 02:28
 * @version v1.0
 */
public class MyAutoScrollTextView extends BaseScrollTextView {

    public MyAutoScrollTextView(Context context) {
        super(context);
    }

    public MyAutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public MarqueeTextView makeTextView() {
        MarqueeTextView textView = new MarqueeTextView(getContext());
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        return textView;
    }
}
