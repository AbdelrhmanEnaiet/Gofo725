public class PgRequest
{
    private Owner requestCreator;
    private Playground reqPlayground;

    public PgRequest(Owner o, Playground p)
    {
        requestCreator = o;
        reqPlayground = p;
    }
    public String getCreator()
    {
        return requestCreator.getUserName();
    }
    public void print ()
    {
        System.out.println(getCreator()+" Requested to add a playground with details:");
        reqPlayground.print();
    }
}
