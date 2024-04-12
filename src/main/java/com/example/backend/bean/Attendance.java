package com.example.backend.bean;

public class Attendance {
    public int attendance_id;
    public String name;
    public int employee_id;
    public String date;
    public String clock_in_time;
    public String clock_out_time;
    public int state;

    public Attendance() {
    }

    public Attendance(int attendance_id, String name, int employee_id, String date, String clock_in_time, String clock_out_time, int state) {
        this.attendance_id = attendance_id;
        this.name = name;
        this.employee_id = employee_id;
        this.date = date;
        this.clock_in_time = clock_in_time;
        this.clock_out_time = clock_out_time;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(int attendance_id) {
        this.attendance_id = attendance_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClock_in_time() {
        return clock_in_time;
    }

    public void setClock_in_time(String clock_in_time) {
        this.clock_in_time = clock_in_time;
    }

    public String getClock_out_time() {
        return clock_out_time;
    }

    public void setClock_out_time(String clock_out_time) {
        this.clock_out_time = clock_out_time;
    }

}
