package ru.jakev.irecipechatbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jakev.irecipechatbackend.entities.User;
import ru.jakev.irecipechatbackend.http.responses.UserDTO;
import ru.jakev.irecipechatbackend.mappers.UserMapper;
import ru.jakev.irecipechatbackend.services.UserService;

/**
 * @author evotintsev
 * @since 24.03.2024
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") long userId) {
        User user = userService.getUserById(userId).orElse(null);
        UserDTO userDTO = user == null ? null : userMapper.toUserDTO(user);

        return userDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email).orElse(null);
        UserDTO userDTO = user == null ? null : userMapper.toUserDTO(user);

        return userDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(userDTO);
    }
}
