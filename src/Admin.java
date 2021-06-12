public class Admin extends User
{
    public Admin(String name, String e, String pass, String aType)
    {
        super(name, e, pass, aType);
    }

    public boolean acceptRequest(int id)
    {System.out.println(id);
        User.newPGsHandler.acceptRequest(id);
        return true;
    }

}
