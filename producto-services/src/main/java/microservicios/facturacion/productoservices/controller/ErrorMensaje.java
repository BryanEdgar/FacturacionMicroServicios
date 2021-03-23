package microservicios.facturacion.productoservices.controller;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorMensaje {
    private String code ;
    private List<Map<String,String>> messages ;

}
