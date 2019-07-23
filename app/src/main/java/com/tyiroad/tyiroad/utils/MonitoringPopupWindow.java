package com.tyiroad.tyiroad.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tooklkit.Tooklkit;
import com.tyiroad.tyiroad.R;
import com.tyiroad.tyiroad.monitoring.MonitoringActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 张成昆 on 2019-6-28.
 */

public class MonitoringPopupWindow  extends PopupWindow {
    /******************控件声明**********************/
    private Activity activity;
    private TextView vCancelTxt;
    private TextView vOkTxt;
    private WheelView vSelectYear;
    private WheelView vSelectMonth;

    /******************变量声明**********************/
    private ArrayList<String> yearSelectArr;
    private ArrayList<String> monthSelectArr;


    private PopMyDataSelectAdapter yearSelectAdapter;
    private PopMyDataSelectAdapter monthSelectAdapter;


    private String resultSelectStr;
    private String resultSelectYearStr;
    private String resultSelectMonthStr;


    private Calendar calendarSelect;
    private SimpleDateFormat simpleDateFormat;
    private PopSelectListener listener;
    private int showViewItemNumber=7;//滚轮控件默认可见item个数
    private String yearStr;
    private String monthStr;


    private String[] months_big = {"1月", "3月", "5月", "7月", "8月", "10月", "12月"};
    private String[] months_little = {"4月", "6月", "9月", "11月"};
    final List<String> list_big = Arrays.asList(months_big);
    final List<String> list_little = Arrays.asList(months_little);


    public MonitoringPopupWindow(Activity activity,PopSelectListener listener) {
        this.activity = activity;
        this.listener = listener;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.popup_monitoring_time, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity)- Tooklkit.dip2px(activity,25));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.mypopwindow_anim_style);
        initViews(contentView);
    }

    /**
     * 初始化控件
     * @param contentView
     */
    private void initViews(View contentView) {
        vCancelTxt = (TextView) contentView.findViewById(R.id.wheel_date_and_time_cancel_btn);
        vOkTxt = (TextView) contentView.findViewById(R.id.wheel_date_and_time_ok_btn);
        vSelectYear = (WheelView) contentView.findViewById(R.id.wheel_date_and_time_year);
        vSelectMonth = (WheelView) contentView.findViewById(R.id.wheel_date_and_time_month);
        vCancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
        vOkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resultSelectStr = resultSelectYearStr + resultSelectMonthStr ;
                    calendarSelect.setTime(simpleDateFormat.parse(resultSelectStr));
                    listener.selectResult(resultSelectStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                close();
            }
        });
        initWheelAdapterAndDatas();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
//                if(activity instanceof RepairActivity){
//                    ((RepairActivity)activity).hideZheZhaoView();
//                }
            }
        });

    }

    /**
     * 初始化日期选择数据 设置时间选择适配器
     */
    private void initWheelAdapterAndDatas() {

        simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        yearStr = "年";
        monthStr ="月";



        if (TextUtils.isEmpty(resultSelectStr)) {
            calendarSelect = Calendar.getInstance();
            resultSelectYearStr=String.valueOf(calendarSelect.get(Calendar.YEAR))+yearStr;
            resultSelectMonthStr=String.valueOf(calendarSelect.get(Calendar.MONTH)+1)+monthStr;


        } else {
            try {
                calendarSelect = Calendar.getInstance();
                calendarSelect.setTime(simpleDateFormat.parse(resultSelectStr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int yearCurrent = calendarSelect.get(Calendar.YEAR);//获取年
        int monthCurrent = calendarSelect.get(Calendar.MONTH) + 1;//获取月份，0表示1月份
        int dayCurrent = calendarSelect.get(Calendar.DAY_OF_MONTH);//获取当前天数
        int hourCurrent = calendarSelect.get(Calendar.HOUR_OF_DAY);//获取时
        int minuteCurrent = calendarSelect.get(Calendar.MINUTE);//获取分
        int lastDayCount = calendarSelect.getActualMaximum(calendarSelect.DAY_OF_MONTH);//获取本月最大天数

        int yearSelectIndex = 0;
        int monthSelectIndex = 0;
        int daySelectIndex = 0;
        int hourSelectIndex = 0;
        int minuteSelectIndex = 0;

        yearSelectArr = new ArrayList<>();
        monthSelectArr = new ArrayList<>();



        for (int i = 1990; i < 2101; i++) {
            yearSelectArr.add(i + yearStr);
        }

        for (int i = 1; i < 13; i++) {
            monthSelectArr.add(i + monthStr);
            if (i == monthCurrent) {
                monthSelectIndex = i - 1;
            }
        }



        for (int i = 0; i < yearSelectArr.size(); i++) {
            if (yearSelectArr.get(i).equals(String.valueOf(yearCurrent) + yearStr)) {
                yearSelectIndex = i;
            }
        }

        yearSelectAdapter = new PopMyDataSelectAdapter(activity, yearSelectArr, yearSelectIndex);
        monthSelectAdapter = new PopMyDataSelectAdapter(activity, monthSelectArr, monthSelectIndex);

        vSelectYear.setViewAdapter(yearSelectAdapter);
        vSelectYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectYearStr = yearSelectArr.get(newValue);
                String currentText = (String) yearSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearSelectAdapter);
                refreshSelectDayPoup();
            }
        });
        vSelectYear.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) yearSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearSelectAdapter);
            }
        });
        vSelectYear.setCurrentItem(yearSelectIndex);
        vSelectYear.setVisibleItems(showViewItemNumber);


        vSelectMonth.setViewAdapter(monthSelectAdapter);
        vSelectMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectMonthStr = monthSelectArr.get(newValue);
                String currentText = (String) monthSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthSelectAdapter);
                refreshSelectDayPoup();
            }
        });
        vSelectMonth.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) monthSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthSelectAdapter);
            }
        });
        vSelectMonth.setCurrentItem(monthSelectIndex);
        vSelectMonth.setVisibleItems(showViewItemNumber);

    }


    /**
     * 显示筛选窗口
     */
    public void show(View anchor) {
        if (isShowing()) {
            return;
        }
        if (anchor != null) {
            if (!TextUtils.isEmpty(resultSelectStr)) {
                initWheelAdapterAndDatas();
            }
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
            if (activity instanceof MonitoringActivity) {
//                ((MonitoringActivity)activity).showZheZhaoView();
            }
        }
    }

    /**
     * 关闭筛选窗口
     */
    public void close() {
        if (isShowing()) {
            dismiss();
        }
    }

    /**
     * 设置文字的颜色和大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextViewStyle(String curriteItemText, PopMyDataSelectAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextColor(activity.getResources().getColor(R.color.text_3));
            } else {
                textvew.setTextColor(activity.getResources().getColor(R.color.text_9));
            }
        }
    }

    /**
     * 年和月改变时刷新日的适配器
     */
    private void refreshSelectDayPoup() {
        int lastStart = 30;
        if(list_big.contains(resultSelectMonthStr)){
            lastStart=31;
        }else if(list_little.contains(resultSelectMonthStr)){
            lastStart=30;
        }else{
            Integer selYear=Integer.parseInt(resultSelectYearStr.substring(0,resultSelectYearStr.length()-1));
            if (isLeapYear(selYear)){
                lastStart=29;
            }else{
                lastStart=28;
            }
        }

    }

    /**
     * 判断是否是闰年
     * @param selYear
     * @return
     */
    private boolean isLeapYear(int selYear){
        boolean result;
        if ((selYear % 4 == 0 && selYear % 100 != 0) || selYear % 400 == 0){
            result=true;
        }else{
            result=false;
        }
        return result;
    }
}

