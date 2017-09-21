package com.haohahu.autoscrolltextviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.haohahu.autoscrolltextview.AutoScrollTextView;
import com.haohahu.autoscrolltextview.IMarqueeListener;
import com.haohahu.autoscrolltextview.MarqueeTextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MarqueeTextView margueeText;
    private MarqueeTextView margueeText1;
    private MarqueeTextView margueeText2;
    private AutoScrollTextView autoScrollTextView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initMarguee();
        findViewById(R.id.main_start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //margueeText.postStartScroll(1000);
                //margueeText1.postStartScroll(1000);
                //margueeText2.postStartScroll(1000);
                autoScrollTextView1.startAutoScroll();
            }
        });

        findViewById(R.id.main_stop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                margueeText.stopScroll();
                margueeText1.stopScroll();
            }
        });
    }

    private void initMarguee() {
        margueeText = (MarqueeTextView) findViewById(R.id.main_marguee_text);
        margueeText.setMarqueeListener(new IMarqueeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "margueeText到达终点", Toast.LENGTH_SHORT).show();
            }
        });
        margueeText.setText("走马灯古称蟠螭灯（秦汉）、仙音烛和转鹭灯（唐）、马骑灯（宋），中国特色工艺品，亦是传统节日玩具之一，属于灯笼的一种。");

        margueeText1 = (MarqueeTextView) findViewById(R.id.main_marguee_text1);
        margueeText1.setText("走马灯古称蟠螭灯（秦汉）、仙音烛和转鹭灯（唐）、马骑灯（宋），中国特色工艺品，亦是传统节日玩具之一，属于灯笼的一种。");
        margueeText1.setMarqueeListener(new IMarqueeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "margueeText1到达终点", Toast.LENGTH_SHORT).show();
            }
        });

        margueeText2 = (MarqueeTextView) findViewById(R.id.main_marguee_text2);
        margueeText2.setText("走马灯古称蟠螭灯（秦汉）");

        margueeText2.setMarqueeListener(new IMarqueeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "margueeText2到达终点", Toast.LENGTH_SHORT).show();
            }
        });

        autoScrollTextView1 = (AutoScrollTextView) findViewById(R.id.main_autoscroll_text1);

        List<String> strings = new ArrayList<>();
        //strings.add("走马灯古称蟠螭灯（秦汉）、仙音烛和转鹭灯（唐）、马骑灯（宋），中国特色工艺品，亦是传统节日玩具之一，属于灯笼的一种。");
        //strings.add("灯内点上蜡烛，烛产生的热力造成气流，令轮轴转动。轮轴上有剪纸，烛光将剪纸的影投射在屏上，图象便不断走动。");
        strings.add("0因多在灯各个面上绘制古代武将骑马的图画，而灯转动时看起来好像几个人你追我赶一样");
        strings.add("1因多在灯各个面上绘制古代武将骑马的图画，而灯转动时看起来好像几个人你追我赶一样");

        autoScrollTextView1.setTextList(strings);
    }
}
