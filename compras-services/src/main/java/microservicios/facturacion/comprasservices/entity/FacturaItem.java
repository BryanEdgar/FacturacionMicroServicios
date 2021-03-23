package microservicios.facturacion.comprasservices.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data //genera los getters y setters
@Table(name = "tbl_invoce_items")
public class FacturaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;


    @Transient
    private Double subTotal;


    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public FacturaItem(){ //inicializa los valores en 0
        this.quantity=(double) 0;
        this.price=(double) 0;

    }
}
