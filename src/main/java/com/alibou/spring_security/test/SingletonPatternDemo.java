package com.alibou.spring_security.test;

import java.util.List;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        Supplier supplier = Supplier.getInstance();
        supplier.create(supplier);
        supplier.update(supplier);
        supplier.delete(supplier);

        List<Supplier> foundSupplier = supplier.searchSupplierByName("John Doe");
    }
}
