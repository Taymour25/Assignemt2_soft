import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class appManager {
    Connect connection = new Connect("tofee.db", "Account");
    Connect C = new Connect("tofee.db", "Item");
    User current = new User();
    boolean loggedin =false;
    Item viewedItem = new Item();
    Visitor V = new Visitor();
    Admin admin = new Admin();

    void initiateApp() throws SQLException {
        Connection conn = connection.connect();
        while (true) {
            System.out.println("Welcome to TOFFEE =) ");
            System.out.println("Do u wish to : ");
            System.out.println(" 1- Login \n 2- Register \n 3- Navigate Categories \n 4- Forget Password");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Enter your username : ");
                String uname = scanner.next();
                System.out.println("Enter your Password : ");
                String P = scanner.next();
                int userID = V.login(uname, P);
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery("SELECT * FROM Account WHERE ID =" + userID);
                if (userID != 0){

                    current.setId(result.getInt("ID"));
                    current.setName("username");
                    current.setPhone("phone");
                    current.setPassword("pass");
                    current.setAddress("address");

                    break;
                }else{
                    System.out.println("Invalid Login Credentials");
                }
            }
            if (choice == 2) {
                System.out.println("Enter your username : ");
                String n = scanner.next();
                System.out.println("Enter your Password : ");
                String p = scanner.next();
                V.register(n, p);
                V.login(n, p);
                loggedin = true;
                break;
            }
            if (choice == 3) {
                C.selectAll();
            }
            if (choice == 4){
                V.forgetPassword();
            }
        }
        conn.close();
    }

    void navigate() throws SQLException {
        Connection conn = connection.connect();
        while (true) {
            C.selectAll();
            Scanner scanner = new Scanner(System.in);
            int item = scanner.nextInt();
            if (item != 0) {
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery("SELECT * FROM Item WHERE itemID =" + item);

                int itemprice = result.getInt("price");
                int itemamount = result.getInt("quantityAvaiable");

                viewedItem.setName(result.getString("name"));
                viewedItem.setPrice(itemprice);
                viewedItem.setAmountAvailable(itemamount);
                viewedItem.setCategory(result.getString("category"));
                viewedItem.setSoldBy(result.getString("SoldBy"));


                System.out.println("ITEM NAME          : " + viewedItem.getName());
                System.out.println("ITEM PRICE         : " + viewedItem.getPrice());
                System.out.println("AMOUNT AVAILABLE   : " + viewedItem.getAmount());
                System.out.println("CATEGORY           : " + viewedItem.getCategory());

                while (true) {

                    System.out.println("Enter The amount you want to purchase : ");
                    int amount = scanner.nextInt();
                    if (amount == 0) {
                        break;

                    } else {
                        if (amount <= viewedItem.getAmount()) {

                            current.cart.addToCart(viewedItem);
                            stmt.execute("INSERT INTO CartItem (CartID,ItemID,quantity) VALUES (" + current.Id + "," + item + "," + amount + ")");
                            System.out.println("Item added to cart successfully");
                            int newamount = viewedItem.getAmount() - amount;
                            stmt.execute("UPDATE Item set quantityAvaiable=" + newamount + " WHERE itemID=" + item);
                            break;

                        } else {
                            System.out.println("This Amount isn't currently Available");
                        }
                    }
                }
            }else{
                break;
            }
        }
        conn.close();
    }
    void showCart() throws SQLException {
        Connection conn = connection.connect();
        current.cart.totalPrice=0;
        Statement stmt = conn.createStatement();
        Statement exc = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM CartItem WHERE CartID ="+current.Id);

        System.out.println("Items     Quantity");

        while (result.next()) {

            int itemid=result.getInt("itemID");
            ResultSet R=exc.executeQuery("SELECT * FROM Item WHERE itemID="+itemid);
            String itemname = R.getString("name");
            System.out.println(itemname  +"\t" +result.getInt("quantity"));
            current.cart.totalPrice += calcPrice(R.getInt("price"),result.getInt("quantity"));
        }
        System.out.println(current.cart.totalPrice);
        conn.close();
    }

    void checkOut() throws SQLException {
        Connection conn = connection.connect();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("How Do You wish to pay :");
            String payment = scanner.next();
            if (Objects.equals(payment, "cash")) {
                System.out.println("The order will be sent to you shortly and will receive payment on delivery");
                emptyCart();
                break;
            } else if (Objects.equals(payment, "wallet")) {
                Statement stmt = conn.createStatement();
                ResultSet R = stmt.executeQuery("SELECT * FROM Wallet WHERE ID=" + current.Id);
                int money = R.getInt("balance");
                if (money < current.cart.totalPrice) {
                    System.out.println("Insufficient funds");
                } else {
                    System.out.println("Successful purchase =D");
                    int newbalance = money-current.cart.totalPrice;
                    stmt.execute("UPDATE Wallet SET balance ="+ newbalance + " WHERE ID="+current.Id);
                    managePurchase();
                    emptyCart();
                    break;
                }

            } else {
                System.out.println("Invalid payment method =(");
            }
        }
        conn.close();
    }

    void emptyCart() throws SQLException {
        Connection conn = connection.connect();
        Statement st = conn.createStatement();
        st.execute("DELETE FROM CartItem WHERE CartID ="+current.Id);
        conn.close();
    }
    int calcPrice(int price,int count){
        return price*count;
    }

    void rechargeWallet() throws SQLException {
        Connection conn = connection.connect();
        System.out.println("Enter the amount to be recharged :");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        Statement stmt = conn.createStatement();
        ResultSet R=stmt.executeQuery("SELECT * FROM Wallet WHERE ID="+current.Id);
        int currentAmount = R.getInt("balance");
        int newAmount = amount + currentAmount;
        Statement S = conn.createStatement();
        S.execute("UPDATE Wallet SET balance ="+newAmount+" WHERE ID="+current.Id);
        System.out.println("Wallet Recharged successfully");
        conn.close();
    }

    void managePurchase() throws SQLException {
        Connection conn = connection.connect();
        Statement st=conn.createStatement();
        Statement t = conn.createStatement();

        ResultSet Rs = st.executeQuery("SELECT * FROM CartItem WHERE CartID=" + current.Id);
        while (Rs.next()){
            int itmId = Rs.getInt("ItemID");
            int amnt = Rs.getInt("quantity");
            ResultSet r=t.executeQuery("SELECT * FROM Item WHERE itemID="+itmId);
            int old = r.getInt("numberSold");
            int nw =old+amnt;
            t.execute("UPDATE Item SET numberSold ="+nw+" WHERE itemID="+current.Id);
        }
        conn.close();
    }

    void adminPanel() throws SQLException {
        System.out.println("1-add Item \n2-remove Item\n3-add offer\n4-change user loyalty points");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(n==1){
            admin.addItem();
        }
        if (n==2){
            System.out.println("Enter item ID ");
            int x =scanner.nextInt();
            admin.removeItem(x);
        }
        if (n==3){
            System.out.println("Enter item ID ");
            int x =scanner.nextInt();
            admin.addOffer(x);
        }
        if (n==4){
            System.out.println("Enter user ID ");
            int x =scanner.nextInt();
            admin.setLoyaltyPoints(x);
        }
    }

    void go() throws SQLException {
        initiateApp();
        while (true) {
            Connection conn = connection.connect();
            Scanner s = new Scanner(System.in);
            System.out.println("What do you wish to do ?");
            System.out.println(" 0- admin panel \n 1- shop for items \n 2- show cart \n 3- check out \n 4- recharge wallet \n 5- Change password \n 6- exit");
            int choose = s.nextInt();
            if (choose==0){
                Statement st =conn.createStatement();
                ResultSet rset = st.executeQuery("SELECT * FROM Account WHERE ID="+current.Id);
                if (rset.getBoolean("is_Admin")){
                    conn.close();
                    adminPanel();
                }else {

                    System.out.println("you aren't an admin >=(");
                }

            }
            if (choose == 1) {
                navigate();
            }
            if (choose == 2) {
                showCart();
            }
            if (choose == 3) {
                checkOut();
            }
            if (choose == 4) {
                rechargeWallet();
            }
            if (choose == 5) {
                current.changePassword(current);
            }
            if (choose > 5){
                break;
            }
        }
    }


}
