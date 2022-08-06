package inventoryManagementProject.repository;

import inventoryManagementProject.entity.Customer;
import inventoryManagementProject.entity.Order;
import inventoryManagementProject.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneOrderProduct() {
        Product product = entityManager.find(Product.class, 4L);
        Customer customer = entityManager.find(Customer.class, 2);

        Order newProduct = new Order();
        newProduct.setCustomer(customer);
      //  newProduct.setListOfProducts(product);
     //   newProduct.setProduct_qty(1); // produkto kieki iskeliau i kita klause, todel jis dabar neprieinamas

        Order savedOrderProduct = orderRepository.save(newProduct);

        assertTrue(savedOrderProduct.getOrderId() > 0);

    }

}