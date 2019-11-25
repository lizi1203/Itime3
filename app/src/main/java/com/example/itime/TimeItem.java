package com.example.itime;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class TimeItem implements Serializable {
    private String title;
    private Date date;
    private String description;
    private int imageId;

    public TimeItem(String title, Date date, String description, int imageId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, endDate.getDay());
        toCalendar.set(Calendar.MINUTE, endDate.getMinutes());
        toCalendar.set(Calendar.SECOND, endDate.getSeconds());
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (int) ((fromCalendar.getTime().getTime() - toCalendar.getTime().getTime()) / 1000 );
    }

}
