package com.aarteaga.reto_tecnico.clients;

import com.aarteaga.reto_tecnico.config.FeignConfig;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "usuarioClient", url = "https://gorest.co.in/public/v2", configuration = FeignConfig.class)
public interface UsuarioClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<GorestUserDto> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    GorestUserDto getUserById(@PathVariable("id") Long userId);
}