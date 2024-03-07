package com.example.webmy3.beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


import java.io.Serializable;
import java.time.ZonedDateTime;

@Named
@ApplicationScoped
public class TimeBean implements Serializable {

    public String getDay() {
        String day = format(ZonedDateTime.now().getDayOfMonth());
        System.out.println("Day: " + day);
        return day;
    }

    public String getMonth() {
        return format(ZonedDateTime.now().getMonthValue());
    }

    public String getYear() {
        return format(ZonedDateTime.now().getYear());
    }

    public String getHour() {
        return format(ZonedDateTime.now().getHour());
    }

    public String getMin() {
        return format(ZonedDateTime.now().getMinute());
    }

    public String getSec() {
        return format(ZonedDateTime.now().getSecond());
    }

    public void setDay(String day) {
    }

    public void setMonth(String month) {
    }

    public void setYear(String year) {
    }

    public void setHour(String hour) {
    }

    public void setMin(String min) {
    }

    public void setSec(String sec) {
    }

    private String format(int val) {
        String day = String.valueOf(val);
        if (day.length() < 2) {
            day = "0" + day;
        }
        return day;
    }
}