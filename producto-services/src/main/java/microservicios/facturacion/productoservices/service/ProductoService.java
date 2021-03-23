package microservicios.facturacion.productoservices.service;

import microservicios.facturacion.productoservices.entity.Categoria;
import microservicios.facturacion.productoservices.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listAllProduct();
    public Producto getProduct(Long id);

    public Producto createProduct(Producto product);
    public Producto updateProduct(Producto product);
    public  Producto deleteProduct(Long id);
    public List<Producto> findByCategory(Categoria category);
    public Producto updateStock(Long id, Double quantity);
}
