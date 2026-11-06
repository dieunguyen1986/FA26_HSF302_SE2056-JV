package com.he123456.mvc.controller;

import com.he123456.mvc.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping({"/", "/devices"})
    public String listDevice(Model model) {
        model.addAttribute("devices", deviceService.findAll());

        return "device-list";
    }
}
