package com.prueba.tecnica.prueba.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponseModel {
    private String codigo;
    private String mensaje;
    private Object objeto;
}
