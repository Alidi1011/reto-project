package com.aarteaga.reto_tecnico.clients;

import com.aarteaga.reto_tecnico.config.FeignConfig;
import com.aarteaga.reto_tecnico.shared.GorestUserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "testClient", url = "${service.external.gorest.url}", configuration = FeignConfig.class)
public interface TestClient {

    @GetMapping(value = "/users/")
    @Headers("Content-Type: application/json")
    List<GorestUserDto> getUsers();

    @GetMapping(value = "/users/{id}", produces = "application/json")
    GorestUserDto getUserById(@PathVariable("id") Long userId);
}