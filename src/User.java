public class User
{
    protected static NewPGsHandler newPGsHandler;
    protected String userName;
    protected String email;
    protected String password;
    protected String accountType;
    /**
     * Copy a user information to another user
     * @param user Takes a user class object
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
    * Create a new user
    * @param name The user's name
    * @param e The user's email
    * @param pass The user's password
    * @param aType The user's account type
    */
    public User(String name, String e, String pass, String aType)
    {
        userName = name;
        email = e;
        password = pass;
        accountType = aType;
    }
    /**
     * Checks if the given password is equal to the user's password
     * @param pass A string type password
     * @return True if the password is valid and false if not
     */
    public boolean checkPassword (String pass)
    {
        if (pass.equals(password)) return true;
        else return false;
    }
    /**
     * 
     * @return The user's username
     */
    public String getUserName()
    {
        return userName;
    }
    /**
     * 
     * @return The user's email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * 
     * @return The user's account type
     */
    public String getAccountType()
    {
        return accountType;
    }
    /**
     * 
     * @return The user's password 
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * Set the username of the user
     * @param userName user's name
     */
    public void setUserName(String userName) {
		this.userName = userName;
	}
    /**
     * Set the user's email
     * @param email User's email
     */
    public void setEmail(String email) {
		this.email = email;
	}
    /**
     * Set the user's password
     * @param password User's new password
     */
    public void setPassword(String password) {
		this.password = password;
	}
    /**
     * Set the type of the account
     * @param accountType The account's type
     */
    public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
