package net.proselyte.customerdemo.servise;

import net.proselyte.customerdemo.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Long id); //найти по ID

    void save (Customer customer); //сохранить customer

    void delete(Long id); // удалить

    List<Customer> getAll(); //получить всех
}
