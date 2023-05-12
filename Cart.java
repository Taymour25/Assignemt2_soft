import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 *@author: Abdelrahman Taymour
 *@modified: Tarek Mostafa Abo-Bakr
 *@version: 0.3
 *@date: 1/5
 * 
 * lass represents the  cart of a user.
 * It contains a list of {@link Item} objects, the total price of the items in the cart,
 * and the payment method chosen by the user.
 * <p>
 *  object is created with an empty list of items, a total price of zero,
 * and an empty payment method. Items can be added to the cart using the {@link #addToCart(Item)} method.
 * 
 * </p>
 * <p>
 * The {@link #isValid()} method always returns true, indicating that the cart is valid.
 * 
 * </p>
 * <p>
 * The {@link #useVoucher(int, User)} method applies a voucher to the total price of the cart.
 * 
 * It calls the {@link User#useVoucher(int)} method of the specified {@link User} object to get the value
 * of the voucher and subtracts it from the total price.
 * </p>
 * @author [insert your name here]
 * @see Item
 * @see User
 * public class Cart {}
 * The list of items in the cart.
 * 
 * private List<Item> items;
 * The total price of the items in the cart.
 * private int totalPrice;
 * The payment method chosen by the user.
 * private String paymentMethod;
 * Constructs an empty cart with a total price of zero and an empty payment method.
 * The list of items in the cart is initialized to an empty ArrayList.
 * 
 * public Cart() {
 * items = new ArrayList<>();
 * totalPrice = 0;
 * paymentMethod = "";
 * 
 * 
 * Adds the specified item to the cart and updates the total price accordingly.
 * @param item the item to be added to the cart
 * 
 * public void addToCart(Item item) {
 * items.add(item);
 * totalPrice += item.getPrice();}
 * 
 * Checks whether the cart is valid.
 * @return true, indicating that the cart is always valid 
 * 
 * public boolean isValid() {
 * return true;}
 * 
 * 
 * Applies a voucher to the total price of the cart.
 * It calls the {@link User#useVoucher(int)} method of the specified {@User} object to get the value
 * of the voucher and subtracts it from the total price.
 * @param VID the ID of the voucher to be used
 * @param usr the user who is using the voucher
 * @throws SQLException if an error occurs while accessing the database
 * public void useVoucher(int VID, User usr) throws SQLException {
 * totalPrice = totalPrice - usr.useVoucher(VID);}
 */

public class Cart {
    private List<Item> items; //add it new v.2
    public int totalPrice;
    private String paymentMethod;

    public Cart() {

        items = new ArrayList<>(); //add it new v.2
        totalPrice = 0;
        paymentMethod = "";
    }

    public void addToCart(Item item) {
        items.add(item); 
        totalPrice += item.getPrice();
    }


    public boolean isValid() {
        return true;
    }

    public void useVoucher(int VID, User usr) throws SQLException {
        totalPrice = totalPrice - usr.useVoucher(VID);
    }

}
