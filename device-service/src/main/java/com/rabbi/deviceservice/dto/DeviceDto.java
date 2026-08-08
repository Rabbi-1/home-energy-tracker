package com.rabbi.deviceservice.dto;

import com.rabbi.deviceservice.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private Long id;
    private String name;
    private DeviceType type;
    private String status;
    private String location;
}
