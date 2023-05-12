/*
 * @author: Abdelrahman Taymour
 * @modified: Tarek Mostafa Abo-Bakr
 * @version: 0.3
 * @date: 7/5
 *
 * The Item class represents a product that can be sold on an e-commerce website.
 * Each item has a price, a name, an amount available, the name of the vendor who sold it, the price after an offer,
 * a category, the number of units sold today, and the number of units sold this year.
 * public class Item {
 * 
 * The price of the item.
 *  private int price;
 * 
 * The name of the item.
 *  private String name;
 * 
 * The amount of the item available in stock.
 *  private int amountAvailable;
 * 
 * The name of the vendor who sold the item.
 *  private String soldBy;
 * 
 * 
 * The price of the item after applying an offer.
 *  private int afterOfferPrice;
 * 
 * 
 * The category of the item.
 *  private String category;
 * 
 * 
 * The number of units of the item sold today.
 *  private int soldToday;
 * 
 * The number of units of the item sold this year.
 *  private int soldThisYear;
 * 
 * Adds an offer to the item, modifying its price after the offer.
 *  public void addOffer(){}
 * 
 * 
 * Recalculates the price of the item based on any applied offers.
 * @return the new price of the item.
 * 
 * 
 * public float reCalculatePrice(){return 0;}
 * 
 * 
 * Adds the item to the user's cart.
 *  public void addToCart(){}
 * 
 * Returns the price of the item.
 * @return the price of the item.
 * public float getPrice(){return 0;}
 * 
 * 
 * 
 *  Returns the number of units of the item sold today.
 * @return the number of units of the item sold today.
 * public int getSoldToday(){return 0;}
 * 
 * 
 * Returns the number of units of the item sold this year.
 * @return the number of units of the item sold this year.
 * public int getSoldThisYear(){return 0;}
 * 
 * Returns the amount of the item available in stock.
 * @return the amount of the item available in stock.
 * 
 * public int getAmount(){return 0 ;}
 * }
 */

public class Item {
private int price;
private String name;
private int amountAvailable;
private String soldBy;
private int afterOfferPrice;
private String category;
public int soldToday;
public int soldThisYear;

public void addOffer(){}
public float reCalculatePrice(){return 0;}
public void addToCart(){}
public int getPrice(){return price;} //add it new v.2
public int getSoldToday(){return 0;}
public int getSoldThisYear(){return 0;}
public int getAmount(){return amountAvailable ;}
    public void setPrice(int n){
        price=n;
    }
    void setAmountAvailable(int n){
        amountAvailable=n;
    }
    void setName(String x){
        name=x;
    }
    void setCategory(String x){ category=x;}
    void setSoldBy(String x){soldBy = x;}
    String getName(){
    return name;
    }
    String getCategory(){
    return category;
    }


}
