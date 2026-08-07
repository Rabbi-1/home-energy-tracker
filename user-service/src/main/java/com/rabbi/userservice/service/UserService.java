package com.rabbi.userservice.service;

import com.rabbi.userservice.dto.UserDto;
import com.rabbi.userservice.entity.User;
import com.rabbi.userservice.mapper.UserMapper;
import com.rabbi.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto input) {
        final User createdUser = User.builder()
                .name(input.getName())
                .surname(input.getSurname())
                .email(input.getEmail())
                .address(input.getAddress())
                .alerting(input.isAlerting())
                .energyAlertingThreshold(input.getEnergyAlertingThreshold())
                .build();
        final User savedUser = userRepository.save(createdUser);

        return userMapper.toDto(savedUser);

    }
}


