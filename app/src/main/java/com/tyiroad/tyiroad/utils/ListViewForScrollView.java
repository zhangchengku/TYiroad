package com.tyiroad.tyiroad.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ListView适用于ScrollView 自定义控件
 *
 * @author caochen
 *
 */
public class ListViewForScrollView extends ListView {

    /**
     * 构造方法
     *
     * @param context
     *            contex对象
     * @param attrs
     *            样式参数
     * @param defStyle
     *            默认样式
     */
    public ListViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    /**
     * 构造方法
     *
     * @param context
     *            contex对象
     * @param attrs
     *            样式参数
     */
    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * 构造方法
     *
     * @param context
     *            contex对象
     */
    public ListViewForScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
