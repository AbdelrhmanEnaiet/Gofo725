public class Admin extends User
{
	/**
	 * Register a new admin
	 * @param name Admin's username
	 * @param e Admin's email
	 * @param pass Admin's password
	 * @param aType Admin's account type
	 */
    public Admin(String name, String e, String pass, String aType)
    {
        super(name, e, pass, aType);
    }
    
    /**
     * Approve a new playground
     * @param id Playground request id
     * @return true if the request accepted
     */
    public boolean acceptRequest(int id)
    {System.out.println(id);
        User.newPGsHandler.acceptRequest(id);
        return true;
    }

}
