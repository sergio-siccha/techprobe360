package com.prueba.tecnica.prueba.util;

import com.prueba.tecnica.prueba.constant.HttpResponseCte;
import com.prueba.tecnica.prueba.models.HttpResponseModel;

public class ExceptionUtils {

    public static HttpResponseModel findErrorCode(String code) {
        HttpResponseModel response = new HttpResponseModel(HttpResponseCte.ERROR_GENERAL.getCodigo(),
                HttpResponseCte.ERROR_GENERAL.getDescripcion(), null);
        for (HttpResponseCte responseTmp : HttpResponseCte.values()) {
            if (responseTmp.getCodigo().equals(code)) {
                response.setCodigo(response.getCodigo());
                response.setMensaje(response.getMensaje());
            }
        }

        return response;
    }
}
