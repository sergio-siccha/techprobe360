package com.prueba.tecnica.prueba.controller;

import com.prueba.tecnica.prueba.constant.HttpResponseCte;
import com.prueba.tecnica.prueba.dto.UsuarioDTO;
import com.prueba.tecnica.prueba.models.HttpResponseModel;
import com.prueba.tecnica.prueba.models.RequestCrearUsuario;
import com.prueba.tecnica.prueba.service.UsuarioService;
import com.prueba.tecnica.prueba.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("")
    public ResponseEntity<HttpResponseModel> setOne(@RequestBody RequestCrearUsuario request) {
        Object respuestaServicio = this.usuarioService.setOne(request);

        if(respuestaServicio instanceof UsuarioDTO) {
            return ResponseEntity.ok(
                    new HttpResponseModel(
                            HttpResponseCte.OPERACION_CORRECTA.getCodigo(),
                            HttpResponseCte.OPERACION_CORRECTA.getDescripcion(),
                            respuestaServicio
                    )
            );
        } else {
            return ResponseEntity.ok(ExceptionUtils.findErrorCode((String) respuestaServicio));
        }
    }

    @GetMapping("")
    public ResponseEntity<HttpResponseModel> getAll() {
        Object respuestaServicio = this.usuarioService.getAll();

        if(respuestaServicio instanceof List) {
            return ResponseEntity.ok(
                    new HttpResponseModel(
                            HttpResponseCte.OPERACION_CORRECTA.getCodigo(),
                            HttpResponseCte.OPERACION_CORRECTA.getDescripcion(),
                            respuestaServicio
                    )
            );
        } else {
            return ResponseEntity.ok(ExceptionUtils.findErrorCode((String) respuestaServicio));
        }
    }

    @PutMapping("")
    public ResponseEntity<HttpResponseModel> updateOne(@RequestBody RequestCrearUsuario request) {
        Object respuestaServicio = this.usuarioService.updateOne(request);

        if(respuestaServicio instanceof UsuarioDTO) {
            return ResponseEntity.ok(
                    new HttpResponseModel(
                            HttpResponseCte.OPERACION_CORRECTA.getCodigo(),
                            HttpResponseCte.OPERACION_CORRECTA.getDescripcion(),
                            respuestaServicio
                    )
            );
        } else {
            return ResponseEntity.ok(ExceptionUtils.findErrorCode((String) respuestaServicio));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponseModel> deleteById(@PathVariable Integer id) {
        Object respuestaServicio = this.usuarioService.deleteById(id);

        if(respuestaServicio instanceof Boolean) {
            return ResponseEntity.ok(
                    new HttpResponseModel(
                            HttpResponseCte.OPERACION_CORRECTA.getCodigo(),
                            HttpResponseCte.OPERACION_CORRECTA.getDescripcion(),
                            respuestaServicio
                    )
            );
        } else {
            return ResponseEntity.ok(ExceptionUtils.findErrorCode((String) respuestaServicio));
        }
    }
}
