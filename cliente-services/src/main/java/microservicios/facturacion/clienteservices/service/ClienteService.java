package microservicios.facturacion.clienteservices.service;



import microservicios.facturacion.clienteservices.entity.Cliente;
import microservicios.facturacion.clienteservices.entity.Region;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findCustomerAll();
    public List<Cliente> findCustomersByRegion(Region region);

    public Cliente createCustomer(Cliente customer);
    public Cliente updateCustomer(Cliente customer);
    public Cliente deleteCustomer(Cliente customer);
    public  Cliente getCustomer(Long id);
}
