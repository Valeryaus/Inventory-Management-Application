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

    // naujas join'as iki cia viskas veikia ;)

    @ManyToMany(mappedBy = "listOfProducts")
    private List<Order> orders = new ArrayList<>();


    @Override
    public String toString() {
        return partDescription;
    }
}

// prideda papildoma stulpelį prie "orders" lenteles, bet su 0 (nuliais)...
//  private int product_qty = 1;

//po sito join'o susikure "products_orders" lentele su product_part_number_id ir su orders_order_id stulpeliais??!!


//    public Long getPartNumberId() {
//        return partNumberId;
//    }
//
//    public void setPartNumberId(Long partNumberId) {
//        this.partNumberId = partNumberId;
//    }
//
//    public String getPartDescription() {
//        return partDescription;
//    }
//
//    public void setPartDescription(String partDescription) {
//        this.partDescription = partDescription;
//    }
//
//    public Integer getPartQty() {
//        return partQty;
//    }
//
//    public void setPartQty(Integer partQty) {
//        this.partQty = partQty;
//    }
//
//    public Double getPartCost() {
//        return partCost;
//    }
//
//    public void setPartCost(Double partCost) {
//        this.partCost = partCost;
//    }
//
//    public Double getPartRetailPrice() {
//        return partRetailPrice;
//    }
//
//    public void setPartRetailPrice(Double partRetailPrice) {
//        this.partRetailPrice = partRetailPrice;
//    }
//
//    public Product(Long partNumberId, String partDescription, Integer partQty, Double partCost, Double partRetailPrice) {
//        this.partNumberId = partNumberId;
//        this.partDescription = partDescription;
//        this.partQty = partQty;
//        this.partCost = partCost;
//        this.partRetailPrice = partRetailPrice;
//    }
//
//    public Product() {
//    }

