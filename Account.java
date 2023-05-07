import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
            state.executeQuery("UPDATE Account SET pass = "+"'"+newpas+"'"+ "\n WHERE ID = " + Id);
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
