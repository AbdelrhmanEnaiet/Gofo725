import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Playground {
    private int pId;
    private String name;
    private String address;
    private int maxTeamSize;
    private String pOwnerName;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private int bookingsNumber;
    private int[][] availableSlots = new int [7][12]; //EX: //1-sun   111111101111    //1 = booked /not available
                                                            //2-mon   110111111111    //0 = free to book in
                                                            //3-tue   111111111111
                                                            //4-wed   111111011111
                                                            //5-thu   111111111111
                                                            //6-fri   111111011111
                                                            //7-sat   111111101111

    public Playground(int id, String n, String adrs, int max, String o) {
        pId = id;
        name = n;
        address = adrs;
        maxTeamSize = max;
        pOwnerName = o;
        for (int i=0; i<7;i++)  //set available times to everyday from 8AM to 8PM except friday
                                //to be filled with extra 1s as owner needs
        {
            if (i!=6)   //if not friday
                for (int j = 0; j < 12; j++)
                {
                    if (j>7 && j<21) //between 8AM and 8PM
                        availableSlots[i][j] = 0;
                    else
                        availableSlots[i][j] = 1;
                }
            else
                for (int j = 0; j < 12; j++)
                    availableSlots[i][j] = 1;
        }
    }

    public boolean isTimeAvailable(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int slot = calendar.get(Calendar.HOUR_OF_DAY)/2;
        if (availableSlots[day][slot]==0)
            return true;
        return false;
    }
    public boolean reserveSlot(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int slot = calendar.get(Calendar.HOUR_OF_DAY)/2;
        if (availableSlots[day][slot]==0)
        {
            availableSlots[day][slot]=1;
            return true;
        }
        return false;
    }
    public void setavailableSlots(int[][] availableSlots)
    {
        this.availableSlots = availableSlots;
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

    public void addBooking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int BookingID)
    {
        if (isTimeAvailable(startTime))
        {
            System.out.println("Slot reserved, booking was not created");
            return;
        }
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


    public void print()
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
