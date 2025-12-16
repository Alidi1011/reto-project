package com.aarteaga.reto_tecnico.presentation;


import com.aarteaga.reto_tecnico.application.AccountService;
import com.aarteaga.reto_tecnico.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/bank-accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public List<Account> findByType(@RequestParam String type) throws Exception {
        return accountService.findByType(type);
    }
}
