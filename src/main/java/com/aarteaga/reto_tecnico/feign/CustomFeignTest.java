package com.aarteaga.reto_tecnico.feign;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFeignTest extends Logger {
    @Override
    protected void log(String configKey, String format, Object... args) {
        String message = String.format(methodTag(configKey).concat(format), args);
        log.debug(message);
    }
}