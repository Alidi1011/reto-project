package com.aarteaga.reto_tecnico.application;

import com.aarteaga.reto_tecnico.clients.UsuarioClient;
import com.aarteaga.reto_tecnico.domain.Account;
import com.aarteaga.reto_tecnico.domain.AccountRepository;
import com.aarteaga.reto_tecnico.shared.ExceptionType;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UsuarioClient usuarioClient;


    @Override
    public List<Account> findByType(String type) throws Exception {

        if(type.equals("OWN") || type.equals("EXTERNAL") || type.isEmpty()){
            if(type.isEmpty()) {
                return accountRepository.list();
            }
            return accountRepository.list().stream().filter(ac -> ac.getType().equals(type)).collect(Collectors.toList());
        }else{
            throw new ExceptionType();
        }

    }

    @Override
    public List<GorestUserDto> getUsers() {
        return usuarioClient.getUsers();
    }
}
