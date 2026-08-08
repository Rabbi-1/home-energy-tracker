package com.rabbi.deviceservice.service;

import com.rabbi.deviceservice.dto.DeviceDto;
import com.rabbi.deviceservice.entity.Device;
import com.rabbi.deviceservice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDto getDeviceById(Long id) {
        return null;
    }

    public DeviceDto createDevice(DeviceDto deviceDto) {
        return null;
    }

    public DeviceDto updateDevice(Long id, DeviceDto input) {
        return null;
    }

    public void deleteDevice(Long id) {
        // Implement the logic to delete a device by its ID
    }

    private DeviceDto toDto(Device device) {
        // Implement the logic to convert a Device entity to a DeviceDto
        return null;
    }

}
