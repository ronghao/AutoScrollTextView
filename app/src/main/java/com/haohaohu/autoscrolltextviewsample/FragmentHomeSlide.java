package com.haohaohu.autoscrolltextviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.haohaohu.autoscrolltextview.AutoScrollTextView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author haohao(ronghao3508 gmail.com) on 2020/4/9 17:39
 * @version v1.0
 */
public class FragmentHomeSlide extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.home_slide, container, false);
        setWidgets(inflated);
        return inflated;
    }

    private void setWidgets(View inflated) {
        String[] text_presentation = new String[3];
        text_presentation[0] = "Foo";
        text_presentation[1] = "Foo1";
        text_presentation[2] = "Foo2";

        AutoScrollTextView baseScrollTextView =
                (AutoScrollTextView) inflated.findViewById(R.id.main_autoscroll_text1);
        baseScrollTextView.setTextList(new ArrayList<>(Arrays.asList(text_presentation)));
        baseScrollTextView.startAutoScroll();
    }
}