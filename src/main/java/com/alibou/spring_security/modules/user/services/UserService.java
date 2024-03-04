package com.alibou.spring_security.modules.user.services;

import com.alibou.spring_security.modules.user.entities.User;
import com.alibou.spring_security.modules.user.helpers.ChangePasswordRequest;
import com.alibou.spring_security.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder; // tuy la lấy từ trong security nhưng đã dđược overrite và bean rồi nên là lấy theo của mình

    @Autowired
    private final UserRepository repository;


    public void changePassword(ChangePasswordRequest request, Principal connnectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connnectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // check if the two passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save database
        repository.save(user);
    }
}
