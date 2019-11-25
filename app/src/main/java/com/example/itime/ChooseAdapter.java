package com.example.itime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChooseAdapter extends ArrayAdapter<ChooseItem>{
    private int resourceId;
    Calendar calendar=Calendar.getInstance();

    Dialog dialog1=new Dialog(getContext());
    Dialog dialog2=new Dialog(getContext());
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;

    public ChooseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChooseItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChooseItem chooseItem = getItem(position);//获取当前项的实例
        final View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((ImageView) view.findViewById(R.id.image2)).setImageResource(chooseItem.getImageId());
        ((TextView) view.findViewById(R.id.title2)).setText(chooseItem.getTitle());
        final TextView textViewDescription=view.findViewById(R.id.description2);
        textViewDescription.setText(chooseItem.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_date, null);
                    //这个布局在下边,可参考
                    final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);

                    //设置日期简略显示 否则详细显示 包括:星期周
                    datePicker.setCalendarViewShown(true);
                //初始化当前日期
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    //初始化当前日期
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), null);
                    /**
                     * 下面这行代吗 设置的是只显示年月
                     */

                    //设置date布局
                    builder.setView(view);
                    builder.setTitle("设置日期信息");


                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                             year = datePicker.getYear();
                             month = datePicker.getMonth()+1;
                             day=datePicker.getDayOfMonth();
                             hour=calendar.get(Calendar.HOUR_OF_DAY);
                            minute=calendar.get(Calendar.MINUTE);


                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                            final View view2 = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_time, null);
                            final TimePicker timePicker=(TimePicker)view2.findViewById(R.id.time_picker);
                            timePicker.setHour(hour);
                            timePicker.setMinute(minute);

                            builder2.setView(view2);
                            builder2.setTitle("设置时间信息");

                            builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    hour=timePicker.getHour();
                                    minute=timePicker.getMinute();
                                    String monthE= String.format(String.valueOf(month), "%tb");
                                    if(minute<10){
                                       textViewDescription.setText(monthE+" "+day+","+year+" "+hour+":0"+minute);
                                    }else
                                    {
                                        textViewDescription.setText(monthE+" "+day+","+year+" "+hour+":"+minute);
                                    }
                                }
                            });
                            builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog2.cancel();
                                }
                            });

                            dialog2 = builder2.create();
                            dialog2.show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog1.cancel();
                        }
                    });
                    dialog1 = builder.create();
                    dialog1.show();

                }
            }
        });
        return view;
    }
}

