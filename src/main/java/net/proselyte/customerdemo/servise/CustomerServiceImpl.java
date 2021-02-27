package net.proselyte.customerdemo.servise;

import lombok.extern.slf4j.Slf4j;
import net.proselyte.customerdemo.model.Customer;
import net.proselyte.customerdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        //log.info("IN CustomerServiceImpl getById {}", id);
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
         //log.info("IN CustomerServiceImpl save {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        //log.info("IN CustomerServiceImpl delete {}", id);
        customerRepository.delete(id);
    }

    @Override
    public List<Customer> getAll() {
        //log.info("IN CustomerServiceImpl getAll");
        return customerRepository.findAll();
    }
}
