package com.he123456.mvc.service;

import com.he123456.mvc.dto.DeviceResponse;
import com.he123456.mvc.entity.Device;

import java.util.List;

public interface DeviceService {
    List<DeviceResponse> findAll();
}
