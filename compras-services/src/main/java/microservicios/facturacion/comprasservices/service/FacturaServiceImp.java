package microservicios.facturacion.comprasservices.service;

import lombok.extern.slf4j.Slf4j;
import microservicios.facturacion.comprasservices.entity.Factura;
import microservicios.facturacion.comprasservices.entity.FacturaItem;
import microservicios.facturacion.comprasservices.repository.FacturaItemsRepository;
import microservicios.facturacion.comprasservices.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FacturaServiceImp implements FacturaService{

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    FacturaItemsRepository facturaItemsRepository;


    @Override
    public List<Factura> findInvoiceAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura createInvoice(Factura invoice) {
        Factura facturaDB = facturaRepository.findByNumberInvoice(
                invoice.getNumberInvoice()); //valida que no exista el numero de factura
        if(facturaDB != null){
            return facturaDB; //si en caso de encontrar una factura ya registrada, retorna la misma factura
        }

        invoice.setState("CREADA");

        return facturaRepository.save(invoice);
    }

    @Override
    public Factura updateInvoice(Factura invoice) {

        Factura facturaDB = getInvoice(invoice.getId());
        if (facturaDB == null){
            return  null;
        }
        facturaDB.setCustomerId(invoice.getCustomerId());
        facturaDB.setDescription(invoice.getDescription());
        facturaDB.setNumberInvoice(invoice.getNumberInvoice());
        facturaDB.getItems().clear();
        facturaDB.setItems(invoice.getItems());
        return facturaRepository.save(facturaDB);

    }

    @Override
    public Factura deleteInvoice(Factura invoice) {
        Factura facturaDB = getInvoice(invoice.getId());
        if (facturaDB == null){
            return  null;
        }
        facturaDB.setState("DELETED");
        return facturaRepository.save(facturaDB);
    }

    @Override
    public Factura getInvoice(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }
}
