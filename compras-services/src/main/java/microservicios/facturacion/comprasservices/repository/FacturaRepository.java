package microservicios.facturacion.comprasservices.repository;

import microservicios.facturacion.comprasservices.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
    public List<Factura> findByCustomerId(Long customerId );
    public Factura findByNumberInvoice(String numberInvoice);
}
