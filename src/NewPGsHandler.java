import java.util.Vector;

public class NewPGsHandler
{
    private Vector<PgRequest> requests = new Vector<PgRequest>();

    public void viewRequestsOf(String oName)
    {
        Vector<PgRequest> temp = new Vector<PgRequest>();
        for (int i=0; i<requests.size(); i++)
        {
            if (requests.get(i).getCreator() == oName)
                temp.add(requests.get(i));
        }
        if (temp.size()==0)
        {
            System.out.println("You don't have any playgrounds yet");
            return;
        }
        for (int i=0; i<temp.size(); i++)
            temp.get(i).print();

    }
    public void addRequest (Owner owner,Playground playground)
    {
        PgRequest temp = new PgRequest(owner,playground);
        requests.add(temp);
    }
}
