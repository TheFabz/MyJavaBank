import junitparams.JUnitParamsRunner;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.SavingsAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(JUnitParamsRunner.class)
public class SavingsAccTest {

    @Test
    public void savingsAccountTest() {

        Account account = new SavingsAccount();
        account.setId(1);


        // should be possible to debit account if sufficient funds
        account.debit(5);
        if (account.getBalance() != SavingsAccount.MIN_BALANCE + 5) {
            fail();
        }

        // should not be possible to credit or debit negative values
        account.credit(-1);
        account.debit(-1);
        if (account.getBalance() != SavingsAccount.MIN_BALANCE + 5) {
            fail();
        }
    }

    @Test
    public void confirmsAccountsStartWithZeroBalance() {
        Account account = new SavingsAccount();
        account.setId(1);

        assertEquals(0, account.getBalance(), account.getBalance());
        //assertEquals(0,account.getBalance());
    }

    @Test
    @Parameters({
            "10",
            "20",
            "100"})
    public void testIfAccountsWithPositiveBalanceCanCredit(int credit) {
        Account account = new SavingsAccount();
        account.setId(1);

        // should be possible to credit account with positive value
        account.credit(SavingsAccount.MIN_BALANCE + credit);

        assertEquals(SavingsAccount.MIN_BALANCE + credit, account.getBalance(), account.getBalance());
    }

    @Test
    @Parameters({
            "10",
            "20",
            "100"})
    public void testsIfPossibleToDebitWithInsufficientFunds(int debit) {

        Account account = new SavingsAccount();
        account.setId(1);

        // should not be possible to debit account if no sufficient funds
        account.debit(SavingsAccount.MIN_BALANCE + debit);

        assertEquals(SavingsAccount.MIN_BALANCE + debit, account.getBalance(), account.getBalance());
    }

    @Test
    @Parameters({
            "10",
            "20",
            "100"})
    public void TestsIfAccountWithSufficientFundsCanBeDebited(int debit){
        Account account = new SavingsAccount();
        account.setId(1);

        // should be possible to debit account if sufficient funds
        account.debit(debit);
        if (account.getBalance() != SavingsAccount.MIN_BALANCE + debit) {
            fail();
        }
    }


}

