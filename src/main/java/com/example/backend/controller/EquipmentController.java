package com.example.backend.controller;

import com.example.backend.bean.Camera;
import com.example.backend.bean.SafeEquipment;
import com.example.backend.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentController {
    @Autowired
    private CameraService cameraService;


    @CrossOrigin
    @RequestMapping("getSurveillanceEquipments")
    @ResponseBody
    public List<Camera> getSurveillanceEquipments(String location){
        List<Camera> list = cameraService.getSurveillanceEquipments(location);
        return list;
    }
}
