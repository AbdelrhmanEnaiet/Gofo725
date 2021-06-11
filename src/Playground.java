import java.util.ArrayList;
import java.util.Date;

public class Playground {
    private int pId;
    private String name;
    private String address;
    private int maxTeamSize;
    private String pOwnerName;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private int bookingsNumber;

    public Playground(int id, String n, String adrs, int max, String o) {
        pId = id;
        name = n;
        address = adrs;
        maxTeamSize = max;
        pOwnerName = o;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return pId;
    }

    public String getAddress() {
        return address;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public String getpOwner() {
        return pOwnerName;
    }

    public void addBooking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int BookingID) {
        /*
        for (int i = 0; i < bookings.size(); i++) {
            Booking curr = bookings.get(i);
            if (curr.getStartTime() == startTime && curr.getPlayerCount() == playersCount && curr.getPlaygroundID() == forPlayground
                    && curr.getbookingCreator().equals(bookingCreator)) {
                System.out.print("Booking already exists");
                return;
            }
        }

        Collections.sort(bookings, new sortBookingByID());

        int BookingID = bookings.get(bookings.size() - 1).getBookingID() + 1;
         */
        Booking booking = new Booking(startTime, playersCount, forPlayground, bookingCreator, BookingID);
        bookings.add(booking);

    }


    public void print() //Not yet implemented
    {
        System.out.println("Playground Name: " + name);
        System.out.println("ID: " + pId);
        System.out.println("Address: " + address);
        System.out.println("Team Capacity: " + maxTeamSize);
    }

    public void printBookedTimes() {
        for (int i = 0; i < bookingsNumber; i++) {
            bookings.get(i).displayDate();
            System.out.print(" ");
        }
        System.out.println();
    }
}
