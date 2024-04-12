package com.example.backend.bean;

public class PatrolCar {
    public String car_id;
    public String location;
    public String regulated_area;
    public String route;
    public String status;

    public PatrolCar() {
    }

    public PatrolCar(String car_id, String regulated_area, String route, String status) {
        this.car_id = car_id;
        this.regulated_area = regulated_area;
        this.route = route;
        this.status = status;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getRegulated_area() {
        return regulated_area;
    }

    public void setRegulated_area(String regulated_area) {
        this.regulated_area = regulated_area;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
