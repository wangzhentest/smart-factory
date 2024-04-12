package com.example.backend.controller;

import com.example.backend.bean.messages;
import com.example.backend.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.stereotype.Controller
public class MessageController {
    @Autowired
    private MessageService messageService;


    @CrossOrigin
    @RequestMapping("get_messages")
    @ResponseBody
    public List<messages> get_messages() {
        List<messages> list = messageService.get_messages();
//        logger.info("Get Message ...");
        return list;
    }

    @CrossOrigin
    @RequestMapping("add_messages")
    @ResponseBody
    public void add_messages(HttpServletRequest request) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String occur_date = nowDateTime.format(formatter);
        String camera_id = request.getParameter("camera_id");
        String alarm_reason = request.getParameter("alarm_reason");
        messages message = new messages(occur_date, camera_id, alarm_reason);
        messageService.add_message(message);
    }

    @CrossOrigin
    @RequestMapping("get_not_reminded_messages")
    @ResponseBody
    public List<messages> get_not_reminded_messages(){
        List<messages> list = messageService.get_not_reminded_messages();
        return list;
    }

    @CrossOrigin
    @RequestMapping("remind_message")
    @ResponseBody
    public void remind_message(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        messageService.remind_message(id);
    }

    //删除信息
    @CrossOrigin
    @RequestMapping("delete_message")
    @ResponseBody
    public void delete_message(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        String user_id = request.getParameter("user_id");
        messageService.delete_message(id);
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String add_date = nowDateTime.format(formatter);
        String change_type = "messages";
        String operate = "删除了报警信息";
    }

    //信息上报
    @CrossOrigin
    @RequestMapping("report_message")
    @ResponseBody
    public void report_message(HttpServletRequest request){
        int reporter_id = Integer.parseInt(request.getParameter("reporter_id"));
        String message_id = request.getParameter("message_id");
        String reason = request.getParameter("reason");
    }
}
