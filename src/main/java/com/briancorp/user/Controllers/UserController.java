package com.briancorp.user.Controllers;

import com.briancorp.user.Jwt.JwtTokenUtility;
import com.briancorp.user.Payload.LoginInfo;
import com.briancorp.user.Payload.LoginResponse;
import com.briancorp.user.Models.Usere;
import com.briancorp.user.Payload.RegistInfo;
import com.briancorp.user.Repositories.UsereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UsereRepository usereRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtility jwtTokenUtility;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Jawaban UTS no. 6
    @PostMapping("/register")
    public ResponseEntity<?> registNewUser(@RequestBody @Valid RegistInfo registInfo) {
        Usere savedUsere = new Usere();
        savedUsere.setUsername(registInfo.getUsername());
        savedUsere.setPassword(passwordEncoder.encode(registInfo.getPassword()));
        savedUsere.setName(registInfo.getName());
        savedUsere.setAddress(registInfo.getAddress());
        usereRepository.save(savedUsere);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginInfo loginInfo) {
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword())
            );

            Usere usere = (Usere) authentication.getPrincipal();
            String accessToken = jwtTokenUtility.generateAccessToken(usere);
            LoginResponse loginResponse = new LoginResponse(usere.getUsername(), accessToken);
            return ResponseEntity.ok(loginResponse);
    }
}
