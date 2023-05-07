import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Visitor {
    public boolean check(int id){
        String sql = " SELECT * FROM " + table ;

        try {
            Connect C = new Connect("tofee.db","Account");
            Connection conn = C.connect();
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            //loop through the result set
            while (result.next()) {
                if(result.getInt("ID")==id){
                    return false;
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    private String table="Account";
    public boolean login(String uname,String password) {
        String sql = " SELECT * FROM " + table ;

        try {
            Connect C = new Connect("tofee.db","Account");
            Connection conn = C.connect();
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

             //loop through the result set
            while (result.next()) {
                if(Objects.equals(result.getString("username"), uname) && Objects.equals(result.getString("pass"), password)){
                    System.out.println("Welcome " + result.getString("username"));
                    conn.close();
                   return true;
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
        }

        public void register() throws SQLException {
            System.out.println("Please Insert Your Data \n");
            System.out.print("ID : ");

            Scanner scanner = new Scanner(System.in);
            int theId= scanner.nextInt();
            Connect C=new Connect("tofee.db","Account");
            Connection conn = C.connect();
            Statement state = conn.createStatement();
            if (check(theId)){
                String Wquery="INSERT INTO Wallet VALUES (0," + theId + ",'Visa') ";
                String Cquery="INSERT INTO Cart VALUES ("+ theId+",'',0,'cash') ";
                System.out.print("Name : ");
                String name= scanner.next();
                System.out.print("password : ");
                String pas= scanner.next();
                System.out.print("Address : ");
                String Addr= scanner.next();
                System.out.print("Phone Number : ");
                String phoneN= scanner.next();
                C.insert(theId,name,pas,Addr,phoneN);
                state.execute(Wquery);
                state.execute(Cquery);
                conn.close();

            }else {
                System.out.println("ID already in use");
            }
            }

        }
