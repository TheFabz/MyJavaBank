package org.academiadecodigo.javabank.serviceLayer;

import org.academiadecodigo.javabank.model.Customer;

public class AuthServiceLogic implements AuthService{

    @Override
    public boolean authenticate(Integer id) {
        return false;
    }

    @Override
    public Customer getAccessingCustomer() {
        return null;
    }


}
