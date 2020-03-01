package com.springsecurity.jwt.contoller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

   @GetMapping
   public String getMessageForAdmin() {
       return "Hello, Admin";
   }

}
