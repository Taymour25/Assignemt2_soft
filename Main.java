import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
            Visitor V = new Visitor();
            System.out.println("1- Login \n 2- Reister \n 3- Navigate Categories");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input :: ");
            int x = scanner.nextInt();
            User current = new User();
            if (x == 1) {
                System.out.println("UserName : ");
                String name = scanner.next();
                System.out.println("Password : ");
                String pas = scanner.next();
                if (V.login(name, pas)) {
                    Connect connection = new Connect("tofee.db", "Account");
                    Connection conn = connection.connect();
                    Statement state = conn.createStatement();
                    ResultSet result = state.executeQuery("Select * from Account Where username = '" + name + "'");
                    current.setId(result.getInt("ID"));
                    current.setName(result.getString("username"));
                    current.setPassword(result.getString("pass"));
                    current.setPhone(result.getString("phone"));
                    current.setAddress(result.getString("address"));
                    System.out.println("1- Navigate \n 2- Change Password");
                    int y = scanner.nextInt();
                    if (y==1){
                        Connect newc = new Connect("tofee.db","Item");
                        newc.selectAll();
                        System.out.println("\n ------------------------------");
                    }
                    else {
                        conn.close();
                        current.changePassword(current);
                    }

                } else {
                    System.out.println("Invalid Login");
                }
            } else if (x == 2) {
                V.register();
            } else {
                Connect C = new Connect("tofee.db", "Item");
                C.selectAll();
            }
    }
}
