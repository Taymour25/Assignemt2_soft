import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JavaDOC:
/*@author: Abdelrahman Taymour
 *@modified: Tarek Mostafa Abo-Bakr
 *@version: 0.3
 *@date: 8/5
 *
 *
 * The Account class represents an account in the system. It includes fields for the account ID, name, password, wallet,
 * cart, address, owned vouchers, and phone. It also contains methods for changing the account's password, adding items.
 *
 * to the cart, verifying the user, retrieving the cart, retrieving the address, setting the phone, setting the name,
 * setting the password, setting the address, and setting the ID. It also includes a method for using a voucher.
 *
 * <p>The changePassword method changes the password for the user's account. It prompts the user to enter a new password,
 * updates the password field in the Account object, and updates the password field in the corresponding row of the.
 * Account table in the database.
 *
 *
 * <p>The addToCart method adds an item to the user's cart.
 * <p>The verifyUser method verifies the user's identity.
 * <p>The getCart method retrieves the user's cart.
 * <p>The getAddress method retrieves the user's address.
 * <p>The setPhone method sets the user's phone number.
 * <p>The setName method sets the user's name.
 * <p>The setPassword method sets the user's password.
 * <p>The setAddress method sets the user's address.
 * <p>The setId method sets the user's ID.
 * <p>The useVoucher method retrieves the value of a voucher from the Voucher table in the database.
 */

public abstract class Account {
    public int Id;
    private String name;
    private String password;
    Wallet wallet =new Wallet();
    Cart cart =new Cart();
    private String address;
    String[] ownedVouchers ={};
    private String phone ;
//-----------------------------------//
    public void changePassword(User usr ) {
        try {
            System.out.println("Enter your new Password : ");
            Scanner scanner=new Scanner(System.in);
            String newpas = scanner.next();
            password = newpas;
            Connect newone = new Connect("tofee.db", "Account");
            Connection connec = newone.connect();
            Statement state = connec.createStatement();
            int res=state.executeUpdate("UPDATE Account SET pass = "+"'"+newpas+"'"+ "\n WHERE ID = " + Id);
            if (res ==1 ){
                System.out.println("Password Changed successfully \n");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void addToCart(){}
    public void verifyUser(){}
    public Cart getCart(){return cart;}
    public String getAddress(){return address;}
    public void setPhone(String x){phone=x;}
    public void setName(String x){name=x;}
    public void setPassword(String x){password=x;}
    public void setAddress(String x){address=x;}
    public void setId(int x){Id=x;}
    public int useVoucher(int VID) throws SQLException {
        Connect newone = new Connect("tofee.db", "Voucher");
        Connection connec = newone.connect();
        Statement state = connec.createStatement();
        ResultSet res=state.executeQuery("Select * FROM Voucher Where ID="+VID);
        return res.getInt("value");
    }

}
