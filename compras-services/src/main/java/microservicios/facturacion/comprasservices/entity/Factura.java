package microservicios.facturacion.comprasservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "tlb_invoices")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;



    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //uno a muchos ..si se crea un cabecera debe crearse varios detalles
    @JoinColumn(name = "invoice_id")
    private List<FacturaItem> items;

    private String state;

    public Factura(){
        items = new ArrayList<>();
    }

    @PrePersist //se registra automaticamente antes de ingresar a nuestra base de datos
    public void prePersist() {
        this.createAt = new Date(); //guardamos la fecha de creacion
    }
}
