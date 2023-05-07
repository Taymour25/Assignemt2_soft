import java.sql.SQLException;

public class Cart {
private int totalPrice;
private String paymentMethod;

public boolean isValid(){return true;}
public void useVoucher(int VID , User usr) throws SQLException {
    totalPrice =totalPrice- usr.useVoucher(VID);
}
}
