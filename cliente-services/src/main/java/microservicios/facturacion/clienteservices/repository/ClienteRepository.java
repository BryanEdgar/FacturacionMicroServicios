package microservicios.facturacion.clienteservices.repository;


import microservicios.facturacion.clienteservices.entity.Cliente;
import microservicios.facturacion.clienteservices.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    public Cliente findByNumberID(String numberID);
    public List<Cliente> findByLastName(String lastName);
    public List<Cliente> findByRegion(Region region);

}
