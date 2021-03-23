package microservicios.facturacion.comprasservices.service;

import microservicios.facturacion.comprasservices.entity.Factura;

import java.util.List;

public interface FacturaService {

    public List<Factura> findInvoiceAll();

    public Factura createInvoice(Factura invoice);
    public Factura updateInvoice(Factura invoice);
    public Factura deleteInvoice(Factura invoice);

    public Factura getInvoice(Long id);
}
