package inventoryManagementProject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    @NotBlank
    @Column(name = "supplier_brand", nullable = false)
    private String supplierBrand;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products; // sukuriamas lenteliu sujungimui

}