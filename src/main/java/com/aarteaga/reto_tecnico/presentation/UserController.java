package com.aarteaga.reto_tecnico.presentation;


import com.aarteaga.reto_tecnico.application.AccountService;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<GorestUserDto> getUsers(){
        return accountService.getUsers();
    }

    @GetMapping("/{id}")
    public GorestUserDto getUsers(@PathVariable Long id){
        return accountService.getUserById(id);
    }
}
