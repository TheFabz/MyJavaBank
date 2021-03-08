import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AuthServiceTest {

    @Test
    public void confirmUserEntersAccountIfAccountExists(){

    AuthServiceImpl authService = new AuthServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();
    authService.setCustomerService(customerService);

    Customer customer = new Customer();
    customerService.add(customer);

    // should authenticate
    if(!authService.authenticate(customer.getId())){
        fail();
    }

    }


}
