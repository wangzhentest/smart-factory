package com.example.backend.bean;

import java.util.Date;

public class Employee {
    public int employee_id;
    public String name;
    public String gender;
    public String birth_date;
    public String position;
    public String department;
    public String phone_number;

    public Employee() {
    }

    public Employee(int employee_id, String name, String gender, String birth_date, String position, String department, String phone_number) {
        this.employee_id = employee_id;
        this.name = name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.position = position;
        this.department = department;
        this.phone_number = phone_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
