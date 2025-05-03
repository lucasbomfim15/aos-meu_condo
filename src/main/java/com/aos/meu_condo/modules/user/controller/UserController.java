package com.aos.meu_condo.modules.user.controller;

import com.aos.meu_condo.modules.user.dtos.CreateUserRequestDTO;
import com.aos.meu_condo.modules.user.dtos.CreateUserResponseDTO;
import com.aos.meu_condo.modules.user.dtos.UpdateUserRequestDTO;
import com.aos.meu_condo.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        var result =  this.userService.createUser(createUserRequestDTO);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/")
    public ResponseEntity<List<CreateUserResponseDTO>> listAllUsers() {
        List<CreateUserResponseDTO> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> getUserById(@PathVariable Long id) {
        CreateUserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> updateUser(@PathVariable Long id,
                                                            @RequestBody UpdateUserRequestDTO dto) {
        CreateUserResponseDTO updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
