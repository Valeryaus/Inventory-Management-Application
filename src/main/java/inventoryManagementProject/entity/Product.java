package inventoryManagementProject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partNumberId;

    @NotBlank
    @Column(name = "part_description", nullable = false)
    private String partDescription;

    @Column(name = "part_qty", nullable = false)
    private Integer partQty;

    @Column(name = "part_cost", nullable = false)
    private Double partCost;

    @Column(name = "part_retail_price", nullable = false)
    private Double partRetailPrice;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Transient
    // sito lauko nesaugos i duomenubaze
    private Integer brandId;

    @ManyToMany(mappedBy = "listOfProducts")
    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return partDescription;
    }
}
//po sito join'o susikure "products_orders" lentele su product_part_number_id ir su orders_order_id stulpeliais??!!
