public class Admin
{
    public static boolean acceptRequest(int id)
    {
        User.newPGsHandler.acceptRequest(id);
        return true;
    }

}
