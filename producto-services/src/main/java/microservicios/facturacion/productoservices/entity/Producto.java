package microservicios.facturacion.productoservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    private String description;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;

    private Double price;

    private String status;

    @Column(name = "create_at") //cuando el nombre de la columna en la DB es diferente
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")

    @NotNull(message = "La categoria no puede ser vacia")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;

}
