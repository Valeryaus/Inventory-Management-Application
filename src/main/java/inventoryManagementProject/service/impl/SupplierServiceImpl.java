package inventoryManagementProject.service.impl;

import inventoryManagementProject.entity.Supplier;
import inventoryManagementProject.repository.SupplierRepository;
import inventoryManagementProject.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    // sitas Spring Beans'as naudoja tik viena konstruktoriu,
    // todel mums nereikia naudoti @Autowire anotacijos
    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(Integer supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplierById(Integer supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
