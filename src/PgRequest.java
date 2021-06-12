public class PgRequest
{
    private int id;
    private String requestCreator;
    private Playground reqPlayground;

    /**
     * create a new playground request and set it's attributes
     * @param id Request id
     * @param o the username of the owner
     * @param p the playground to make a request for
     */
    public PgRequest(int id, String o, Playground p )
    {
        this.id = id;
        this.requestCreator = o;
        this.reqPlayground = p;
    }
    
    /**
     * Return the username of who made the request 
     * @return The name of the person that made the request 
     */
    public String getCreator()
    {
        return requestCreator;
    }
    /**
     * 
     * @return the playground request id
     */
    public int getId ()
    {
        return id;
    }
    /**
     * 
     * @return the playground the request was made for
     */
    public Playground getReqPlayground()
    {
        return reqPlayground;
    }
    /**
     * Display the request with it's information
     */
    public void print ()
    {
        System.out.println("#############################################");
        System.out.println(getCreator()+" Requested to add a playground with details:");
        System.out.println("Request ID: "+ id);
        reqPlayground.print();
    }
}
