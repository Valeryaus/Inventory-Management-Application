package inventoryManagementProject.controller;

import inventoryManagementProject.entity.Supplier;
import inventoryManagementProject.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // handler method to handle list...
    @GetMapping("/suppliers")
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers";
    }

    @GetMapping("/suppliers/new")
    public String createSupplierForm(Model model) {
        // create supplier object to hold supplier form data
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "create_supplier";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        //pavaliduoti produkta prie ji saugant - sukuri nauja beansa-supplier validation
        // service ir jis galetu tureti metoda validate supplier, arba/ir paziureti validacijas Thymeleafe...
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String editSupplierForm(@PathVariable(value = "id") Integer supplierId, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(supplierId));
        return "edit_supplier";

    }

    @PostMapping("/suppliers/{id}")
    public String updateSupplier(@PathVariable(value = "id") Integer supplierId,
                                 @ModelAttribute("supplier") Supplier supplier,
                                 Model model) {
        // get supplier from database by id
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);
        // iskelti iki 68 eilute i supplier service nauja metoda
        existingSupplier.setSupplierId(supplierId);
        existingSupplier.setSupplierBrand(supplier.getSupplierBrand());
        // save updated supplier object
        supplierService.updateSupplier(existingSupplier);
        return "redirect:/suppliers";
    }

    // handler method to handle delete supplier request
    @GetMapping("/suppliers/{id}")
    public String deleteSupplier(@PathVariable(value = "id") Integer supplierId) {
        supplierService.deleteSupplierById(supplierId);
        return "redirect:/suppliers";
    }
}





