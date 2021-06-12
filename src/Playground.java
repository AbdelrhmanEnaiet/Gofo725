import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Playground {
    private int pId;
    private String name;
    private String address;
    private int maxTeamSize;
    private String pOwnerName;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private int bookingsNumber;
    private int[][] availableSlots = new int[7][12];
    //EX:   //0-sun   111100000011    //1 = booked /not available
            //1-mon   111100000011    //0 = free to book in
            //2-tue   111100000011
            //3-wed   111100000011
            //4-thu   111100000011
            //5-fri   111111111111
            //6-sat   111100000011

    public Playground(int id, String n, String adrs, int max, String o) {
        pId = id;
        name = n;
        address = adrs;
        maxTeamSize = max;
        pOwnerName = o;
        for (int i = 0; i < 7; i++)  //set available times to everyday from 8AM to 8PM except friday
        //to be filled with extra 1s as owner needs
        {
            if (i != 5)   //if not friday
                for (int j = 0; j < 12; j++) {
                    if (j > 3 && j < 10) //between 8AM and 8PM
                        availableSlots[i][j] = 0;
                    else
                        availableSlots[i][j] = 1;
                }
            else
                for (int j = 0; j < 12; j++)
                    availableSlots[i][j] = 1;
        }
    }

    public boolean isTimeAvailable(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int slot = calendar.get(Calendar.HOUR_OF_DAY) / 2;
        if (availableSlots[day][slot] == 0)
            return true;
        return false;
    }

    public boolean reserveSlot(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        int slot = calendar.get(Calendar.HOUR_OF_DAY) / 2;
        if (availableSlots[day][slot] == 0) {
            availableSlots[day][slot] = 1;
            return true;
        }
        return false;
    }

    public void setavailableSlots(int[][] availableSlots)
    {
        for (int i=0;i<7;i++)
        {
            for (int j=0;j<12;j++)
                this.availableSlots[i][j] = availableSlots[i][j];
        }
    }
    public void setavailableSlots(String str) {
        int[][] availableSlots = new int [7][12];
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                availableSlots[i][j] = str.charAt(i * 12 + j)-'0';
            }
        }
        setavailableSlots(availableSlots);
    }
    public String getFreeTimes() {
        String temp = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++)
                temp += Integer.toString(availableSlots[i][j]);
        }
        //System.out.println("From PG: " + temp);
        return temp;
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


    public void addBooking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int BookingId) {
        Booking temp = new Booking(startTime, playersCount, forPlayground, bookingCreator, BookingId);
        bookings.add(temp);
    }

    public void addNewBooking(Date startTime, int playersCount, int forPlayground, String bookingCreator) {
        if (!isTimeAvailable(startTime)) {
            System.out.println("Slot reserved, booking was not created");
            return;
        }

        Collections.sort(bookings, new sortBookingByID());
        int bookingID = 1;
        if (bookings.size() != 0)
            bookingID = bookings.get(bookings.size() - 1).getBookingID() + 1;

        Booking booking = new Booking(startTime, playersCount, forPlayground, bookingCreator, bookingID);
        bookings.add(booking);
        reserveSlot(startTime);
        bookingsNumber++;
    }

    int getBookingsNumber() {
        return bookingsNumber;
    }

    Booking getBookingWithIndex(int i) {
        return bookings.get(i);
    }


    public void print() {
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
