package microservicios.facturacion.productoservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import microservicios.facturacion.productoservices.entity.Categoria;
import microservicios.facturacion.productoservices.entity.Producto;
import microservicios.facturacion.productoservices.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //LISTAR PRODUCTOS POR CATEGORIA
    @GetMapping
    public ResponseEntity <List <Producto>> listaProductos(@RequestParam(name = "categoriaId",
            required = false) Long categoriaId){

        List<Producto> productos = new ArrayList<>();
        if (null == categoriaId){
            productos = productoService.listAllProduct();
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            productos = productoService.findByCategory(Categoria.builder().id(categoriaId).build());
            if (productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);
    }

//    //----BUSCA PRODUCTOS POR ID
    @GetMapping(value = "/{id}") //Busca productos por el ID
    public  ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
        Producto producto = productoService.getProduct(id);
        if(null == producto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

//    //----CREA UN NUEVO PRODUCTO
    @PostMapping
    public ResponseEntity<Producto> createProducto (@Valid @RequestBody Producto producto, BindingResult result){
       if (result.hasErrors()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatoMensaje(result));
       }

        Producto productonuevo = productoService.createProduct(producto);

       return ResponseEntity.status(HttpStatus.CREATED).body(productonuevo);
    }
//
//    //-----ACTUALIZA UN NUEVO PRODUCTO
    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id,@RequestBody Producto producto){
        producto.setId(id);

        Producto productoUpdate = productoService.updateProduct(producto);

        if(productoUpdate == null){
            return ResponseEntity.notFound().build();
        }
    return ResponseEntity.ok(productoUpdate);
    }
//
//    //-----ELIMINA UN NUEVO PRODUCTO
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProducto (@PathVariable("id") Long id){

        Producto productoDelete = productoService.deleteProduct(id);

        if(productoDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDelete);
    }
//
//    //------ACTUALIZA EL STOCK DE UN PRODUCTO
    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Producto> updateStock(@PathVariable("id") Long id, @RequestParam(name = "stock",required = true) Double stock){
        Producto producto = productoService.updateStock(id,stock);

        if(producto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }
//
//
    private String formatoMensaje(BindingResult resultado){

        List<Map<String,String>> errors = resultado.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMensaje errorMessage = ErrorMensaje.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
