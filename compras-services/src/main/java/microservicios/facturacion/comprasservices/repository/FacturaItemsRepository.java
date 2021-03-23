package microservicios.facturacion.comprasservices.repository;

import microservicios.facturacion.comprasservices.entity.FacturaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaItemsRepository extends JpaRepository<FacturaItem,Long> {

}
