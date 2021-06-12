public class Owner extends User {
    /**
     * 
     * Register a new Owner
	 * @param name Owner's username
	 * @param e Owner's email
	 * @param pass Owner's password
	 * @param aType Owner's account type
     */
    public Owner(String name, String e, String pass, String aType) //constructor
    {
        super(name, e, pass, aType);
    }
    
    /**
     * Copy the information of an Owner to another Owner
     * @param u a User
     */
    public Owner(User u) {
        super(u);
    }

    /**
     * Make a request to add a new playground
     * @param playground A playground class object to make a request for
     * @return True if the request made successfully
     */
    public boolean addNewPlayground(Playground playground) {
        return newPGsHandler.addRequest(this, playground);
    }

}
