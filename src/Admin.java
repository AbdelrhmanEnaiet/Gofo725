public class Admin
{
    public static boolean acceptRequest(int id)
    {System.out.println(id);
        User.newPGsHandler.acceptRequest(id);
        return true;
    }

}
