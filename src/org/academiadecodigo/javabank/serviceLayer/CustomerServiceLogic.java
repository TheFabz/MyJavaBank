package org.academiadecodigo.javabank.serviceLayer;
import org.academiadecodigo.javabank.model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomerServiceLogic implements CustomerService{

    private HashMap<Integer, Customer> customers = new HashMap<>();

    @Override
    public Customer get(Integer id) {
        return null;
    }

    @Override
    public List<Customer> list() {
        return null;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return null;
    }

    @Override
    public double getBalance(int customerId) {
        return 0;
    }

    @Override
    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     */
    public void add(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
