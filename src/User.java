public class User
{
    protected static NewPGsHandler newPGsHandler;
    protected String userName;
    protected String email;
    protected String password;
    protected String accountType;

    public User(User user)
    {
        this.userName = user.userName;
        this.email = user.email;
        this.password = user.password;
        this.accountType = user.accountType;
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
        if (pass.equals(password)) return true;
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

    public String getPassword()
    {
        return password;
    }
    public void setUserName(String userName) {
		this.userName = userName;
	}
    public void setEmail(String email) {
		this.email = email;
	}
    public void setPassword(String password) {
		this.password = password;
	}
    public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
