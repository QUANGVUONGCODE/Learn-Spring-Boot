package com.API.Service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.API.dto.request.UserCreationRequest;
import com.API.dto.request.UserUpdateRequest;
import com.API.dto.response.UserResponse;
import com.API.entity.User;
import com.API.exception.AppException;
import com.API.exception.ErrorCode;
import com.API.exception.TrueAppException;
import com.API.mapper.UserMapper;
import com.API.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTS);
        User user = userMapper.mapToUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUser() {
        return userRepository.findAll().stream().map(
                userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID)));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        userRepository.deleteById(id);
        throw new TrueAppException(ErrorCode.UNICATEGORIZE_EXCEPTION);
    }
}
