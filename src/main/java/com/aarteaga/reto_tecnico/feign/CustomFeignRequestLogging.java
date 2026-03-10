package com.aarteaga.reto_tecnico.feign;

import feign.Logger;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static feign.Logger.Level.HEADERS;

@Slf4j
public class CustomFeignRequestLogging extends Logger {

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        log.info("Url --> " + request.requestTemplate().url());
        log.info("Url2 --> " + request.url());

        URI uri = null;
        try {
            uri = new URI(request.url());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        log.info("request.requestTemplate().path() --> " + uri.getPath());

        // Example: Extract path segments
        String[] segments = uri.getPath().split("/");
        System.out.println("Path segments:");
        // Example: log each path segment
        for (int i = 0; i < segments.length; i++) {
            if (segments[i].matches("\\d{7}")) {
                log.info("Es dni {}", segments[i]);
                segments[i] = segments[i].substring(0,2) + "***";
            }
            if (!segments[i].isEmpty()) {
                log(configKey, "Path segment[%d]: %s", i, segments[i]);
            }
        }

        String pathUrlUpdated = String.join("/", segments);
        log.info("Path Segments url updated: {}", pathUrlUpdated);



        log.info("-------------------------");
        log.info("request.requestTemplate().path() --> " + request.requestTemplate().path());

        String requestUrl = "";
        String requestUrlPath = ofuscateDniInUrlPath(request.requestTemplate().path());

        /*
        if(request.requestTemplate().path().contains("/users/")){
            Pattern pattern = Pattern.compile("\\d{7}");
            Matcher matcher = pattern.matcher(request.requestTemplate().path());
            requestUrlPath = matcher.replaceAll(match -> {
                String matchStr = match.group();
                return matchStr.substring(0,2) + "*****";
            });
        }*/

        log.info("REQUEST URL PATH OFUSCATED --> " + requestUrlPath);

        String requestParamsUrl = "";
        if(!request.requestTemplate().queries().isEmpty()){
            String[] parts = request.requestTemplate().url().split("\\?", 2); // "\\?" porque ? es caracter especial en regex
            log.info("PARTS 1 -> {}", parts[1]);
            String exampleOfuscated = parts[1];
            requestParamsUrl = ("?").concat(exampleOfuscated);
            //requestUrl = requestUrlPath.concat("?").concat(exampleOfuscated);
        }

        requestUrl = requestUrlPath.concat(requestParamsUrl);
        log.info("REQUEST URL UPDATED: {}", requestUrl);

        System.out.println("Query params actuales: " + request.requestTemplate().queries().get("type"));

        if(request.requestTemplate().queries().get("identityDocumentNumber") != null){

            System.out.println("Query params actuales: " + request.requestTemplate().queries());

            String documentNumber = request.requestTemplate().queries().get("identityDocumentNumber").toString();
            String documentType = request.requestTemplate().queries().get("identityDocumentType").toString();

            log.info("DocumentNumber extracted: -> {}", documentNumber);
            log.info("documentType extracted: -> {}", documentType);

            documentNumber = "12****78";

            String urlRequest = String.format("%s?identityDocumentType=%s&identityDocumentNumber=%s", request.requestTemplate().path(), documentType, documentNumber);


            /*URI uri = UriComponentsBuilder.fromHttpUrl(request.requestTemplate().path())
                    //.path("/full")
                    .queryParam("identityDocumentType", documentType)
                    .queryParam("identityDocumentNumber", documentNumber)
                    .build()
                    .encode()
                    .toUri();*/
            //RequestTemplate requestTemplate = new RequestTemplate();
            //requestTemplate.query("documentType", "DNI");
            //requestTemplate.query("documentNumber", "23423434");

            request.requestTemplate().queries();
            log.info("UsuarioClient has Type param");
            log(configKey, "---> %s %s HTTP/1.1) ", request.httpMethod().name(), urlRequest);

        }else{
            log.info("UsuarioClient hasn't Type param");
            super.logRequest(configKey, logLevel, request);
        }

        //request.requestTemplate().queries().remove("type");
        //request.requestTemplate().query("type", "valorModificado");
        //request.requestTemplate().query("documentType", "DNI");

        //System.out.println("Queries updated: " + request.requestTemplate().queries());

        //log.info("Request --> " + request.requestTemplate().url());
        //log.info("Request url --> " + request.url());

        /*
        log.info("LogRequest--> " + configKey);
        if(configKey.equals("UsuarioClient#getUserById(Long)")){
            log.info("UsuarioClient is");
            log(configKey, "---> %s HTTP/1.1) ", request.httpMethod().name());
        }else{
            log.info("UsuarioClient is not");
            super.logRequest(configKey, logLevel, request);
        }*/
        /*
        if (logLevel.ordinal() >= HEADERS.ordinal()) {
            super.logRequest(configKey, logLevel, request);
        } else {
            log.info("es menor que 2");
            //int bodyLength = 0;
            //if (request.requestBody().asBytes() != null) {
                //bodyLength = request.requestBody().asBytes().length;
            //}
            log(configKey, "---> %s %s HTTP/1.1) ", request.httpMethod().name(), request.requestTemplate().path());
        }*/
    }

    public String ofuscateDniInUrlPath(String urlPath){
        if (urlPath == null) return "";

        String DNI_REGEX = "(?i)/users/(\\d{7})";
        Pattern pattern = Pattern.compile(DNI_REGEX);
        Matcher matcher = pattern.matcher(urlPath);

        if (matcher.find()) {
            log.info("Dni value found as PathParam in URL");
            String dniFound = matcher.group(1);
            String dniOfuscated = dniFound.substring(0,2)+ "*+*";
            return urlPath.replace(dniFound, dniOfuscated);
        }
        return urlPath;
    }

    /**
     * Extrae el primer DNI de 7 dígitos encontrado en una URL.
     * @param url La URL a analizar.
     * @return El DNI encontrado o null si no hay coincidencia.
     */
    public static String extractDNI(String url) {
        if (url == null) return null;

        // Patrón: exactamente 6 dígitos
        Pattern pattern = Pattern.compile("\\b\\d{7}\\b");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(); // Devuelve el primer DNI encontrado
        }
        return null; // No encontrado
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
            throws IOException {
        if (logLevel.ordinal() >= HEADERS.ordinal()) {
            return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
        } else {
            int status = response.status();
            Request request = response.request();

            if(request.requestTemplate().queries().get("identityDocumentNumber") != null) {

                log(configKey, "<--- %s %s HTTP/1.1 %s (%sms) ", request.httpMethod().name(), request.requestTemplate().path(), status, elapsedTime);
            }else{
                log(configKey, "<--- %s %s HTTP/1.1 %s (%sms) ", request.httpMethod().name(), request.url(), status, elapsedTime);

            }
            return response;
        }
    }


    @Override
    protected void log(String configKey, String format, Object... args) {
        log.debug(format(configKey, format, args));
    }

    protected String format(String configKey, String format, Object... args) {
        return String.format(methodTag(configKey) + format, args);
    }
}