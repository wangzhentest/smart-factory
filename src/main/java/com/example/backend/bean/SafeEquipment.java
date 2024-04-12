package com.example.backend.bean;

public class SafeEquipment {
    public String equipment_id;
    public String location;
    public String regulated_area;
    public String camera_source;
    public String type;

    public SafeEquipment() {
    }

    public SafeEquipment(String equipment_id, String location, String regulated_area, String camera_source, String type) {
        this.equipment_id = equipment_id;
        this.location = location;
        this.regulated_area = regulated_area;
        this.camera_source = camera_source;
        this.type = type;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
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

    public String getCamera_source() {
        return camera_source;
    }

    public void setCamera_source(String camera_source) {
        this.camera_source = camera_source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
