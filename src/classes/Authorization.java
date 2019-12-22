package classes;

public abstract class Authorization
{
    String userName;
    String password;

    public Authorization( String userName, String pass ){
        this.userName = userName;
        this.password = pass;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @param name user name that is entered.
     * @param password password that is entered.
     * @return id of the user if name and pass is true, negative values if otherwise.
     */
    public boolean authUser( String name, String password, int role )
    {
        return true;
    }
}
