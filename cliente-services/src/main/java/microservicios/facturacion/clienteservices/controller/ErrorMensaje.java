package microservicios.facturacion.clienteservices.controller;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMensaje {
    private String codigo;
    private List<Map<String,String>> mensajes;

}
