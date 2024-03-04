package com.alibou.spring_security.modules.user.controller;

import com.alibou.spring_security.modules.user.helpers.ChangePasswordRequest;
import com.alibou.spring_security.modules.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController{

    @Autowired
    private final UserService service;


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdmin() {
        return "admin";
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String showUserPage() {
        return "user";
    }


    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser // thông tin user được set ở file JwtFilter, khi được set thì ở cứ security luôn có thông tin user
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
