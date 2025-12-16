package com.aarteaga.reto_tecnico.infraestructure;

import com.aarteaga.reto_tecnico.domain.Account;
import com.aarteaga.reto_tecnico.domain.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public List<Account> list() {
        Account account1 = new Account();
        account1.setId(new Long(1));
        account1.setAccountNumber("9876543210");
        account1.setType("OWN");
        account1.setAmmount(1500.75);
        account1.setCurrency("USD");


        Account account2 = new Account();
        account2.setId(new Long(2));
        account2.setAccountNumber("9876543210");
        account2.setType("EXTERNAL");
        account2.setAmmount(1500.75);
        account2.setCurrency("USD");

        Account account3 = new Account();
        account3.setId(new Long(3));
        account3.setAccountNumber("9876543210");
        account3.setType("EXTERNAL");
        account3.setAmmount(1500.75);
        account3.setCurrency("USD");


        List<Account> accountList = Arrays.asList(account2, account1, account3);

        return accountList;
    }
}
