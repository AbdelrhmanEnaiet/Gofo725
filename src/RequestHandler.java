import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class RequestHandler {
    private static PlaygroundHandler playgroundHandler = new PlaygroundHandler();
    static ArrayList<Request> requests = new ArrayList<Request>();
    private static File file;
    static SimpleDateFormat dateFormater = new SimpleDateFormat("E/MMM/dd/yyyy/HH");

    public RequestHandler() {
        file = new File("GofoData\\PlayerRequests.txt");


        try {

            if (!file.createNewFile()) {
                readRequestsFile();
            }

        } catch (IOException e) {
            System.out.println("error in file opening");
        }

    }

    public void addRequest(Date startTime, int playersCount, int forPlayground,
                                  String bookingCreator) {
        //check to see if request already exists
        for (int i = 0; i < requests.size(); i++) {
            Request curr = requests.get(i);
            if (curr.getStartTime() == startTime && curr.getPlayerCount() == playersCount && curr.getPlaygroundID() == forPlayground
                    && curr.getbookingCreator() == bookingCreator) {
                System.out.println("Request already exists");
                return;
            }
        }


        Collections.sort(requests, new sortByID());

        int requestID;
        if (requests.size()==0) requestID = 1;
        else requestID = requests.get(requests.size() - 1).getRequestID() + 1;

        Request request = new Request(startTime, playersCount, forPlayground, bookingCreator, requestID);
        requests.add(request);
        writeRequestsFile();
        System.out.println("Request added successfully");

    }


    public void acceptRequest(int id)
    {
        for (int i=0; i<requests.size();i++)
        {
            if (requests.get(i).getRequestID() == id)
            {
                playgroundHandler.bookPlayground(requests.get(i));
                requests.remove(requests.get(i));
                writeRequestsFile();
                break;
            }
        }
    }

    public void showRequestsByPlayground(int ID) {
        for (int i = 0; i < requests.size(); i++) {
            Request curr = requests.get(i);
            if (curr.getPlaygroundID() == ID)
                curr.printRequest();
        }
    }
    //Jun/13/2021/12
    public void showRequestsByPlayer(String name)
    {
        for (int i = 0; i < requests.size(); i++) {
            Request curr = requests.get(i);
            if (curr.getbookingCreator().equals(name))
                curr.printRequest();
        }
    }

    public static void readRequestsFile() {
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String lineTokens[] = line.split(" ");
                Date date = dateFormater.parse(lineTokens[0]);


                Request tempRequest = new Request(date, Integer.parseInt(lineTokens[1].trim()),
                        Integer.parseInt(lineTokens[2].trim()), lineTokens[3],
                        Integer.parseInt(lineTokens[4]));
                requests.add(tempRequest);


            }
        } catch (Exception e) {
            System.out.println("error in reading the file");
            e.printStackTrace();
        }
    }


    public static void writeRequestsFile() {
        try {
            FileWriter writer = new FileWriter(file);

            for (int i=0; i<requests.size(); i++)
            {
                Request request = requests.get(i);
                String date = dateFormater.format(request.getStartTime()).toString();
                String line = date + " " + request.getPlayerCount() + " " + request.getPlaygroundID() + " " +
                        request.getbookingCreator() + " " + request.getRequestID();
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
