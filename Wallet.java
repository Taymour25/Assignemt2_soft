/* @author: Abdelrahman Taymour
 * @modified: Tarek Mostafa Abo-Bakr
 * @version: 0.1
 * @date: 3/5
 * 
 * 
 * 
 * A class representing a Wallet, which is associated with a User, has a balance, and a payment type.
 * 
 * public class Wallet {
 * 
 * The user associated with the wallet.
 * private User user;
 * 
 * The balance amount in the wallet.
 * private int balance;
 * 
 * The payment type associated with the wallet.
 * private String paymentType;
 * 
 * Returns the payment type associated with the wallet.
 * @return the payment type associated with the wallet.
 * public String changePaymentType() {return "";};
 * 
 *  
 * Returns the balance amount in the wallet.
 * @return the balance amount in the wallet.
 * public int getBalance() {return 0;}
 * 
 * Recharges the wallet with the specified amount.
 * public void rechargeWallet() {}
 * 
 * Returns the user associated with the wallet.
 * @return the user associated with the wallet.
 * public User getUser() {
 * return user;}
 * }
 */

public class Wallet {
private User user;
private int balance;
private String paymentType;

public String changePaymentType(){return "";};
public int getBalance(){return 0;}
public void rechargeWallet(){}
public User getUser(){return user;}

}
