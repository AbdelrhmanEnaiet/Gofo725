public class Playground
{
    private int pId;
    private String name;
    private String address;
    private int maxTeamSize;
    private Owner pOwner;
    private Booking[] bookings = new Booking[50];

    public Playground (int id,String n, String adrs, int max, Owner o)
    {
        pId=id;
        name=n;
        address=adrs;
        maxTeamSize =max;
        pOwner =o;
    }
    public void print() //Not yet implemented
    {

    }
}
