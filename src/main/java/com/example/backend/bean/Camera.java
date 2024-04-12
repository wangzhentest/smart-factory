package com.example.backend.bean;

public class Camera {
    String equipment_id;
    String location;
    String regulated_area;
    String camera_source;
    String temperature;
    int humidity;

    public Camera() {
    }

    public Camera(String equipment_id, String location, String regulated_area, String camera_source, String temperature, int humidity) {
        this.equipment_id = equipment_id;
        this.location = location;
        this.regulated_area = regulated_area;
        this.camera_source = camera_source;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
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
}
