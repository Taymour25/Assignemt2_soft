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
    
    public void addItem(Item item){}
    public void removeItem(Item item){}
    public void addOffer(){}
    public void editItem(Item item){}
    public void setLoyaltyPoints(User user){}
    public void viewStatistics(Item item){}
}
