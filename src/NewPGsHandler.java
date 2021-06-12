import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class NewPGsHandler {
    public PlaygroundHandler playgroundHandler = new PlaygroundHandler();
    private UserHandler userhandler = new UserHandler();
    private Vector<PgRequest> requests = new Vector<PgRequest>();
    private static File file;
    private static String InnerFile;

    public NewPGsHandler() {
        //set the path to the needed file as it is created on the userHandler constructor
        file = new File("GofoData\\NewPGsRequests.txt");

        try {
            if (!(file.createNewFile()))//return true if created a new .txt and false if it already exists
            {

                readRequestsFile();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void viewRequestsOf(String oName) //view requests of specific owner
    {
        if (oName.equalsIgnoreCase("ALL")) //no specific owner, so show all
        {
            for (int i = 0; i < requests.size(); i++)
                requests.get(i).print();

        } else    //show for him
        {
            Vector<PgRequest> temp = new Vector<PgRequest>();
            for (int i = 0; i < requests.size(); i++) {
                if (requests.get(i).getCreator().equals(oName))
                    temp.add(requests.get(i));


            }
            if (temp.size() == 0) {
                System.out.println(oName + " does not have any Requests yet");
                return;
            }
            for (int i = 0; i < temp.size(); i++)
                temp.get(i).print();

        }
    }

    public boolean addRequest(Owner owner, Playground playground) {
        int id = 1;
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getId() > id)
                id = requests.get(i).getId();

        }
        id++;
        PgRequest temp = new PgRequest(id, owner.getUserName(), playground);
        if (!requests.add(temp))
            return false;
        else {
            updateRequestsFile();
            return true;
        }

    }

    public boolean acceptRequest(int accId) {
        if (playgroundHandler.addPlayground(getRequestById(accId).getReqPlayground())) {
            requests.remove(getRequestById(accId));
            updateRequestsFile();
            return true;
        } else return false;
    }

    public PgRequest getRequestById(int reqId) {
        for (int i = 0; i < requests.size(); i++)
            if (requests.get(i).getId() == reqId)
                return requests.get(i);
        return null;
    }

    /**
     * Write the requests vector to an external file
     */
    public void updateRequestsFile() {
        try {
            FileWriter myWriter = new FileWriter(file);
            for (int i = 0; i < requests.size(); i++) {
                PgRequest current = requests.get(i);
                /*0*/
                myWriter.write(current.getId() + " ");//write into file the Id
                /*1*/
                myWriter.write(current.getCreator() + " ");//write into file the creator's name
                /*2*/
                myWriter.write(current.getReqPlayground().getId() + " ");//write into file the playground's ID
                /*3*/
                myWriter.write(current.getReqPlayground().getName() + " ");//write into file the playground's name
                /*4*/
                myWriter.write(current.getReqPlayground().getAddress() + " ");//write into file the playground's Address
                /*5*/
                myWriter.write(current.getReqPlayground().getMaxTeamSize() + " ");//write into file the playground's team Size
                /*6*/
                myWriter.write(current.getReqPlayground().getpOwner() + " ");//write into file the playground's owner's name

                myWriter.write(System.lineSeparator());
                myWriter.write(current.getReqPlayground().getFreeTimes());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * read external file and update requests vector
     */
    public void readRequestsFile() {
        try {
            Scanner fileReader;//initialise a new scanner to read from file
            fileReader = new Scanner(file);
            //this checks if there is a next line in file then it returns true and stay in loop
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();//read the new line in the file

                String[] txtInFile = line.split(" ");//split the string into a string list

                Owner tempO = (Owner) userhandler.getUserByName(txtInFile[6]);
                ;
                Playground tempPG = new Playground(Integer.parseInt(txtInFile[2].trim()),
                        txtInFile[3], txtInFile[4], Integer.parseInt(txtInFile[5]), tempO.getUserName());
                line = fileReader.nextLine();
                tempPG.setavailableSlots(line);
                PgRequest tempReq = new PgRequest(Integer.parseInt(txtInFile[0].trim()), txtInFile[1], tempPG);
                requests.add(tempReq);//adds loaded user into users arraylist

            }
            fileReader.close();//close the scanner as it is not going to be used again

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }


}

