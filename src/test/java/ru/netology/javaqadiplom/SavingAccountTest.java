package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldNotCreateAccountWithNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    1_000,
                    10_000,
                    -5);
        });
    }

    @Test
    public void shouldNotCreateAccountWithNegativeBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -1_000,
                    -1_000,
                    1_000,
                    5);
        });
    }

    @Test
    public void shouldNotCreateAccountWhenMinMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    10_000,
                    9_000,
                    5);
        });
    }

    @Test
    public void shouldNotCreateAccountWithMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    4_000,
                    1_000,
                    3_000,
                    5);
        });
    }

    @Test
    public void shouldNotCreateAccountWithInitialBalanceLessThanMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    2_000,
                    3_000,
                    5);
        });
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {

        SavingAccount account = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5);

        account.add(2_000);
        Assertions.assertEquals(9_000, account.getBalance());

    }

    @Test
    public void shouldPayMoreThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                7_000,

                5);

        account.pay(2_000);

        Assertions.assertEquals(3_000 - 2_000, account.getBalance());

    }

    @Test
    public void shouldNotPayLessThanZeroBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                7_000,
                5);

        account.pay(4_000);

        Assertions.assertEquals(3_000, account.getBalance());

    }

    @Test
    public void shouldNotPayLessThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                7_000,
                5);

        account.pay(2_500);

        Assertions.assertEquals(3_000, account.getBalance());

    }

    @Test
    public void shouldNotPayWhenNegativeAmount() {

        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                7_000,
                5);

        account.pay(-1);

        Assertions.assertEquals(3_000, account.getBalance());

    }
}