package com.haohaohu.autoscrolltextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 跑马灯效果TextView
 *
 * @author haohao on 2017/9/21 下午 02:33
 * @version v1.0
 */
public class MarqueeTextView extends TextView {

    private int currentScrollPos = 0;
    //速度
    private int speed = 6;
    // 文字宽度
    private int textWidth = -1;
    //是否计算了宽度
    private volatile boolean isMeasured = false;
    //是否完成移动
    private volatile boolean flag = false;
    //是否停止移动
    private volatile boolean isStop = false;

    private IMarqueeListener marqueeListener;
    private Future future;

    ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (textWidth == -1) {
                postInvalidate();
                return;
            }
            if (isStop) {
                return;
            }
            if (!flag && currentScrollPos >= textWidth - getWidth()) {
                //currentScrollPos = -getWidth();
                task.cancel();
                flag = true;
                if (marqueeListener != null) {
                    marqueeListener.onFinish();
                }
            }

            if (!flag) {
                currentScrollPos += 1;
                scrollTo(currentScrollPos, 0);
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
        stopFuture();
        future = pool.scheduleAtFixedRate(task, 0, speed, TimeUnit.MILLISECONDS);
        //removeCallbacks(runable);
        //post(runable);
    }

    public void postStartScroll(int delay) {
        reset();
        stopFuture();
        future = pool.scheduleAtFixedRate(task, delay, speed, TimeUnit.MILLISECONDS);
        //removeCallbacks(runable);
        //postDelayed(runable, delay);
    }

    public void stopScroll() {
        isStop = true;
        stopFuture();
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
        isStop = false;
        currentScrollPos = 0;
        scrollTo(currentScrollPos, 0);
    }

    public void setText(String str) {
        super.setText(str);
        isMeasured = false;
        invalidate();
    }

    private synchronized void stopFuture() {
        if (future != null && !future.isCancelled()) {
            future.cancel(true);
        }
        if (task != null) {
            task.cancel();
        }
    }
}
