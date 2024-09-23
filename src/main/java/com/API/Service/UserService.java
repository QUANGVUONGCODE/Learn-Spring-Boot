package com.API.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.API.dto.request.UserCreationRequest;
import com.API.dto.request.UserUpdateRequest;
import com.API.dto.response.UserResponse;
import com.API.entity.User;
import com.API.enums.Role;
import com.API.exception.AppException;
import com.API.exception.ErrorCode;
import com.API.exception.TrueAppException;
import com.API.mapper.UserMapper;
import com.API.repository.RoleRepository;
import com.API.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTS);
        User user = userMapper.mapToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        // user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')") // thoa dieu kien moi duoc vao ben trong method
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream().map(
                userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name") // xu ly ben trong method moi kiem tra dieu kien
    public UserResponse getUser(String id) {

        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID)));
    }

    public UserResponse getMyinfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        userRepository.deleteById(id);
        throw new TrueAppException(ErrorCode.UNICATEGORIZE_EXCEPTION);
    }
}
