import java.util.ArrayList;

public class UserHandler
{
    private User currentUser;
    private ArrayList<User> users=new ArrayList<>(); //changed the array to an ArrayList to have a better control and add new elements easily

    public UserHandler()
    {
        currentUser=null;
    }

    public User getUserByName(String name)
    {
        for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getUserName().equals(name) )
                return users.get(i);
        }
        return null;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }
    
    
    
    
    
    /**
     *check if the user is registered before and return true if existing 
     * @param eml the email to be searched for in the user object
     * @return true if the email exist
     */
    public boolean checkIfExists(String eml) {
    	
    	for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getEmail().equalsIgnoreCase(eml) )
                return true;
        }
    	
    	return false;
    	
    }
    /**
     * this method register a new user
     * @param name user's name
     * @param e user's email
     * @param pass user's password
     * @param aType user's type (owner/player)
     */
    public void adduser(String name, String e, String pass, String aType) {
    	
    	if(aType.equalsIgnoreCase("owner")) {
    		currentUser=new Owner(name, e, pass, aType);	
    	}else {
    		currentUser=new Player(name, e, pass, aType);
    	}
    	
    	
    	
    	
    	users.add(currentUser);
    }
    /**
     * this method check if the email and password given are valid and set that user as the current user 
     * @param eml user's email
     * @param pass user's password
     * @return true if signIn is successful 
     */
    public boolean signIn(String eml, String pass) {
    	
    	
    	for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getEmail().equalsIgnoreCase(eml) && users.get(i).checkPassword(pass) ) {
            	currentUser=users.get(i);
            	return true;
            }
                
        }
    	return false;
    	
    	
    }
    
}
