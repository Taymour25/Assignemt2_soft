import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*@author: Abdelrahman Taymour
 *@modified: Tarek Mostafa Abo-Bakr
 *@version: 0.3
 *@date: 1/5
 *
 * Represents an Admin account with additional functionalities beyond a regular Account.
 * Inherits from the Account class.
 * public class Admin extends Account {}
 * Adds an Item to the system.
 * @param item The Item object to be added.
 * public void addItem(Item item){}
 * 
 * Removes an Item from the system.
 * @param item The Item object to be removed.
 * public void removeItem(Item item){}
 * 
 * Edits an existing Item in the system.
 * @param item The Item object to be edited.
 * public void editItem(Item item){}
 * 
 * Sets the loyalty points of a User in the system.
 * @param user The User object whose loyalty points will be set.
 * public void setLoyaltyPoints(User user){}
 * 
 * Views statistics of an Item in the system.
 * @param item The Item object for which statistics will be viewed.
 * public void viewStatistics(Item item){}
 * 

  */
public class Admin extends Account{
    Connect C = new Connect("tofee.db","Item");
    Scanner scan = new Scanner(System.in);
    public void addItem(){
        System.out.println("Enter Item's ID");
        int id = scan.nextInt();
        System.out.println("Enter Item's NAME");
        String nm = scan.next();
        System.out.println("Enter Item's PRICE");
        int price = scan.nextInt();
        System.out.println("Enter Item's Category");
        String ct = scan.next();
        System.out.println("Enter Item's SOLD BY");
        String sb = scan.next();
        System.out.println("Enter Item's Quantity available");
        int qty = scan.nextInt();
        C.insertItem(id,price,nm,ct,sb,qty);
        System.out.println("item added");
    }
    public void removeItem(int item) throws SQLException {
        Connection c = C.connect();
        Statement stmnt = c.createStatement();
        stmnt.execute("DELETE FROM Item WHERE itemID ="+item);
        System.out.println("item deleted");
        c.close();
    }
    public void addOffer(int item) throws SQLException {
        Connection c = C.connect();
        Statement stmnt = c.createStatement();
        System.out.println("Enter the new item price : ");
        int npr = scan.nextInt();
        stmnt.execute("UPDATE Item SET price = "+ npr +" WHERE itemID ="+item);
        System.out.println("item price changed");
        c.close();
    }
    public void setLoyaltyPoints(int user) throws SQLException {
        Connection c = C.connect();
        Statement stmnt = c.createStatement();
        System.out.println("Enter the new Loyalty Points : ");
        int npr = scan.nextInt();
        stmnt.execute("UPDATE Account SET loyaltyPoints = "+ npr +" WHERE ID ="+user);
        System.out.println("Loyalty points Changed");
        c.close();
    }
}
