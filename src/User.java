public class User
{
    protected static NewPGsHandler newPGsHandler;
    protected String userName;
    protected String email;
    protected String password;
    protected String accountType;
    /**
     * copy a user information to another user
     * @param user takes a user class object
     */
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
   /**
    * create a new user
    * @param name the user's name
    * @param e the user's email
    * @param pass the user's password
    * @param aType the user's account type
    */
    public User(String name, String e, String pass, String aType)
    {
        userName = name;
        email = e;
        password = pass;
        accountType = aType;
    }
    /**
     * checks if the given password is equal to the user's password
     * @param pass a string type password
     * @return true if the password is valid and false if not
     */
    public boolean checkPassword (String pass)
    {
        if (pass.equals(password)) return true;
        else return false;
    }
    /**
     * 
     * @return the user's username
     */
    public String getUserName()
    {
        return userName;
    }
    /**
     * 
     * @return the user's email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * 
     * @return the user's account type
     */
    public String getAccountType()
    {
        return accountType;
    }
    /**
     * 
     * @return the user's password 
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * set the username of the user
     * @param userName user's name
     */
    public void setUserName(String userName) {
		this.userName = userName;
	}
    /**
     * set the user's email
     * @param email user's email
     */
    public void setEmail(String email) {
		this.email = email;
	}
    /**
     * set the user's password
     * @param password user's new password
     */
    public void setPassword(String password) {
		this.password = password;
	}
    /**
     * set the type of the account
     * @param accountType the account's type
     */
    public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
