package inventoryManagementProject;

import inventoryManagementProject.entity.Customer;
import inventoryManagementProject.entity.Order;
import inventoryManagementProject.entity.Product;
import inventoryManagementProject.entity.Supplier;
import inventoryManagementProject.repository.OrderRepository;
import inventoryManagementProject.repository.ProductRepository;
import inventoryManagementProject.repository.SupplierRepository;
import inventoryManagementProject.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class InventoryManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApp.class, args);
    }

    @Bean
    public CommandLineRunner createData(ProductRepository productRepository,
                                        OrderRepository orderRepository, SupplierRepository supplierRepository, OrderService orderService) {
        return args -> {

          //  Supplier supplier1 = new Supplier();
           // supplier1.setSupplierBrand("Asus");

            Order order = new Order();
            order.setOrderDate("2022-05-20");

            Product product = new Product();
            product.setPartDescription("kompas PC");
            product.setPartQty(2);
            product.setPartCost(15.0);
            product.setPartRetailPrice(17.0);
            product.setOrders(List.of(order));

           // product.setSupplier(supplier1);

            Product product2 = new Product();
            product2.setPartDescription("MB PC");
            product2.setPartQty(3);
            product2.setPartCost(55.0);
            product2.setPartRetailPrice(77.0);
            product2.setOrders(List.of(order));

           // product2.setSupplier(supplier1);

            List<Product> someProducts = new ArrayList<>();
            someProducts.add(product);
            someProducts.add(product2);

           // supplier1.setProducts(someProducts);

           // supplierRepositoryl.save(supplier1);

            order.setListOfProducts(someProducts);

           // orderRepository.save(order);
            orderService.saveOrder(order);


        };
    }
}
