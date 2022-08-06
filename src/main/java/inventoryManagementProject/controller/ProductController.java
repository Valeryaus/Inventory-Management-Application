package inventoryManagementProject.controller;

import inventoryManagementProject.entity.Product;
import inventoryManagementProject.entity.Supplier;
import inventoryManagementProject.service.ProductService;
import inventoryManagementProject.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final SupplierService supplierService;

    public ProductController(ProductService productService, SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    // handler method to handle list products and return mode and view
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";

    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {

        // create product object to hold product form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";

    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {
        //pavaliduoti produkta prie ji saugant - sukuri nauja beansa-product validation
        // service ir jis galetu tureti metoda validate product, arba/ir paziureti validacijas Thymeleafe...
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable(value = "id") Long partNumberId, Model model) {
        model.addAttribute("product", productService.getProductById(partNumberId));
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", allSuppliers);
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable(value = "id") Long partNumberId,
                                @ModelAttribute("product") Product product,
                                Model model) {
        // get product from database by id
        Product existingProduct = productService.getProductById(partNumberId);
        // iskelti iki 68 eilute i product service nauja metoda
        existingProduct.setPartNumberId(partNumberId);
        existingProduct.setPartDescription(product.getPartDescription());
        existingProduct.setPartQty(product.getPartQty());
        existingProduct.setPartCost(product.getPartCost());
        existingProduct.setPartRetailPrice(product.getPartRetailPrice());

        Supplier supplier = new Supplier();
        supplier.setSupplierId(product.getBrandId());
        existingProduct.setSupplier(supplier);

        // save updated product object
        productService.updateProduct(existingProduct);
        return "redirect:/products";
    }

    // handler method to handle delete product request
    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long partNumberId) {
        productService.deleteProductById(partNumberId);
        return "redirect:/products";
    }
}
