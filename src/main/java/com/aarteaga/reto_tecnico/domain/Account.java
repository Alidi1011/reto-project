package com.aarteaga.reto_tecnico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private Long id;
    private String accountNumber;
    private String type;
    private Double ammount;
    private String currency;


}
