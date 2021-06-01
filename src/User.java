public class User
{
    protected String userName;
    private String email;
    private String password;
    protected String accountType;

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
