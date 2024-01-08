package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.domain.user.UserDTO;
import com.exemple.simplifiedpicpay.domain.user.User;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody UserDTO request) throws BusinessErrorException {
        var response = userService.createUser(request);
        return ResponseEntity.ok(response);
        //TODO IMPLEMENTAR HATEOAS
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable("id") Long id){
        var response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }
}
