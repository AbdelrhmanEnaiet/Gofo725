public class UserHandler
{
    private User currentUser;
    private User[] users;

    public UserHandler()
    {
        currentUser=null;
    }

    public User getUserByName(String name)
    {
        for (int i=0; i<users.length; i++)
        {
            if ( users[i].getUserName()==name )
                return users[i];
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
}
