package net.proselyte.customerdemo.servise;

import net.proselyte.customerdemo.model.Customer;

import java.util.List;

/**
 * This interfase for methods working with Customers
 * @see CustomerServiceImpl
 * @author Maxim Eliseev
 * @version 1.0
 */
public interface CustomerService {

    /**@see CustomerServiceImpl*/
    Customer getById(Long id); //найти по ID

    /**@see CustomerServiceImpl*/
    void save (Customer customer); //сохранить customer

    /**@see CustomerServiceImpl*/
    void delete(Long id); // удалить

    /**@see CustomerServiceImpl*/
    List<Customer> getAll(); //получить всех
}
