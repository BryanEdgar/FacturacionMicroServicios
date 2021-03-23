package microservicios.facturacion.productoservices.service;


import lombok.RequiredArgsConstructor;
import microservicios.facturacion.productoservices.entity.Categoria;
import microservicios.facturacion.productoservices.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImp implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listAllCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoria(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria createProduct(Categoria categoria) {
        categoria.setName("Plasticos");
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria updateProduct(Categoria categoria) {
        Categoria categoriadb = getCategoria(categoria.getId());
        if (null == categoriadb){
            return null;
        }
        String des = categoria.getName().concat(" CONCATENACION");
        categoriadb.setName(des);
        return categoriaRepository.save(categoriadb);
    }

    @Override
    public Categoria deleteProduct(Long id) {
        Categoria categoriadb = getCategoria(id);
        if (null == categoriadb){
            return null;
        }
        return null; //aqui se debe cambiar el estado ..para hacer eliminacion logica
    }
}
