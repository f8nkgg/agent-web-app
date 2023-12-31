package com.example.agent.service;

import com.example.agent.dto.UserDTO;
import com.example.agent.entity.User;
import com.example.agent.mapper.UserMapper;
import com.example.agent.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }
    public UserDTO updateUser(String name) {
        User user = userRepository.findById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(name);
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}