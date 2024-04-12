package com.example.backend.bean;

public class messages {
    private int id;
    private String occur_date;
    private String occur_area;
    private String occur_place;
    private String camera_id;
    private String alarm_reason;
    private String if_warn;

    public messages() {
    }

    public messages(int id, String occur_date, String occur_area, String occur_place, String camera_id, String alarm_reason, String if_warn) {
        this.id = id;
        this.occur_date = occur_date;
        this.occur_area = occur_area;
        this.occur_place = occur_place;
        this.camera_id = camera_id;
        this.alarm_reason = alarm_reason;
        this.if_warn = if_warn;
    }

    public messages(String occur_date, String camera_id, String alarm_reason) {
        this.occur_date = occur_date;
        this.camera_id = camera_id;
        this.alarm_reason = alarm_reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccur_date() {
        return occur_date;
    }

    public void setOccur_date(String occur_date) {
        this.occur_date = occur_date;
    }

    public String getOccur_area() {
        return occur_area;
    }

    public void setOccur_area(String occur_area) {
        this.occur_area = occur_area;
    }

    public String getOccur_place() {
        return occur_place;
    }

    public void setOccur_place(String occur_place) {
        this.occur_place = occur_place;
    }

    public String getCamera_id() {
        return camera_id;
    }

    public void setCamera_id(String camera_id) {
        this.camera_id = camera_id;
    }

    public String getAlarm_reason() {
        return alarm_reason;
    }

    public void setAlarm_reason(String alarm_reason) {
        this.alarm_reason = alarm_reason;
    }

    public String getIf_warn() {
        return if_warn;
    }

    public void setIf_warn(String if_warn) {
        this.if_warn = if_warn;
    }
}
