package com.prueba.tecnica.prueba.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpResponseCte {

    OPERACION_CORRECTA("00","Operacion Correcta"),
    ERROR_GENERAL("01","Ocurrio un error general al realizar la operacion")
    ;


    private String codigo;
    private String descripcion;
}
