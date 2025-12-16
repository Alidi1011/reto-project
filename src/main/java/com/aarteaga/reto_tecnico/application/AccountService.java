package com.aarteaga.reto_tecnico.application;


import com.aarteaga.reto_tecnico.domain.Account;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;

import java.util.List;

public interface AccountService {

    public List<Account> findByType(String type) throws Exception;
    public List<GorestUserDto> getUsers();


}
