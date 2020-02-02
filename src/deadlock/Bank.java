/* 
 Project: Deadlock
 Purpose Details: Use your understanding of deadlocks to 
                  create a Java application that will consistently cause a 
                  deadlock condition to occur. 
 Course: IST411
 Author: Team 3
 Date Developed: 26-2 Feb 2020
 Last Date Changed: 2 Feb 2020
 Revision: too many to count!
 */
package deadlock;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kristinamantha
 */
public class Bank {

    private final double[] accounts;
    private final Lock bankLock;
    //private final Condition sufficientFunds;

    /**
     * Constructs the bank.
     *
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        //sufficientFunds = bankLock.newCondition();
    }

    /**
     * Transfers money from one account to another.
     *
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();

        try {
            while (accounts[from] < amount) {
                //sufficientFunds.await();        
            }
            System.out.print(Thread.currentThread().getName());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            //sufficientFunds.signalAll();

        } finally {
            bankLock.unlock();

        }
    }


    /**
     * Gets the sum of all account balances.
     *
     * @return the total balance
     */
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }

    }

    /**
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
