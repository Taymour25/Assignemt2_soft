import java.sql.*;

public class Connect {

        String database_name;
        String table_name;


        /**
         * Connect to a sample database
         *
         * @return
         */
        public Connect (String DB_name, String TB_name){
            database_name = DB_name;
            table_name = TB_name;
        }

    public static PreparedStatement prepareStatement(String s) {

        return null;
    }

    public Connection connect() {
            Connection conn = null;
            try {
                // db parameters
                String url = "jdbc:sqlite:batadase/" + database_name;
                // create a connection to the database
                conn = DriverManager.getConnection(url);


            } catch (SQLException e) {
                System.out.println(e.getMessage());}
            return conn;
        }

        public void createNewTable() {
            // SQLite's connection string
            String url = "jdbc:sqlite:DataBases/" + database_name;

            // SQL statement for creating a new table
            String sql = "CREATE TABLE IF NOT EXISTS"+ table_name + "(\n"
                    + " id integer PRIMARY KEY,\n"
                    + " name text NOT NULL,\n"
                    + " capacity real\n"
                    + ");";

            try{
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void insert( String name , String password,String Address,String Ph,String mail ) {
            String sql = "INSERT INTO "+ table_name +"( username,pass,address,phone,loyaltyPoints,mail) VALUES(?,?,?,?,?,?)";

            try{
                Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                pstmt.setString(3, Address);
                pstmt.setString(4, Ph);
                pstmt.setInt(5, 0);
                pstmt.setString(6,mail);

                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    public void insertItem( int ID , int price,String name,String cat,String sold,int aval ) {
        String sql = "INSERT INTO "+ table_name +"( itemID,price,name,category,SoldBy,quantityAvaiable,numberSold) VALUES(?,?,?,?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ID);
            pstmt.setInt(2, price);
            pstmt.setString(3, name);
            pstmt.setString(4, cat);
            pstmt.setString(5, sold);
            pstmt.setInt(6,aval);
            pstmt.setInt(7,0);

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void selectAll() {
            String sql = " SELECT * FROM " + table_name;

            try {
                Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                // loop through the result set
                System.out.println("Item number Item Name             Item Price");
                while (rs.next()) {
                    System.out.println(rs.getInt("itemID")+"\t \t \t |"+rs.getString("name") + "\t |" + rs.getInt("price") + "$");
                }
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }


    }
