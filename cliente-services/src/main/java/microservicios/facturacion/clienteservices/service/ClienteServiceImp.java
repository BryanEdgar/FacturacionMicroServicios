package microservicios.facturacion.clienteservices.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservicios.facturacion.clienteservices.entity.Cliente;
import microservicios.facturacion.clienteservices.entity.Region;
import microservicios.facturacion.clienteservices.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class ClienteServiceImp implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findCustomerAll() {

        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findCustomersByRegion(Region region) {
        return clienteRepository.findByRegion(region);
    }

    @Override
    public Cliente createCustomer(Cliente customer) {
        Cliente clienteNuevo = clienteRepository.findByNumberID(customer.getNumberID());
        if(clienteNuevo == null){
            return null;
        }
       customer.setState("Creado");
        clienteNuevo =  clienteRepository.save(clienteNuevo);
        return clienteNuevo;
    }

    @Override
    public Cliente updateCustomer(Cliente customer) {
        Cliente clienteDB = getCustomer(customer.getId());
        if (clienteDB == null){
            return  null;
        }
        clienteDB.setFirstName(customer.getFirstName());
        clienteDB.setLastName(customer.getLastName());
        clienteDB.setNumberID(customer.getNumberID());
        clienteDB.setEmail(customer.getEmail());
        clienteDB.setPhotoUrl(customer.getPhotoUrl());

        return clienteRepository.save(clienteDB);
    }

    @Override
    public Cliente deleteCustomer(Cliente customer) {
        Cliente clienteDB = getCustomer(customer.getId());
        if (clienteDB ==null){
            return  null;
        }
        customer.setState("DELETED");
        return clienteRepository.save(clienteDB);
    }

    @Override
    public Cliente getCustomer(Long id) {
        return  clienteRepository.findById(id).orElse(null);
    }
}
