import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class RequestHandler {
    static ArrayList<Request> requests;
    private static File file;
    static SimpleDateFormat dateFormater = new SimpleDateFormat("E, MMM dd yyyy HH:mm::ss");

    public RequestHandler() throws IOException {
        file = new File("GofoData\\PlayerRequests.txt");


        try {

            if (!file.createNewFile()) {
                readRequestsFile();
            }

        } catch (IOException e) {
            System.out.println("error in file opening");
        }

    }

    public static void addRequest(Date startTime, int playersCount, int forPlayground,
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

        int requestID = requests.get(requests.size() - 1).getRequestID() + 1;
        Request request = new Request(startTime, playersCount, forPlayground, bookingCreator, requestID);
        writeRequestsFile(request);
        System.out.println("Request added successfully");

    }


    public static void DeleteRequest(Request request) {
        requests.remove(request);
    }

    public static void showRequestsByPlayground(int ID) {
        for (int i = 0; i < requests.size(); i++) {
            Request curr = requests.get(i);
            if (curr.getPlaygroundID() == ID)
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
        }
    }


    public static void writeRequestsFile(Request request) {
        try {
            FileWriter writer = new FileWriter(file, true);

            String date = request.getStartTime().toString();

            String line = date + " " + request.getPlayerCount() + " " + request.getPlaygroundID() + " " +
                    request.getbookingCreator() + " " + request.getRequestID();
            writer.write(line);
            writer.write(System.lineSeparator());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //writeRequest


}
