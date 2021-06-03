public class PgRequest
{
    private int id;
    private String requestCreator;
    private Playground reqPlayground;

    public PgRequest(int id, String o, Playground p )
    {
        this.id = id;
        this.requestCreator = o;
        this.reqPlayground = p;
    }
    public String getCreator()
    {
        return requestCreator;
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
