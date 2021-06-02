public class User
{
    protected static NewPGsHandler newPGsHandler;
    protected String userName;
    protected String email;
    protected String password;
    protected String accountType;

    public User(User user)
    {
        userName = user.userName;
        email = user.email;
        password = user.password;
        accountType = user.accountType;
    }
    //##########################################
    // inherited classes uses static functions
    // the inheritance is only to add new functionalities to USER class
    // keep adding functions that way, it's easier and more straightforward
    //###########################################
    public User(String name, String e, String pass, String aType)
    {
        userName = name;
        email = e;
        password = pass;
        accountType = aType;
    }

    public boolean checkPassword (String pass)
    {
        if (pass == password) return true;
        else return false;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAccountType()
    {
        return accountType;
    }
}
