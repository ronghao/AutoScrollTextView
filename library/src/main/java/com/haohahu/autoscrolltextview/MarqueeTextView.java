package com.haohahu.autoscrolltextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯效果TextView
 *
 * @author haohao on 2017/9/21 下午 02:33
 * @version v1.0
 */
public class MarqueeTextView extends TextView {

    private int currentScrollPos = 0;
    //速度
    private int speed = 10;
    // 文字宽度
    private int textWidth = -1;
    //是否计算了宽度
    private boolean isMeasured = false;
    //是否完成移动
    private boolean flag = false;

    private IMarqueeListener marqueeListener;

    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            if (textWidth == -1) {
                postInvalidate();
                return;
            }
            currentScrollPos += 1;
            scrollTo(currentScrollPos, 0);
            if (!flag && currentScrollPos >= textWidth - getWidth()) {
                //currentScrollPos = -getWidth();
                flag = true;
                if (marqueeListener != null) {
                    marqueeListener.onFinish();
                }
            }

            if (!flag) {
                // 滚动时间间隔
                postDelayed(this, speed);
            }
        }
    };

    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSingleLine();
    }

    public void startScroll() {
        reset();
        removeCallbacks(runable);
        post(runable);
    }

    public void postStartScroll(int delay) {
        reset();
        removeCallbacks(runable);
        postDelayed(runable, delay);
    }

    public void stopScroll() {
        removeCallbacks(runable);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMarqueeListener(IMarqueeListener marqueeListener) {
        this.marqueeListener = marqueeListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isMeasured) {
            getTextWidth();
            isMeasured = true;
        }
    }

    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        if (TextUtils.isEmpty(str)) {
            textWidth = 0;
        }
        textWidth = (int) paint.measureText(str);
    }

    public void reset() {
        flag = false;
        currentScrollPos = 0;
        scrollTo(currentScrollPos, 0);
    }

    public void setText(String str) {
        super.setText(str);
        isMeasured = false;
        invalidate();
    }
}
