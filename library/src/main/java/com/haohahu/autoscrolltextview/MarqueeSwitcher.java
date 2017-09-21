package com.haohahu.autoscrolltextview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

/**
 * MarqueeSwitcher {@link android.widget.TextSwitcher} t
 *
 * @author haohao on 2017/9/21 下午 03:57
 * @version v1.0
 */
public class MarqueeSwitcher extends ViewSwitcher {
    /**
     * Creates a new empty TextSwitcher.
     *
     * @param context the application's environment
     */
    public MarqueeSwitcher(Context context) {
        super(context);
    }

    /**
     * Creates a new empty TextSwitcher for the given context and with the
     * specified set attributes.
     *
     * @param context the application environment
     * @param attrs a collection of attributes
     */
    public MarqueeSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if child is not an instance of
     * {@link android.widget.TextView}
     */
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof RelativeLayout)) {
            throw new IllegalArgumentException(
                    "MarqueeSwitcher children must be instances of RelativeLayout");
        }
        super.addView(child, index, params);
    }

    /**
     * Sets the text of the next view and switches to the next view. This can
     * be used to animate the old text out and animate the next text in.
     *
     * @param text the new text to display
     */
    public void setText(String text) {
        final MarqueeTextView t = getNextView();
        t.setText(text);
        t.postStartScroll(1500);
        showNext();
    }

    public void setText(String text, IMarqueeListener iMarqueeListener) {
        final MarqueeTextView t = getNextView();
        t.setMarqueeListener(iMarqueeListener);
        t.setText(text);
        t.postStartScroll(1500);
        showNext();
    }

    public void stopText() {
        MarqueeTextView t = getNextView();
        if (t != null) t.stopScroll();

        MarqueeTextView t1 = getCurrentView();
        if (t != null) t1.stopScroll();
    }

    public MarqueeTextView getCurrentView() {
        return (MarqueeTextView) ((RelativeLayout) super.getCurrentView()).getChildAt(0);
    }

    public MarqueeTextView getNextView() {
        return (MarqueeTextView) ((RelativeLayout) super.getNextView()).getChildAt(0);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return MarqueeTextView.class.getName();
    }
}
