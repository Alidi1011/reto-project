package com.aarteaga.reto_tecnico.application;

import com.aarteaga.reto_tecnico.clients.TestClient;
import com.aarteaga.reto_tecnico.clients.UsuarioClient;
import com.aarteaga.reto_tecnico.domain.Account;
import com.aarteaga.reto_tecnico.domain.AccountRepository;
import com.aarteaga.reto_tecnico.shared.ExceptionType;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Value("${service.external.gorest.url}")
    private String urlGorest;

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

    @Override
    public GorestUserDto getUserById(Long id) {

        //String idOfuscated = id.toString().replace("8", "*");

        // Build URI with query parameters

        /*log.info("URL GOREST: -->" + urlGorest);
        URI uri = UriComponentsBuilder.fromHttpUrl(urlGorest)
                .path("/full")
                .queryParam("identityDocumentType", id)
                .queryParam("identityDocumentNumber", id)
                .build()
                .encode()
                .toUri();

        log.info("URI: --> {}", uri);*/


        //String urlRequest = String.format("%s%s?identiyDocumentType=%s&identityDocumentNumber=%s", urlGorest, "/full", idOfuscated, idOfuscated);

        //log.info(String.format("[UsuarioClient#getUserById] ---> %s %s HTTP/1.1", "GET", uri));

        return usuarioClient.getUserById(id);
    }
}
