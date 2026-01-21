package com.sumukh.fabricos.controllers;

import com.sumukh.fabricos.Dtos.AuthRequestDto;
import com.sumukh.fabricos.Entities.Users;
import com.sumukh.fabricos.Repositories.UsersRepository;
import com.sumukh.fabricos.mappers.UsersMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    AuthController(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();
        String password = authRequestDto.getPassword();

        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(password.equals(user.getPassword())) {
            System.out.println("Login successful");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else  {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();

        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            System.out.println("User already exists");
            return ResponseEntity.badRequest().build();
        }

        System.out.println("User Created");
        usersRepository.save(usersMapper.toUsers(authRequestDto));
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
