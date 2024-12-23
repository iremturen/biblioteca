package com.biblioteca.controllers;

import com.biblioteca.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value="api/login")
public class LoginController {

    private LoginService loginService;

}
