package inventoryManagementProject.service;

import inventoryManagementProject.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product saveProduct(Product product);

    Product getProductById(Long partNumberId);

    Product updateProduct (Product product);

    void deleteProductById(Long partNumberId);
}
