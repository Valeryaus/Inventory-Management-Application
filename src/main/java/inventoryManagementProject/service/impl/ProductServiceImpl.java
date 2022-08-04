package inventoryManagementProject.service.impl;

import inventoryManagementProject.entity.Product;
import inventoryManagementProject.repository.ProductRepository;
import inventoryManagementProject.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long partNumberId) {
        return productRepository.findById(partNumberId).get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // veliau ideti validacijas kad patikrint ar yra produktas pries ji trinant
    @Override
    public void deleteProductById(Long partNumberId) {
       productRepository.deleteById(partNumberId);

    }
}
