package com.he123456.mvc.service.impl;

import com.he123456.mvc.dto.DeviceResponse;
import com.he123456.mvc.entity.Category;
import com.he123456.mvc.entity.Device;
import com.he123456.mvc.repository.DeviceRepository;
import com.he123456.mvc.service.DeviceService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<DeviceResponse> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "installationDate");

        List<Device> devices = deviceRepository.findAll(sort);

        List<DeviceResponse> deviceResponses = devices.stream().map((device) -> {
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setId(device.getDeviceId());
            deviceResponse.setDeviceCode(device.getDeviceCode());
            deviceResponse.setDeviceName(device.getDeviceName());
            deviceResponse.setInstallationDate(device.getInstallationDate());
            deviceResponse.setPrice(device.getPrice());
            String deviceStatus = device.getStatus();
            String status = switch (deviceStatus) {
                case "AC" -> "Active";
                case "IN" -> "Inactive";
                case "DR" -> "Draft";
                default -> "";
            };
            deviceResponse.setStatus(status);

            Category category = device.getCategory();
            if (category != null) {
                deviceResponse.setCategoryName(category.getCategoryName());
            }

            return deviceResponse;
        }).toList();

        return deviceResponses;
    }
}
