package com.aarteaga.reto_tecnico.clients;

import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "usuarioClient", url = "https://gorest.co.in/public/v2", configuration = FeignConfig.class)
public interface UsuarioClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/")
    List<GorestUserDto> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = "application/json")
    GorestUserDto getUserById(@PathVariable("id") Long userId);
}