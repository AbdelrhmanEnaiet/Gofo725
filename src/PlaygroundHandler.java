import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class PlaygroundHandler {
    Vector<Playground> playgrounds = new Vector<Playground>();
    File file;
    static SimpleDateFormat dateFormater = new SimpleDateFormat("E/MMM/dd/yyyy/HH");

    public PlaygroundHandler() {  //stores all data about already made playgrounds, available times, bookings
        file = new File("GofoData\\Playgrounds.txt");

        try {
            if (!(file.createNewFile())) {
                readPlaygroundsFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void showPlaygroundsOfOwner(String owner)
    {
        for (int i=0;i<playgrounds.size();i++)
        {
            if(playgrounds.get(i).getpOwner().equals(owner))
                playgrounds.get(i).print();
        }
    }

    public boolean addPlayground(Playground playground)
    {
        readPlaygroundsFile();
        if (playgrounds.add(playground)) {
            writePlaygroundsFile();
            return true;
        } else
            return false;
    }

    public void showAllPlaygrounds() {
        if (playgrounds.size()==0)
        {
            System.out.println("NO playgrounds");
            return;
        }
        System.out.println("Printing "+playgrounds.size()+" Playgrounds");
        for (int i = 0; i < playgrounds.size(); i++) {
            Playground current = playgrounds.get(i);

            current.print();
            System.out.println();
            current.printBookedTimes();
        }
    }

    public boolean validate(int id) {
        for (int i = 0; i < playgrounds.size(); i++) {
            if (playgrounds.get(i).getId() == id)
                return true;
        }
        return false;
    }


    public void writePlaygroundsFile()  //update the playgrounds database
    {
        try {
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < playgrounds.size(); i++) {
                Playground current = playgrounds.get(i);
                String line = current.getId() + " " + current.getName() + " " + current.getAddress() + " " +
                        current.getMaxTeamSize() + " " + current.getpOwner();
                writer.write(line + System.lineSeparator());


                String times = current.getFreeTimes();

                writer.write((times + System.lineSeparator()));

                line = "";
                for (int j = 0; j < current.getBookingsNumber(); j++) {
                    Booking currBook = current.getBookingWithIndex(j);
                    String date = dateFormater.format(currBook.getStartTime()).toString();
                    line += date + " " + currBook.getPlayerCount() + " " + currBook.getPlaygroundID() +
                            " " + currBook.getbookingCreator() + " " + currBook.getBookingID() + " ";
                }

                writer.write(line + System.lineSeparator());

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void bookPlayground(Request request)
    {
        for (int i=0; i<playgrounds.size();i++)
        {
            if (playgrounds.get(i).getId() == request.getPlaygroundID())
            {
                Playground current = playgrounds.get(i);
                current.addNewBooking(request.getStartTime(),request.getPlayerCount(),current.getId(),request.getbookingCreator());
                writePlaygroundsFile();
                break;
            }

        }

    }
    public void getBookingsOf(String pName)
    {
        for(int i=0; i<playgrounds.size();i++)
        {
            for(int j=0;j<playgrounds.get(i).getBookingsNumber();j++)
            {
                if (playgrounds.get(i).getBookingWithIndex(j).getbookingCreator().equals(pName))
                {
                    playgrounds.get(i).getBookingWithIndex(j).print();
                }
            }
        }

    }
    public void readPlaygroundsFile()   //update the vector from the database
    {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (fileReader.hasNextLine()) {
                //read the information of the playground
                String line = fileReader.nextLine();
                String lineTokens[] = line.split(" ");
                Playground playground = new Playground(Integer.parseInt(lineTokens[0]), lineTokens[1], lineTokens[2],
                        Integer.parseInt(lineTokens[3]), lineTokens[4]);

                playgrounds.add(playground);

                Playground current = playgrounds.get(playgrounds.size() - 1);

                //read the available time slots
                line = fileReader.nextLine();
                int[][] timeSlots = new int[7][12];

                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 12; j++) {
                        timeSlots[i][j] = line.charAt(i * 7 + j)-'0';
                    }
                }

                current.setavailableSlots(timeSlots);

                //read the bookings
                line = fileReader.nextLine();
                lineTokens = line.split(" ");
                if (!lineTokens[0].equalsIgnoreCase(""))
                {
                    for (int i = 0; i < lineTokens.length; i += 5)
                    {
                        Date date = dateFormater.parse(lineTokens[i]);
                        current.addBooking(date, Integer.parseInt(lineTokens[i + 1]), Integer.parseInt(lineTokens[i + 2]),
                                lineTokens[i + 3], Integer.parseInt(lineTokens[i + 4]));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error in reading the file");
            e.printStackTrace();
        }

    }
}
