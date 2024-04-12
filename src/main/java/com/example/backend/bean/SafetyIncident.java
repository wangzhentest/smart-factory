package com.example.backend.bean;

public class SafetyIncident {
    public int incident_id;
    public String incident_type;
    public String location;
    public String regulated_area;
    public String reason;
    public String occur_date;
    public String solve_date;
    public String equipment_id;
    public String on_site_picture;
    public int handle_employee_id;
    public int resolve_employee_id;

    public SafetyIncident() {
    }

    public SafetyIncident(int incident_id, String incident_type, String location, String regulated_area, String reason, String occur_date, String solve_date, String equipment_id, String on_site_picture, int handle_employee_id, int resolve_employee_id) {
        this.incident_id = incident_id;
        this.incident_type = incident_type;
        this.location = location;
        this.regulated_area = regulated_area;
        this.reason = reason;
        this.occur_date = occur_date;
        this.solve_date = solve_date;
        this.equipment_id = equipment_id;
        this.on_site_picture = on_site_picture;
        this.handle_employee_id = handle_employee_id;
        this.resolve_employee_id = resolve_employee_id;
    }

    public int getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(int incident_id) {
        this.incident_id = incident_id;
    }

    public String getIncident_type() {
        return incident_type;
    }

    public void setIncident_type(String incident_type) {
        this.incident_type = incident_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegulated_area() {
        return regulated_area;
    }

    public void setRegulated_area(String regulated_area) {
        this.regulated_area = regulated_area;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOccur_date() {
        return occur_date;
    }

    public void setOccur_date(String occur_date) {
        this.occur_date = occur_date;
    }

    public String getSolve_date() {
        return solve_date;
    }

    public void setSolve_date(String solve_date) {
        this.solve_date = solve_date;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getOn_site_picture() {
        return on_site_picture;
    }

    public void setOn_site_picture(String on_site_picture) {
        this.on_site_picture = on_site_picture;
    }

    public int getHandle_employee_id() {
        return handle_employee_id;
    }

    public void setHandle_employee_id(int handle_employee_id) {
        this.handle_employee_id = handle_employee_id;
    }

    public int getResolve_employee_id() {
        return resolve_employee_id;
    }

    public void setResolve_employee_id(int resolve_employee_id) {
        this.resolve_employee_id = resolve_employee_id;
    }

}

