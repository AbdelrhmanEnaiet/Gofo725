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

    /**
     * Create a new playground and set it's attributes
     * @param id The playground's id 
     * @param n The playground's name
     * @param adrs The playground's address
     * @param max The playground's max number of players
     * @param o The playground's owner's name
     */
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
    
    /**
     * Checks if the date is available for booking
     * @param date The date to be booked
     * @return True if the time is available
     */
    public boolean isTimeAvailable(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int slot = calendar.get(Calendar.HOUR_OF_DAY) / 2;
        if (availableSlots[day][slot] == 0)
            return true;
        return false;
    }

    /**
     * Book a date
     * @param date the date to be booked
     * @return true if the booking process is success
     */
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

    /**
     * Initialise the available times of the playground
     * @param availableSlots A two dimension array
     */
    public void setavailableSlots(int[][] availableSlots)
    {
        for (int i=0;i<7;i++)
        {
            for (int j=0;j<12;j++)
                this.availableSlots[i][j] = availableSlots[i][j];
        }
    }
    
    /**
     * Convert a string of available times to a 2D array and initialise the availableSlots 2D array attribute
     * @param str The available times of the playground
     */
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
    /**
     * Returns the available times of the playground of each day of the week
     * @return A string of the available times of the playground
     */
    public String getFreeTimes() {
        String temp = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++)
                temp += Integer.toString(availableSlots[i][j]);
        }
       
        return temp;
    }
    
    /**
     * 
     * @return The playground name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return The playground id
     */
    public int getId() {
        return pId;
    }

    /**
     * 
     * @return The playground address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @return The playground max number of players can play
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }
    
    /**
     * 
     * @return The playground owner name
     */
    public String getpOwner() {
        return pOwnerName;
    }


    /**
     * Create a new booking for a playground
     * @param startTime The time to book
     * @param playersCount The number of players will be going to play
     * @param forPlayground The playground to be booked
     * @param bookingCreator The player name
     * @param BookingId The booking id
     */
    public void addBooking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int BookingId) {
        Booking temp = new Booking(startTime, playersCount, forPlayground, bookingCreator, BookingId);
        bookings.add(temp);
    }
    
    /**
     * Add a new booking and add it to the database
     * @param startTime The time to book
     * @param playersCount The number of players will be going to play
     * @param forPlayground The playground to be booked
     * @param bookingCreator The player name
     */
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

    /**
     * 
     * @return The number of bookings made
     */
    int getBookingsNumber() {
        return bookingsNumber;
    }
    
    /**
     * Get a booking by it's id
     * @param i The booking id
     * @return A booking
     */
    Booking getBookingWithIndex(int i) {
        return bookings.get(i);
    }

    
    /**
     * Display the playground information
     */
    public void print() {
        System.out.println("Playground Name: " + name);
        System.out.println("ID: " + pId);
        System.out.println("Address: " + address);
        System.out.println("Team Capacity: " + maxTeamSize);
    }

    /**
     * Display the Dates of all the bookings
     */
    public void printBookedTimes() {
        for (int i = 0; i < bookingsNumber; i++) {
            bookings.get(i).displayDate();
            System.out.print(" ");
        }
        System.out.println();
    }
}
