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

/**
 *
 * @author kristinamantha
 */
public class DeadlockTest {

    public static final int NACCOUNTS = 10;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
