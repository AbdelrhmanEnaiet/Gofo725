import java.util.Vector;

public class NewPGsHandler
{
    public PlaygroundHandler playgroundHandler;
    private Vector<PgRequest> requests = new Vector<PgRequest>();


    public void viewRequestsOf(String oName) //view requests of specific owner
    {
        if (oName != "")
        {
            Vector<PgRequest> temp = new Vector<PgRequest>();
            for (int i = 0; i < requests.size(); i++)
            {
                if (requests.get(i).getCreator() == oName)
                    temp.add(requests.get(i));
            }
            if (temp.size() == 0)
            {
                System.out.println(oName + " does not have any Requests yet");
                return;
            }
            for (int i = 0; i < temp.size(); i++)
                temp.get(i).print();
        }
        else    //no specific owner, so show all
        {
            for (int i=0; i<requests.size(); i++)
                requests.get(i).print();
        }
    }
    public boolean addRequest (Owner owner,Playground playground)
    {
        readRequestsFile();
        PgRequest temp = new PgRequest(owner,playground,requests.size());
        if (!requests.add(temp))
            return false;
        else
        {
            writeRequestsFile();
            return true;
        }
    }
    public boolean acceptRequest(int accId)
    {
        return playgroundHandler.addPlayground(getRequestById(accId).getReqPlayground());
    }
    public PgRequest getRequestById(int reqId)
    {
        for (int i=0; i<requests.size(); i++)
            if (requests.get(i).getId() == reqId)
                return requests.get(i);
        return null;
    }
    //                  /!\ /!\   not implemented yet   /!\
    //                  /!\ /!\   not implemented yet   /!\
    public void writeRequestsFile() //Write the requests vector to an external file
    {
        //Write the requests vector to and external file
    }
    public void readRequestsFile() //read external file and update requests vector
    {
        //Write the requests vector to and external file
    }
}
