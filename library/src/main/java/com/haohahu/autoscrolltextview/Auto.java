package com.haohahu.autoscrolltextview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewSwitcher;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haohao on 2017/9/21 下午 02:42
 * @version v1.0
 */
public class Auto extends ViewSwitcher {

    private List<View> mViews = new ArrayList<>();

    public Auto(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setViews(List<View> views) {
        if (views == null || views.size() == 0) return;
        mViews = views;
        showViews();
    }

    public void setStrs(List<String> strings) {
        if (strings == null || strings.size() == 0) return;
        mViews.clear();
        for (int i = 0; i < strings.size(); i++) {
            //TextView text = new TextView(getContext());
            //mViews.add(text);
        }
        showViews();
    }

    private void showViews() {

    }
}
