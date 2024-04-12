package com.example.backend.bean;

public class Summary {
    public int summary_id;
    public int incident_id;
    public String incident_type;
    public String occur_date;
    public String location;
    public String occur_area;
    public String incident_reason;
    public String dispose_date;
    public int dispose_staff_id;
    public String equipment_id;
    public String expenditures;
    public String potential_risk;

    public Summary() {
    }

    public Summary(int summary_id, int incident_id, String incident_type, String occur_date, String location, String occur_area, String incident_reason, String dispose_date, int dispose_staff_id, String equipment_id, String expenditures, String potential_risk) {
        this.summary_id = summary_id;
        this.incident_id = incident_id;
        this.incident_type = incident_type;
        this.occur_date = occur_date;
        this.location = location;
        this.occur_area = occur_area;
        this.incident_reason = incident_reason;
        this.dispose_date = dispose_date;
        this.dispose_staff_id = dispose_staff_id;
        this.equipment_id = equipment_id;
        this.expenditures = expenditures;
        this.potential_risk = potential_risk;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSummary_id() {
        return summary_id;
    }

    public void setSummary_id(int summary_id) {
        this.summary_id = summary_id;
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

    public String getIncident_reason() {
        return incident_reason;
    }

    public void setIncident_reason(String incident_reason) {
        this.incident_reason = incident_reason;
    }

    public String getDispose_date() {
        return dispose_date;
    }

    public void setDispose_date(String dispose_date) {
        this.dispose_date = dispose_date;
    }

    public int getDispose_staff_id() {
        return dispose_staff_id;
    }

    public void setDispose_staff_id(int dispose_staff_id) {
        this.dispose_staff_id = dispose_staff_id;
    }

    public String getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(String expenditures) {
        this.expenditures = expenditures;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getPotential_risk() {
        return potential_risk;
    }

    public void setPotential_risk(String potential_risk) {
        this.potential_risk = potential_risk;
    }
}
