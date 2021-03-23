package microservicios.facturacion.clienteservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import microservicios.facturacion.clienteservices.entity.Cliente;
import microservicios.facturacion.clienteservices.entity.Region;
import microservicios.facturacion.clienteservices.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    //LISTAR PRODUCTOS POR CATEGORIA
    @GetMapping
    public ResponseEntity <List<Cliente>> listaClientes(@RequestParam(name = "regionId",
            required = false) Long regionId){

        List<Cliente> clientes = new ArrayList<>();
        if (null == regionId){
            clientes = clienteService.findCustomerAll();
            if (clientes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            clientes = clienteService.findCustomersByRegion(Region.builder().id(regionId).build());
            if (clientes.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(clientes);
    }

    //----BUSCA CLIENTE POR ID
    @GetMapping(value = "/{id}") //Busca cliente por el ID
    public ResponseEntity<Cliente> getCustomer(@PathVariable("id") Long id){
        Cliente cliente = clienteService.getCustomer(id);
        if(null == cliente){
            return ResponseEntity.notFound().build();
        }
        System.out.println("cliente"+cliente.getFirstName().toString());
        return ResponseEntity.ok(cliente);
    }

    // -------------------Create a Customer-------------------------------------------

    @PostMapping
    public ResponseEntity<Cliente> createCustomer(@Valid @RequestBody Cliente customer, BindingResult result) {
        log.info("Creating Customer : {}", customer);
        System.out.println("cleinte nuevo:"+customer.getFirstName().toString());

        if (result.hasErrors()){
            System.out.println("cliente:===");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Cliente customerDB = clienteService.createCustomer (customer);

        return  ResponseEntity.status( HttpStatus.CREATED).body(customerDB);
    }

    // ------------------- ACTUALIZAR A CLIENTE ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Cliente customer) {
        log.info("Updating Customer with id {}", id);

        Cliente currentCustomer = clienteService.getCustomer(id);

        if ( null == currentCustomer ) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer=clienteService.updateCustomer(customer);
        return  ResponseEntity.ok(currentCustomer);
    }

    // ------------------- Delete a Cliente-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deleteCustomer(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Customer with id {}", id);

        Cliente customer = clienteService.getCustomer(id);
        if ( null == customer ) {
            log.error("Unable to delete. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer = clienteService.deleteCustomer(customer);
        return  ResponseEntity.ok(customer);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMensaje errorMessage = ErrorMensaje.builder()
                .codigo("01")
                .mensajes(errors).build();
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
