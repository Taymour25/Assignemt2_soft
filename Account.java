public abstract class Account {
    private String name;
    private String password;
    Wallet wallet =new Wallet();
    Cart cart =new Cart();
    private String address;
    String[] ownedVouchers ={};
    private String phone ;
//-----------------------------------//
    public boolean isVerified(){return true;}
    public void changePassword(){}
    public void addToCart(){}
    public void verifyUser(){}
    public Cart getCart(){return cart;}
    public String getAddress(){return "";}
    public void setPhone(){}
    public void useVoucher(){}
    public void sendVerification(){}

}
