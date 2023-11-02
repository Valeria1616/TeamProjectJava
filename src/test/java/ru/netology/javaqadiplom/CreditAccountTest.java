package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test //1
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }


//    @Test //2
//    public void shouldInitialNegativeBalance() { // тест на отрицательный стартовый баланс
//        CreditAccount account = new CreditAccount(
//                -100,
//                5_000,
//                15
//        );
//
//        account.getBalance();
//        Assertions.assertEquals(-100, account.getBalance());
//    }

    @Test //3
    public void shouldInitialNegativeRate() { // тест на возможность задолжать банку сумму отрицательнного значения
        Assertions.assertThrows(IllegalArgumentException.class , () ->{
            CreditAccount account = new CreditAccount(
                    0,
                    -5000,
                    15
            );

        });
    }

    //тесты для метода pay
    @Test //4
    public void shouldPayFalse() {
        CreditAccount account = new CreditAccount(
                0,
                100,
                15
        );

        boolean res = account.pay(200);
        Assertions.assertFalse( res );
    }

    @Test //5
    public void shouldPayFalseLimit() { //     сумма на initialBalance не должна выходить за рамки creditLimit

        CreditAccount account = new CreditAccount(
                10_000,
                5_000,
                15
        );

        Integer resSum = 20_000;

        account.pay(resSum);
        Assertions.assertFalse(account.getBalance() < -account.getCreditLimit());


    }


    @Test //6
    public void shouldPayTrueBoundaryValues() { // должен уменьшится initialBalance на сумму покупки, но этого не происходит
        CreditAccount account = new CreditAccount(
                1_000,
                0,
                15
        );
        boolean res = account.pay(999);
        account.pay(999);
        int resSumAfterPay = 1;
        Assertions.assertTrue( res );
        Assertions.assertEquals(resSumAfterPay, account.getBalance());
        Assertions.assertEquals(1, account.getBalance());
    }

    //тесты для метода add
    @Test //7
    public void shouldAdd() {
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );
        boolean res = account.add(999);
        Assertions.assertTrue( res );
    }

    @Test //8
    public void shouldAddZero() {
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );
        boolean res = account.add(0);
        Assertions.assertFalse( res );
    }

    @Test //9
    public void shouldAddCash() { //тест на пополнение карты
        CreditAccount account = new CreditAccount(
                10,
                0,
                15
        );
        boolean res = account.add(10);
        int actual = 20;
        Assertions.assertTrue( res );
        Assertions.assertEquals(actual, account.getBalance());
    }

    //тесты для метода yearChange
    @Test //10
    public void shouldNegativeScoreInterestCalculation() {
        CreditAccount account = new CreditAccount(
                -200,
                0,
                15
        );
        int res = account.yearChange();
        int actual = -30;
        Assertions.assertEquals(actual, res);
    }


    @Test //11
    public void shouldInterestCalculation() {
        CreditAccount account = new CreditAccount(
                200,
                0,
                15
        );
        int res = account.yearChange();
        int actual = 0;
        Assertions.assertEquals(actual, res);
    }
}
