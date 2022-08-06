package inventoryManagementProject.repository;

import inventoryManagementProject.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // likutis nuo seno kodo ManyToMany kodo pvz - reikia istrinti
//    @Query("SELECT new inventoryManagementProject.dto.ProductSupplierResponse(s.supplierBrand, p.partDescription) FROM Supplier s JOIN s.products p")
//    public List<ProductSupplierResponse> getJoinInformation();

    // realus Query is MySql
//    "SELECT order_product.order_id, products.part_description, products.part_retail_price, order_product.product_id,
//    orders.order_date FROM inventory_management.order_product, orders, products WHERE order_product.order_id =
//    orders.order_id AND order_product.product_id = products.part_number_id ORDER BY order_product.order_id";

}
