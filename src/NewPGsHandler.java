import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class NewPGsHandler
{
    public PlaygroundHandler playgroundHandler;
    public UserHandler userHandler;
    private Vector<PgRequest> requests = new Vector<PgRequest>();
    private File file;
    private String InnerFile;

    public NewPGsHandler()
    {
        //check if there is a file for the desired destination
        file=new File("GofoData");
        //make directory if new and set path to credentials
        if(file.mkdir())
        {
            InnerFile=file.getAbsolutePath()+"\\NewPGsRequests.txt";
            file=new File(InnerFile);//set the new file path to create the credentials
            try
            {
                file.createNewFile();//create the txt file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            {
                InnerFile=file.getAbsolutePath()+"\\NewPGsRequests.txt";
                file=new File(InnerFile);//set the existing file path
                readRequestsFile();
            }
        }

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
        PgRequest temp = new PgRequest(requests.size(),owner.getUserName(),playground);
        if (!requests.add(temp))
            return false;
        else
        {
            writeRequest(temp);
            return true;
        }
    }
    public boolean acceptRequest(int accId)
    {
        if (playgroundHandler.addPlayground(getRequestById(accId).getReqPlayground()))
        {
            requests.remove(getRequestById(accId));
            return true;
        }
        else return false;
    }
    public PgRequest getRequestById(int reqId)
    {
        for (int i=0; i<requests.size(); i++)
            if (requests.get(i).getId() == reqId)
                return requests.get(i);
        return null;
    }

    /**
     * Write the requests vector to an external file
     */
    public void writeRequest(PgRequest request)
    {
        try {
	    	/*initialise an object from FileWriter class
	    	 and add the  file's absolute path in the first slot and true in second slot to allow appending
	    	 and preserve any old data  */
            FileWriter myWriter = new FileWriter(file,true);
            /*0*/myWriter.write(request.getId()+" ");//write into file the Id
            /*1*/myWriter.write(request.getCreator()+" ");//write into file the creator's name
            /*2*/myWriter.write(request.getReqPlayground().getId()+" ");//write into file the playground's ID
            /*3*/myWriter.write(request.getReqPlayground().getName()+" ");//write into file the playground's name
            /*4*/myWriter.write(request.getReqPlayground().getAddress()+" ");//write into file the playground's Address
            /*5*/myWriter.write(request.getReqPlayground().getMaxTeamSize()+" ");//write into file the playground's team Size
            /*6*/myWriter.write(request.getReqPlayground().getpOwner().getUserName()+" ");//write into file the playground's owner's name
            myWriter.write(System.lineSeparator());// this indicate the end of the line
            //close the file writer to save the data in the file if it is left the data will not be saved
            myWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * read external file and update requests vector
     */
    public void readRequestsFile()
    {
        try
        {
            Scanner fileReader;//initialise a new scanner to read from file
            fileReader = new Scanner(file);
            //this checks if there is a next line in file then it returns true and stay in loop
            while(fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();//read the new line in the file
                String[] txtInFile = line.split(" ");//split the string into a string list
                Owner tempO = (Owner) userHandler.getUserByName(txtInFile[6]);
                Playground tempPG = new Playground(Integer.parseInt(txtInFile[2].trim()),
                                                   txtInFile[3],txtInFile[4], Integer.parseInt(txtInFile[5]),tempO);
                PgRequest tempReq = new PgRequest(Integer.parseInt(txtInFile[0].trim()),txtInFile[1],tempPG);
                    requests.add(tempReq);//adds loaded user into users arraylist
            }
            fileReader.close();//close the scanner as it is not going to be used again
        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
    }


}

