package com.haohaohu.autoscrolltextviewsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haohao(ronghao3508 gmail.com) on 2020/4/9 17:39
 * @version v1.0
 */
public class MainViewPagerActivity extends AppCompatActivity {
    private List<FragmentHomeSlide> slides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);

        final FragmentHomeSlide slide_1 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_2 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_3 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_4 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_5 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_6 = new FragmentHomeSlide();
        final FragmentHomeSlide slide_7 = new FragmentHomeSlide();
        slides.add(slide_1);
        slides.add(slide_2);
        slides.add(slide_3);
        slides.add(slide_4);
        slides.add(slide_5);
        slides.add(slide_6);
        slides.add(slide_7);

        final ViewPager view_pager = (ViewPager) findViewById(R.id.view_pager);
        assert getFragmentManager() != null;
        view_pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            @NonNull
            public Fragment getItem(final int position) {
                return slides.get(position);
            }

            @Override
            public int getCount() {
                return slides.size();
            }
        });
    }
}
