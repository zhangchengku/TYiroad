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
import com.tyiroad.tyiroad.disease.newdisease.NewdiseaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 日期时间选择器
 * Created by dongxiaoqing on 2018/10/17.
 */

public class WheelDateAndTimeSelectPopupWindow extends PopupWindow implements View.OnClickListener {

    /******************控件声明**********************/
    private Activity activity;
    private TextView vCancelTxt;
    private TextView vOkTxt;
    private WheelView vSelectYear;
    private WheelView vSelectMonth;
    private WheelView vSelectDay;
    private WheelView vSelectHour;
    private WheelView vSelectMinute;
    /******************变量声明**********************/
    private ArrayList<String> yearSelectArr;
    private ArrayList<String> monthSelectArr;
    private ArrayList<String> daySelectArr;
    private ArrayList<String> hourSelectArr;
    private ArrayList<String> minuteSelectArr;
    private PopMyDataSelectAdapter yearSelectAdapter;
    private PopMyDataSelectAdapter monthSelectAdapter;
    private PopMyDataSelectAdapter daySelectAdapter;
    private PopMyDataSelectAdapter hourSelectAdapter;
    private PopMyDataSelectAdapter minuteSelectAdapter;
    private String resultSelectStr;
    private String resultSelectYearStr;
    private String resultSelectMonthStr;
    private String resultSelectDayStr;
    private String resultSelectHourStr;
    private String resultSelectMinuteStr;
    private Calendar calendarSelect;
    private SimpleDateFormat simpleDateFormat;
    private PopSelectListener listener;
    private int showViewItemNumber=7;//滚轮控件默认可见item个数
    private String yearStr;
    private String monthStr;
    private String dayStr;
    private String hourStr;
    private String minuteStr;
    private String[] months_big = {"1月", "3月", "5月", "7月", "8月", "10月", "12月"};
    private String[] months_little = {"4月", "6月", "9月", "11月"};
    final List<String> list_big = Arrays.asList(months_big);
    final List<String> list_little = Arrays.asList(months_little);


    public WheelDateAndTimeSelectPopupWindow(Activity activity, PopSelectListener listener) {
        this.activity = activity;
        this.listener = listener;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.popup_wheel_date_and_time, null);
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
        vSelectDay = (WheelView) contentView.findViewById(R.id.wheel_date_and_time_day);
        vSelectHour = (WheelView) contentView.findViewById(R.id.wheel_date_and_time_hour);
        vSelectMinute = (WheelView) contentView.findViewById(R.id.wheel_date_and_time_minute);

        vCancelTxt.setOnClickListener(this);
        vOkTxt.setOnClickListener(this);
        initWheelAdapterAndDatas();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity instanceof NewdiseaseActivity){
                    ((NewdiseaseActivity)activity).hideZheZhaoView();
                }
            }
        });

    }

    /**
     * 初始化日期选择数据 设置时间选择适配器
     */
    private void initWheelAdapterAndDatas() {

        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        yearStr = "年";
        monthStr = "月";
        dayStr = "日";
        hourStr = "时";
        minuteStr = "分";

        if (TextUtils.isEmpty(resultSelectStr)) {
            calendarSelect = Calendar.getInstance();
            resultSelectYearStr=String.valueOf(calendarSelect.get(Calendar.YEAR))+yearStr;
            resultSelectMonthStr=String.valueOf(calendarSelect.get(Calendar.MONTH)+1)+monthStr;
            resultSelectDayStr=String.valueOf(calendarSelect.get(Calendar.DAY_OF_MONTH))+dayStr;
            resultSelectHourStr=String.valueOf(calendarSelect.get(Calendar.HOUR_OF_DAY))+hourStr;
            resultSelectMinuteStr=String.valueOf(calendarSelect.get(Calendar.MINUTE))+minuteStr;
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
        daySelectArr = new ArrayList<>();
        hourSelectArr = new ArrayList<>();
        minuteSelectArr = new ArrayList<>();

        for (int i = 1990; i < 2101; i++) {
            yearSelectArr.add(i + yearStr);
        }

        for (int i = 1; i < 13; i++) {
            monthSelectArr.add(i + monthStr);
            if (i == monthCurrent) {
                monthSelectIndex = i - 1;
            }
        }
        for (int i = 1; i <= lastDayCount; i++) {
            daySelectArr.add(i + dayStr);
            if (i == dayCurrent) {
                daySelectIndex= i - 1;
            }
        }
        for (int i = 0; i <= 23; i++) {
            hourSelectArr.add(i + hourStr);
            if (i == hourCurrent) {
                hourSelectIndex = i;
            }
        }
        for (int i = 0; i <= 59; i++) {
            minuteSelectArr.add(i + minuteStr);
            if (i == minuteCurrent) {
                minuteSelectIndex = i;
            }
        }

        for (int i = 0; i < yearSelectArr.size(); i++) {
            if (yearSelectArr.get(i).equals(String.valueOf(yearCurrent) + yearStr)) {
                yearSelectIndex = i;
            }
        }

        yearSelectAdapter = new PopMyDataSelectAdapter(activity, yearSelectArr, yearSelectIndex);
        monthSelectAdapter = new PopMyDataSelectAdapter(activity, monthSelectArr, monthSelectIndex);
        daySelectAdapter = new PopMyDataSelectAdapter(activity, daySelectArr, daySelectIndex);
        hourSelectAdapter = new PopMyDataSelectAdapter(activity, hourSelectArr, hourSelectIndex);
        minuteSelectAdapter = new PopMyDataSelectAdapter(activity, minuteSelectArr, minuteSelectIndex);

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


        vSelectDay.setViewAdapter(daySelectAdapter);
        vSelectDay.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectDayStr = daySelectArr.get(newValue);
                String currentText = (String) daySelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, daySelectAdapter);
            }
        });
        vSelectDay.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) daySelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, daySelectAdapter);
            }
        });
        vSelectDay.setCurrentItem(daySelectIndex);
        vSelectDay.setVisibleItems(showViewItemNumber);


        vSelectHour.setViewAdapter(hourSelectAdapter);
        vSelectHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectHourStr = hourSelectArr.get(newValue);
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour.setCurrentItem(hourSelectIndex);
        vSelectHour.setVisibleItems(showViewItemNumber);


        vSelectMinute.setViewAdapter(minuteSelectAdapter);
        vSelectMinute.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectMinuteStr = minuteSelectArr.get(newValue);
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute.setCurrentItem(minuteSelectIndex);
        vSelectMinute.setVisibleItems(showViewItemNumber);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.wheel_date_and_time_cancel_btn:
                close();
                break;
            case R.id.wheel_date_and_time_ok_btn:
                try {
                    resultSelectStr = resultSelectYearStr + resultSelectMonthStr + resultSelectDayStr+" "+resultSelectHourStr+resultSelectMinuteStr;
                    calendarSelect.setTime(simpleDateFormat.parse(resultSelectStr));
                    listener.selectResult(resultSelectStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                close();
                break;
        }
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
            if (activity instanceof NewdiseaseActivity) {
                ((NewdiseaseActivity)activity).showZheZhaoView();
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
        if(lastStart!=daySelectArr.size()){
            daySelectArr.clear();
            for (int i = 1; i <= lastStart; i++) {
                daySelectArr.add(i + dayStr);
            }
            int currentIndex = 0;
            if (lastStart < vSelectDay.getCurrentItem()) {
                currentIndex = lastStart - 1;
            } else {
                currentIndex = vSelectDay.getCurrentItem();
            }
            daySelectAdapter = new PopMyDataSelectAdapter(activity, daySelectArr, currentIndex);
            vSelectDay.setViewAdapter(daySelectAdapter);
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