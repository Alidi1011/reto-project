package com.aarteaga.reto_tecnico.feign;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueryParamInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        //log.info("INTERCEPTOR");
        //log.info(template.getRequestVariables().toArray().toString());;
        //log.info(template.bodyTemplate());
        //log.info(template.path());
        // Leer query params actuales
        /*var queries = template.queries(); // Map<String, Collection<String>>
        System.out.println("Query params actuales: " + queries);*/

        // Agregar uno nuevo
        //template.query("identityDocumentType", "DNI");
        //template.query("identityDocumentNumber", "1234567");

        //template.header("X-Original-Path-Template", template.path());
    }

}
