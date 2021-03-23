package microservicios.facturacion.productoservices.service;



import microservicios.facturacion.productoservices.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> listAllCategoria();
    public Categoria getCategoria(Long id);
    public Categoria createProduct(Categoria categoria);
    public Categoria updateProduct(Categoria categoria);
    public  Categoria deleteProduct(Long id);
}
