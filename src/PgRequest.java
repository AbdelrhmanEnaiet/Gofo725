public class PgRequest
{
    private int id;
    private Owner requestCreator;
    private Playground reqPlayground;

    public PgRequest(Owner o, Playground p, int id)
    {
        this.requestCreator = o;
        this.reqPlayground = p;
        this.id = id;
    }
    public String getCreator()
    {
        return requestCreator.getUserName();
    }
    public int getId ()
    {
        return id;
    }
    public Playground getReqPlayground()
    {
        return reqPlayground;
    }
    public void print ()
    {
        System.out.println("#############################################");
        System.out.println(getCreator()+" Requested to add a playground with details:");
        System.out.println("Request ID: "+ id);
        reqPlayground.print();
    }
}
