package com.rabbi.deviceservice;

import com.rabbi.deviceservice.entity.Device;
import com.rabbi.deviceservice.model.DeviceType;
import com.rabbi.deviceservice.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
class DeviceServiceApplicationTests {

    public static final int DEVICE_COUNT = 20;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void createDevices() {
        for (int i = 1; i <= DEVICE_COUNT; i++) {
            var device = Device.builder()
                    .name("Device " + i)
                    .type(DeviceType.values()[i % DeviceType.values().length])
                    .location("Location " + (i % 5 + 1)) // Assigning location in a round-robin fashion for demonstration
                    .userId((long) ((i % 10) + 1)) // Assigning userId in a round-robin fashion for demonstration
                    .build();
            deviceRepository.save(device);
        }
        log.info("Created {} devices", DEVICE_COUNT);
    }

}
