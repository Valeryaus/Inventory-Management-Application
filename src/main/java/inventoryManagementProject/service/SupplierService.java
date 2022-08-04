package inventoryManagementProject.service;

import inventoryManagementProject.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier saveSupplier(Supplier supplier);

    Supplier getSupplierById(Integer supplierId);

    Supplier updateSupplier(Supplier supplier);

    void deleteSupplierById(Integer supplierId);


}
