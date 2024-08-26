package com.nathan.crud_products.controller;

import com.nathan.crud_products.DTO.LoginRequestDTO;
import com.nathan.crud_products.DTO.RegisterRequestDTO;
import com.nathan.crud_products.domain.user.User;
import com.nathan.crud_products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        return userService.login(body);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        System.out.println("executou");
        return userService.register(body);
    }

    @GetMapping
    public List<User> returnAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> returnOneUser(@PathVariable("id") Long id) {
        return userService.findOneUser(id);
    }

    @PutMapping("/{id}")
    public User returnEditUser(@PathVariable("id") Long id, @RequestBody User body){
        return userService.editUser(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

}
