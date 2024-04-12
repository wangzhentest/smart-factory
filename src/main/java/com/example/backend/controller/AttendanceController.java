package com.example.backend.controller;

import com.example.backend.bean.Attendance;
import com.example.backend.bean.Employee;
import com.example.backend.bean.LeaveRequest;
import com.example.backend.bean.Shifts;
import com.example.backend.service.AttendanceService;
import com.example.backend.service.EmployeeService;
import com.example.backend.service.LeaveRequestService;
import com.example.backend.service.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
@RestController
public class AttendanceController {

    @Autowired
    private LeaveRequestService leaveRequestService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShiftsService shiftsService;
    @Autowired
    private AttendanceService attendanceService;

    @CrossOrigin
    @RequestMapping("addAttendance")
    @ResponseBody
    public void addAttendance(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(dateFormatter);
        List<Employee> list = employeeService.getEmployees();
        for (int i = 0; i < list.size(); i++) {
            attendanceService.addAttendance(list.get(i).name,list.get(i).employee_id,date);
        }
    }

    @CrossOrigin
    @RequestMapping("get_today_shifts")
    @ResponseBody
    public List<Shifts>  getTodayShifts(){
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(dateFormatter);
        List<LeaveRequest> leaveRequestList = leaveRequestService.getLeaveRequests(date);
        ArrayList<Shifts> shiftsList = shiftsService.getTodayShifts(dayOfWeekString);
        List<Shifts> shiftsToRemove = new ArrayList<>();
        for (LeaveRequest leaveRequest : leaveRequestList) {
            int employeeId = leaveRequest.getEmployee_id();
            for (Shifts shifts : shiftsList) {
                if (shifts.getEmployee_id() == employeeId) {
                    shiftsToRemove.add(shifts);
                }
            }
        }
        shiftsList.removeAll(shiftsToRemove);


        return shiftsList;
    }


    @CrossOrigin
    @RequestMapping("employeeClockIn")
    @ResponseBody
    public void employeeClockIn(int employee_id){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(dateFormatter);
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String clock_in_time = currentTime.format(timeFormatter);
        LocalTime lateTime = LocalTime.parse("08:00:00");
        if (currentTime.isAfter(lateTime)) {
            attendanceService.employeeClockIn(employee_id,date,clock_in_time,2);
        } else {
            attendanceService.employeeClockIn(employee_id,date,clock_in_time,1);
        }

    }

    @CrossOrigin
    @RequestMapping("employeeClockOut")
    @ResponseBody
    public void employeeClockOut(int employee_id){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(dateFormatter);
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String clock_out_time = currentTime.format(timeFormatter);
        LocalTime leaveTime = LocalTime.parse("23:00:00");
        int state = attendanceService.get_state(employee_id,date);
        if (currentTime.isBefore(leaveTime)) {
            if (state == 1){
                attendanceService.employeeClockOut(employee_id,date,clock_out_time,4);
            }else {
                attendanceService.employeeClockOut(employee_id,date,clock_out_time,6);
            }
        } else {
            if (state == 1){
                attendanceService.employeeClockOut(employee_id,date,clock_out_time,1);
            }else {
                attendanceService.employeeClockOut(employee_id,date,clock_out_time,state);
            }
        }
    }
    @CrossOrigin
    @RequestMapping("getTodayAttendance")
    @ResponseBody
    public List<Attendance> getTodayAttendance(String date){
        List<Attendance> list = attendanceService.getTodayAttendance(date);
        return list;
    }


    @CrossOrigin
    @RequestMapping("getIP")
    @ResponseBody
    public void getIP(){
        try {
            // 获取本地主机的InetAddress实例
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Localhost IP Address: " + localhost.getHostAddress());

            // 获取所有的网络接口
            InetAddress[] allAddresses = InetAddress.getAllByName(localhost.getHostName());

            // 使用正则表达式筛选出以192.168开头的IP地址
            Pattern pattern = Pattern.compile("^192\\.168\\..*");
            for (InetAddress address : allAddresses) {
                if (pattern.matcher(address.getHostAddress()).matches()) {
                    System.out.println("IP Address starting with 192.168: " + address.getHostAddress());
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}


