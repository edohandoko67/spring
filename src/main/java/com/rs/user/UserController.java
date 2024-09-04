package com.rs.user;


import com.rs.auth.MetaData;
import com.rs.jwt.JwtTokenUtility;
import com.rs.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired AuthenticationManager authenticationManager;

    @Autowired JwtTokenUtility jwtTokenUtility;

    @Autowired PasswordEncoder passwordEncoder;

    @Autowired UserInfoRepository userInfoRepository;

    @GetMapping("/loginn")
    public Map<String,Object> getData() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Testing");
        response.put("age", 17);
        response.put("address", "Mojokerto");

        return response;
    }

    @PostMapping("/login")
    //public ResponseEntity<LoginResponse>login(@RequestBody LoginInfo loginInfo ) {
    public ResponseEntity<?>login(@RequestBody LoginInfo loginInfo ) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));
            UserInfo user = (UserInfo) authentication.getPrincipal();
            String accessToken = jwtTokenUtility.generateAccessToken(user);
            String role = user.getRoles().toArray()[0].toString();
            MetaData metaData = new MetaData(200, "success", "Selamat Datang" + " " + user.getName());
            LoginInfo responseData = new LoginInfo(
                    user.getName(),
                    user.getPassword(),
                    user.getNumber(),
                    accessToken,
                    role,
                    user.getGender()
            );
            //LoginResponse loginResponse = new LoginResponse(metaData,user.getUsername(), accessToken, role);
            LoginResponse loginResponse = new LoginResponse(metaData, responseData);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            MetaData metaData = new MetaData(500, "gagal","Login failed");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/registrasi")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationInfo registrationInfo){
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(registrationInfo.getUsername());
            userInfo.setPassword(passwordEncoder.encode(registrationInfo.getPassword()));
            userInfo.setAddress(registrationInfo.getAddress());
            userInfo.setName(registrationInfo.getName());
            userInfo.addRole(new Role(1));
            userInfo.setNumber(registrationInfo.getNumber());
            userInfo.setGender(registrationInfo.getGender());
            MetaData metaData = new MetaData(201, "success", "Berhasil mendaftar");
            RegistrationInfo responseData = new RegistrationInfo(
                    userInfo.getUsername(),
                    userInfo.getPassword(),
                    userInfo.getName(),
                    userInfo.getAddress(),
                    userInfo.getNumber(),
                    userInfo.getGender()
            );
            RegisterResponse response = new RegisterResponse(metaData, responseData);
            userInfoRepository.save(userInfo);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            MetaData metaData = new MetaData(400, "gagal", "Gagal register");
            ErrorResponse errorResponse = new ErrorResponse(metaData);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }


    /*UserService userService = new UserService();

    @GetMapping("/")
    public ResponseEntity<Vector<MyUser>> test() {
        return new ResponseEntity<Vector<MyUser>>(userService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<String>User(@RequestBody MyUser user){
        if (user!=null){
            userService.User(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

     */
}
