package com.example.backend.bean;

import java.util.Date;

public class LeaveRequest {
    public int request_id;
    public int employee_id;
    public String date;
    public String reason;
    public String status;

    public LeaveRequest() {
    }

    public LeaveRequest(int request_id, int employee_id, String date, String reason, String status) {
        this.request_id = request_id;
        this.employee_id = employee_id;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
