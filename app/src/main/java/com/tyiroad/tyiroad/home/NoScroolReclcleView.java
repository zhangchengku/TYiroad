package com.tyiroad.tyiroad.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 张成昆 on 2019-6-27.
 */

public class NoScroolReclcleView extends RecyclerView {

    public NoScroolReclcleView(Context context) {
        super(context);
    }

    public NoScroolReclcleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScroolReclcleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 不出现滚动条
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}