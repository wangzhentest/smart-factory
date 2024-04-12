package com.example.backend.bean;

import java.sql.Time;

public class Shifts {
    public int shift_id;
    public int employee_id;
    public String week;
    public String start_time;
    public String end_time;

    public Shifts() {
    }

    public Shifts(int shift_id, int employee_id, String week, String start_time, String end_time) {
        this.shift_id = shift_id;
        this.employee_id = employee_id;
        this.week = week;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
