package com.example.backend.service;

import com.example.backend.bean.Camera;
import com.example.backend.bean.SafeEquipment;
import com.example.backend.mapper.CameraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {
    @Autowired
    private CameraMapper cameraMapper;

    public List<Camera> getSurveillanceEquipments(String location){
        List<Camera> list = cameraMapper.getSurveillanceEquipments(location);
        return list;
    }

}
