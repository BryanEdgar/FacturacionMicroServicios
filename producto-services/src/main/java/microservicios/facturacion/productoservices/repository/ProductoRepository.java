package microservicios.facturacion.productoservices.repository;


import microservicios.facturacion.productoservices.entity.Categoria;
import microservicios.facturacion.productoservices.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    public List<Producto> findByCategoria(Categoria categoria);

}
