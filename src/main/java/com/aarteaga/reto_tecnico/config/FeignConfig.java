package com.aarteaga.reto_tecnico.config;


import com.aarteaga.reto_tecnico.feign.CustomFeignRequestLogging;
import com.aarteaga.reto_tecnico.feign.CustomFeignTest;
import lombok.Generated;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Generated
    private static final Logger log = LoggerFactory.getLogger(FeignConfig.class);

    @Bean
    feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.BASIC;
    }

    /*
    @Bean
    public CustomFeignRequestLogging customFeignRequestLogging() {
        return new CustomFeignRequestLogging();
    }

*/
    @Bean
    feign.Logger customFeignLogger() {
        return new feign.Logger() {
            @Override
            protected void log(String s, String s1, Object... objects) {

            }
        };
    }

    /*@Bean
    public Client Client() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        log.info("Configuring Okhttpclient Builder with Loggin Interceptor");
        if(log.isDebugEnabled()){
            log.info("Is debug enabled");
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }else{
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder builder = (new OkHttpClient.Builder()).addInterceptor(httpLoggingInterceptor);

        builder.connectTimeout(10, TimeUnit.SECONDS) // Connection timeout
                .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
                .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
                .retryOnConnectionFailure(true);   // Retry

        return new feign.okhttp.OkHttpClient(builder.build());
    }*/
}
