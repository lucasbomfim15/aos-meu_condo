package com.aos.meu_condo.modules.user.services;

import com.aos.meu_condo.modules.user.dtos.CreateUserRequestDTO;
import com.aos.meu_condo.modules.user.dtos.CreateUserResponseDTO;
import com.aos.meu_condo.modules.user.dtos.UpdateUserRequestDTO;
import com.aos.meu_condo.modules.user.exceptions.UserFoundException;

import com.aos.meu_condo.modules.user.exceptions.UserNotFoundException;
import com.aos.meu_condo.modules.user.mappers.UserMapper;
import com.aos.meu_condo.modules.user.model.User;
import com.aos.meu_condo.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        this.userRepository.findByEmailOrUsername(createUserRequestDTO.getEmail(), createUserRequestDTO.getUsername()).ifPresent((user) -> {
            throw new UserFoundException();
        });

        User user = UserMapper.toEntity(createUserRequestDTO);

        User savedUser = userRepository.save(user);


        return UserMapper.toResponse(savedUser);

    }

    public List<CreateUserResponseDTO> listAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }


    public CreateUserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return UserMapper.toResponse(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }


    public CreateUserResponseDTO updateUser(Long id, UpdateUserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserMapper.updateEntityFromDto(user, dto);
        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }










}
