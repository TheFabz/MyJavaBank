import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CheckingAccTest {

    @Test
    public void confirmsCheckingAccountIsAddedDebitsWithdrawsCorrectly(){

        Account account = new CheckingAccount();
        account.setId(1);

        // initial balance should be zero
        if (account.getBalance() != 0) {
            fail();
        }

        // should not be possible to credit or debit negative values
        account.credit(-1);
        account.debit(-2);
        if (account.getBalance() != 0) {
            fail();
        }

        // should be possible to credit account with positive value
        account.credit(10);
        if (account.getBalance() != 10) {
            fail();
        }

        // should not be possible to debit account if no sufficient funds
        account.debit(11);
        if (account.getBalance() != 10) {
            fail();
        }

        // should be possible to debit account if sufficient funds
        account.debit(8);
        if (account.getBalance() != 2) {
            fail();
        }

        fail();
    }

}
